package com.zhou.springbootquartz.config;

import com.zhou.springbootquartz.job.ScheduledJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/2 6:05 下午
 */
@Component
public class SchedulerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerConfig.class);

    @Resource
    private Scheduler scheduler;

    public void addJob() throws SchedulerException {
        scheduler.start();

        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class).withIdentity("test_name", "test_group").build();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");

        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("test_name", "test_group").withSchedule(scheduleBuilder).build();

        scheduler.scheduleJob(jobDetail, trigger);

        LOGGER.info("定时任务创建成功");
    }

    public void pushJob() throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey("test_name", "test_group"));
        LOGGER.info("定时任务被挂起");
    }

    public void resumeJob() throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey("test_name", "test_group"));
        LOGGER.info("定时任务恢复");
    }
}
