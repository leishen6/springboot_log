package com.lyl.logpool;

import com.lyl.bean.TestLogBean;
import com.lyl.service.TestLogService;

import java.util.List;

/**
 * @PACKAGE_NAME: com.lyl.logpool
 * @ClassName: InsertThread
 * @Description:  日志批量插入 的工作线程
 * @Date: 2020-12-25 19:03
 * @Author: 木子雷 公众号
 **/
public class InsertThread implements Runnable {

    private TestLogService testLogService;

    private List<TestLogBean> list;

    public InsertThread(TestLogService testLogService, List<TestLogBean> list) {
        this.testLogService = testLogService;
        this.list = list;
    }

    @Override
    public void run() {
        testLogService.batchInsert(list);
        // hellp GC
        list = null;
    }
}
