package com.lyl.bean;

/**
 * @PACKAGE_NAME: com.lyl.bean
 * @ClassName: TestLogBean
 * @Description:  测试日志
 * @Date: 2020-12-25 19:09
 * @Author: 木子雷 公众号
 **/
public class TestLogBean {

    private int id;

    private String logContent;

    private String createts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getCreatets() {
        return createts;
    }

    public void setCreatets(String createts) {
        this.createts = createts;
    }


    @Override
    public String toString() {
        return "TestLogBean{" +
                "id=" + id +
                ", logContent='" + logContent + '\'' +
                ", createts='" + createts + '\'' +
                '}';
    }
}
