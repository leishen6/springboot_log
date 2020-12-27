package com.lyl.logclean;

import cn.hutool.setting.dialect.Props;
import com.lyl.bean.LogCleanBean;
import com.lyl.service.LogCleanService;
import com.lyl.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @PACKAGE_NAME: com.lyl.logclean
 * @ClassName: CleanManager
 * @Description:  日志清理 控制器
 * @Date: 2020-12-23 15:10
 * @Author: 木子雷 公众号
 **/
@Component("cleanManager")
public class CleanManager {

    @Autowired
    private LogCleanService logCleanService;

    private static ExecutorService executorService;
    private static Props props = new Props("application-cleanLog.properties");

    private static LogCleanBean logCleanBean;

    static {
        int threadNum = props.getInt("threads.pool.num");
        executorService = Executors.newFixedThreadPool(threadNum);
        logCleanBean = new LogCleanBean();
        logCleanBean.setBatchCleanCount(props.getInt("log.clean.batchCount"));
        logCleanBean.setTableName(props.getStr("log.clean.table"));
        logCleanBean.setFieldName(props.getStr("log.clean.filed"));
        logCleanBean.setMinTime(DateUtil.getBeforeTime(props.getInt("log.clean.dateNum")));
    }


    /**
     *  多线程清理日志启动
     */
    public void cleanLogStart(){

        int whileNum = props.getInt("log.clean.batchNum");

        while (whileNum > 0){
            LogCleanBean logClean = null;

            List<String> list = logCleanService.selectTime(logCleanBean);
            if (list != null && list.size() > 0){
                logClean = new LogCleanBean();
                logClean.setTableName(logCleanBean.getTableName());
                logClean.setFieldName(logCleanBean.getFieldName());
                logClean.setMinTime(list.get(list.size()-1));
                logClean.setMaxTime(list.get(0));
                logCleanBean.setMinTime(logClean.getMinTime());

                // 清理次数进行递减
                --whileNum;

                // 此次查询已经不足设置的批量清理的数据量了，所以已经清理干净了
                if (list.size() < logCleanBean.getBatchCleanCount()){
                    whileNum = 0;
                }
            }else {
                break;
            }
            // 进行多线程处理
            executorService.execute(new CleanThread(this.logCleanService, logClean));
        }

    }

}
