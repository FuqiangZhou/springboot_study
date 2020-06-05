package com.zhou.devicecontrolserver.config;

import com.zhou.devicecontrolserver.job.MarqueeJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/3 10:48 上午
 */
@Component
public class SchedulerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerConfig.class);

    @Resource
    private Scheduler scheduler;

    public void addJob() throws SchedulerException {
        scheduler.start();

        JobDetail jobDetail = JobBuilder.newJob(MarqueeJob.class).withIdentity("marquee", "marquee_group").build();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");

        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("marquee", "marquee_group").withSchedule(scheduleBuilder).build();

        scheduler.scheduleJob(jobDetail, trigger);

        LOGGER.info("定时任务创建成功");
    }

    public void pushJob() throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey("marquee", "marquee_group"));
        LOGGER.info("定时任务被挂起");
    }

    public void resumeJob() throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey("marquee", "marquee_group"));
        LOGGER.info("定时任务恢复");
    }
}
