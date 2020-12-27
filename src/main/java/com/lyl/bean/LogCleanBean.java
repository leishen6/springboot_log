package com.lyl.bean;


/**
 * @PACKAGE_NAME: com.lyl.bean
 * @ClassName: LogCleanBean
 * @Description:  日志清理 的 bean
 * @Date: 2020-12-23 14:36
 * @Author: 木子雷 公众号
 **/
public class LogCleanBean {

    /**
     *  需要删除的表
     */
    private String tableName;

    /**
     *  根据的哪个字段进行的删除，一般都是日期型字段
     */
    private String fieldName;

    /**
     *  每次删除的数量
     */
    private int batchCleanCount;

    /**
     *  根据每次删除的数据量，先查询出的符合在这个数据量的时间间隔的最小时间
     */
    private String minTime;

    /**
     *  根据每次删除的数据量，先查询出的符合在这个数据量的时间间隔的最大时间
     */
    private String maxTime;



    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getBatchCleanCount() {
        return batchCleanCount;
    }

    public void setBatchCleanCount(int batchCleanCount) {
        this.batchCleanCount = batchCleanCount;
    }

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public String getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }


    @Override
    public String toString() {
        return "LogCleanBean{" +
                "tableName='" + tableName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", batchCleanNum=" + batchCleanCount +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                '}';
    }
}
