package com.lyl.logpool;

import com.lyl.bean.TestLogBean;
import com.lyl.service.TestLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @PACKAGE_NAME: com.lyl.logpool
 * @ClassName: LogPoolManager
 * @Description: 日志 池化管理
 * @Date: 2020-12-25 19:02
 * @Author: 木子雷 公众号
 **/
@Component
@Scope("singleton")
public class LogPoolManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestLogService testLogService;

    /**
     * 日志队列 的最大容量
     */
    private int MAX_QUEUE_SIZE = 100;

    /**
     *  日志批量插入的数量，10条日志
     */
    private int BATCH_SIZE = 10;

    /**
     * 线程睡眠时间，具体时间需要结合项目实际情况，单位毫秒
     */
    private int SLEEP_TIME = 500;

    /**
     * 日志插入执行的最大的时间间隔，单位毫秒
     */
    private long MAX_EXE_TiME = 5000;

    /**
     * 创建一个单线程的线程池
     */
    private ExecutorService logManagerThreadPool = Executors.newSingleThreadExecutor();


    /**
     * 创建一个定长的线程池，线程数量为（java虚拟机可用的处理器数量 * 2 + 20 ）
     */
    private ExecutorService logWorkerThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2 + 20);


    /**
     * 任务队列，存放日志内容
     */
    private BlockingQueue<TestLogBean> queue;

    /**
     * Boolean 原子变量类
     */
    private AtomicBoolean run = new AtomicBoolean(true);

    /**
     * 整型 原子变量类，记录 任务队列 中的任务数量
     */
    private AtomicInteger logCount = new AtomicInteger(0);


    /**
     * 上一次执行日志插入时的时间
     */
    private long lastExecuteTime;


    /**
     *  初始化
     */
    public void init(){
        // 基于链表的双向阻塞队列，在队列的两端都可以插入和移除元素，是线程安全的，多线程并发下效率更高
        queue = new LinkedBlockingDeque<TestLogBean>(MAX_QUEUE_SIZE);
        lastExecuteTime = System.currentTimeMillis();

        logger.info("LogPoolManager init successfully。。。。。。");

        logManagerThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                while (run.get()){
                    try {
                        // 线程休眠，具体时间根据项目的实际情况配置
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        logger.error("log Manager Thread sleep fail ", e);
                    }
                    // 满足存放了10个日志  或  满足时间间隔已经大于设置的最大时间间隔时  执行日志插入
                    if (logCount.get() >= BATCH_SIZE || (System.currentTimeMillis() - lastExecuteTime) > MAX_EXE_TiME) {
                        if (logCount.get() > 0) {
                            logger.info("begin drain log queue to database...");
                            List<TestLogBean> list = new ArrayList<TestLogBean>();
                            /**
                             *  drainTo (): 一次性从BlockingQueue获取所有可用的数据对象（还可以指定获取数据的个数），
                             *  通过该方法，可以提升获取数据效率；不需要多次分批加锁或释放锁。
                             *  将取出的数据放入指定的list集合中
                             */
                            queue.drainTo(list);
                            // 任务队列 中任务数量置为0
                            logCount.set(0);
                            // 从线程池中取出线程执行日志插入
                            logWorkerThreadPool.execute(new InsertThread(testLogService, list));
                            logger.info("end drain log queue to database...");
                        }
                        // 获取当前执行的时间
                        lastExecuteTime = System.currentTimeMillis();
                    }
                }
                logger.info("LogPoolManager shutdown successfully");
            }
        });
    }


    /**
     *  将日志放入到 队列中
     * @param testLogBean
     */
    public void addLog(TestLogBean testLogBean) {
        if (logCount.get() >= MAX_QUEUE_SIZE) {
            logger.error("Warning.. Log count exceed log queue's max size ！");
            return;
        }
        // 将日志放入 任务队列中，放入成功返回true
        this.queue.offer(testLogBean);
        // 队列中的任务数量 +1
        logCount.incrementAndGet();
    }


    /**
     *  关闭 日志池
     */
    public void shutdown() {
        logger.info("LogPoolManager shutdown...");
        // 结束while循环
        run.set(false);
        // 关闭线程池
        logWorkerThreadPool.shutdownNow();
        logManagerThreadPool.shutdownNow();
    }

}
