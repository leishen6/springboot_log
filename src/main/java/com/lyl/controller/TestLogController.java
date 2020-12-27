package com.lyl.controller;

import com.lyl.bean.TestLogBean;
import com.lyl.logpool.LogPoolManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PACKAGE_NAME: com.lyl.controller
 * @ClassName: TestLogController
 * @Description:
 * @Date: 2020-12-25 20:19
 * @Author: 木子雷 公众号
 **/
@RestController
@RequestMapping(value = "/v1/api")
public class TestLogController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogPoolManager logPoolManager;


    @GetMapping("/log/test")
    public void logTest() {
        // 此处可以写具体的业务逻辑

        TestLogBean testLogBean = new TestLogBean();
        testLogBean.setLogContent("test log , test log ");
        // 将业务日志放入到队列中，然后使用线程 异步 批量进行入库，以提升接口的响应速度
        logPoolManager.addLog(testLogBean);

        logger.info("log test success !");
    }

}
