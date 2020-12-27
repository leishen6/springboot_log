package com.lyl.logclean;

import com.lyl.bean.LogCleanBean;
import com.lyl.service.LogCleanService;


/**
 * @PACKAGE_NAME: com.lyl.logclean
 * @ClassName: CleanThread
 * @Description:  实际进行日志清理时的线程
 * @Date: 2020-12-23 15:07
 * @Author: 木子雷 公众号
 **/
public class CleanThread implements Runnable {

    private LogCleanService logCleanService;

    private LogCleanBean logCleanBean;

    public CleanThread(LogCleanService logCleanService, LogCleanBean logCleanBean) {
        this.logCleanService = logCleanService;
        this.logCleanBean = logCleanBean;
    }

    @Override
    public void run() {
        logCleanService.logBatchClean(logCleanBean);
        // hellp GC
        logCleanBean = null;
    }
}
