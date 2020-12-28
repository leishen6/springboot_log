package com.lyl.listener;

import com.lyl.logclean.CleanManager;
import com.lyl.logpool.LogPoolManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @PACKAGE_NAME: com.lyl.listener
 * @ClassName: InitServletContextListener
 * @Description:  项目启动监听器，启动时进行数据初始化、以及启动各模块组件
 * @Date: 2020-12-23 15:41
 * @Author: 木子雷 公众号
 **/
@WebListener
public class InitServletContextListener implements ServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private LogPoolManager logPoolManager;

    @Autowired
    private CleanManager cleanManager;


    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // 日志 异步池化处理 启动
        logPoolManager.init();

        logger.info(" 日志异步池化处理启动成功. . . . . . .");
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 关闭线程池
        logPoolManager.shutdown();
        cleanManager.shutdown();
    }
}
