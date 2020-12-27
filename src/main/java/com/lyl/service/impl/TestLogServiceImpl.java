package com.lyl.service.impl;

import com.lyl.bean.TestLogBean;
import com.lyl.dao.TestLogDao;
import com.lyl.service.TestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PACKAGE_NAME: com.lyl.service.impl
 * @ClassName: TestLogServiceImpl
 * @Description:
 * @Date: 2020-12-25 19:13
 * @Author: 木子雷 公众号
 **/
@Service("testLogService")
public class TestLogServiceImpl implements TestLogService{

    @Autowired
    private TestLogDao testLogDao;

    @Override
    public void batchInsert(List<TestLogBean> list) {
        testLogDao.batchInsert(list);
    }
}
