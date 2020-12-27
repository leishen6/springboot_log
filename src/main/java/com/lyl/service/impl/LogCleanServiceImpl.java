package com.lyl.service.impl;

import com.lyl.bean.LogCleanBean;
import com.lyl.dao.LogCleanDao;
import com.lyl.service.LogCleanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PACKAGE_NAME: com.lyl.service.impl
 * @ClassName: LogCleanServiceImpl
 * @Description:  日志清理 service层
 * @Date: 2020-12-23 14:50
 * @Author: 木子雷 公众号
 **/
@Service("logCleanService")
public class LogCleanServiceImpl implements LogCleanService{

    @Autowired
    private LogCleanDao logCleanDao;

    @Override
    public List<String> selectTime(LogCleanBean logCleanBean) {
        return logCleanDao.selectTime(logCleanBean);
    }

    @Override
    public void logBatchClean(LogCleanBean logCleanBean) {
        logCleanDao.logBatchClean(logCleanBean);
    }
}
