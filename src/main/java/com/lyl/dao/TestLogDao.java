package com.lyl.dao;

import com.lyl.bean.TestLogBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @PACKAGE_NAME: com.lyl.dao
 * @ClassName: TestLogDao
 * @Description:  测试日志 dao
 * @Date: 2020-12-25 19:11
 * @Author: 木子雷 公众号
 **/
@Mapper
public interface TestLogDao {

    /**
     *  批量进行日志插入
     * @param list  测试日志集合
     */
    void batchInsert(List<TestLogBean> list);

}
