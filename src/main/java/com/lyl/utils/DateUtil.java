package com.lyl.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @PACKAGE_NAME: com.lyl.utils
 * @ClassName: DateUtil
 * @Description:  日期工具类
 * @Date: 2020-12-23 16:47
 * @Author: 木子雷 公众号
 **/
public class DateUtil {


    /**
     *  查询当前日期前 多少天 的日期
     * @param dateNum
     * @return
     */
    public static String getBeforeTime(int dateNum){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 0-dateNum);
        date = calendar.getTime();
        return sdf.format(date);
    }

    public static void main(String[] args) {
        getBeforeTime(185);
    }
}
