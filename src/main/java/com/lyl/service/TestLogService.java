package com.lyl.service;

import com.lyl.bean.TestLogBean;

import java.util.List;

/**
 * @PACKAGE_NAME: com.lyl.service
 * @ClassName: TestLogService
 * @Description:
 * @Date: 2020-12-25 19:12
 * @Author: 木子雷 公众号
 **/
public interface TestLogService {

    /**
     *  批量进行日志插入
     * @param list
     */
    void batchInsert(List<TestLogBean> list);

}
