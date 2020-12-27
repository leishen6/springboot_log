package com.lyl.dao;

import com.lyl.bean.LogCleanBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @PACKAGE_NAME: com.lyl.dao
 * @ClassName: LogCleanDao
 * @Description:  日志清理 dao
 * @Date: 2020-12-23 14:45
 * @Author: 木子雷 公众号
 **/
@Mapper
public interface LogCleanDao {

    /**
     *  查询出符合删除量的 时间间隔
     * @param logCleanBean  日志清理bean
     * @return
     */
    List<String> selectTime(LogCleanBean logCleanBean);

    /**
     *  根据时间间隔进行删除
     * @param logCleanBean  日志清理bean
     */
    void logBatchClean(LogCleanBean logCleanBean);

}
