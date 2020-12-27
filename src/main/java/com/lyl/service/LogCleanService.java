package com.lyl.service;

import com.lyl.bean.LogCleanBean;
import java.util.List;

/**
 * @PACKAGE_NAME: com.lyl.service
 * @ClassName: LogCleanService
 * @Description:
 * @Date: 2020-12-23 14:49
 * @Author: 木子雷 公众号
 **/
public interface LogCleanService {

    /**
     *  查询出符合删除量的 时间间隔
     * @param logCleanBean
     * @return
     */
    List<String> selectTime(LogCleanBean logCleanBean);

    /**
     *  根据时间间隔进行删除
     * @param logCleanBean
     */
    void logBatchClean(LogCleanBean logCleanBean);

}
