package com.lyl.quartz;

import com.lyl.logclean.CleanManager;
import com.lyl.utils.SpringContextJobUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @PACKAGE_NAME: com.lyl.quartz
 * @ClassName: LogCleanJob
 * @Description:  日志清理 定时任务
 * @Date: 2020-06-26 17:34
 **/
public class LogCleanJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void execute(JobExecutionContext arg0)
            throws JobExecutionException {
        logger.debug("Log Clean Quartz execute start . . . . . . .");

        // TODO 业务逻辑
        /**
         * 通过 自定义工具类 获取Spring容器中的实例bean
         * 在quartz框架中，Job 是通过反射出来的实例，不受spring的管理，即使使用@Component注解，
         * 将其标记为组件类，它也不会被注册到容器中，所以就无法直接通过自动注入IOC容器中的对象等
         */
        CleanManager cleanManager = (CleanManager) SpringContextJobUtil.getBean("cleanManager");
        // 进行日志清理
        cleanManager.cleanLogStart();

        logger.debug("Log Clean Quartz execute end . . . . . . . .");
    }

}
