package com.zhou.springbootquartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/2 6:03 下午
 */
public class ScheduledJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledJob.class);

    private static int flag = 0;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        flag ++;
        LOGGER.info("当前时间=>{}, flag is {}", LocalDateTime.now(), flag);
        if (flag > 10){
            flag = 0;
        }
    }


}
