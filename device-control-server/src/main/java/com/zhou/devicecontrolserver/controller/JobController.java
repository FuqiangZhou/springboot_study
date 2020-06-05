package com.zhou.devicecontrolserver.controller;

import com.zhou.devicecontrolserver.config.SchedulerConfig;
import com.zhou.devicecontrolserver.utils.PageDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/3 10:53 上午
 */
@RestController
@RequestMapping("/job")
public class JobController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);

    @Resource
    private SchedulerConfig schedulerConfig;

    @GetMapping("/startJob")
    public PageDataResult<String> startJob(){
        try {
            this.schedulerConfig.addJob();
            return new PageDataResult<>(1, "创建并启动任务成功", "操作成功");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new PageDataResult<>(2, "系统错误");
        }
    }


    @GetMapping("/pushJob")
    public PageDataResult<String> pushJob(){
        try {
            this.schedulerConfig.pushJob();
            return new PageDataResult<>(1, "任务被挂起成功","操作成功");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new PageDataResult<>(2, "系统错误");
        }
    }

    @GetMapping("/resumeJob")
    public PageDataResult<String> resumeJob(){
        try {
            this.schedulerConfig.resumeJob();
            return new PageDataResult<>(1, "任务被恢复成功","操作成功");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new PageDataResult<>(2, "系统错误");
        }
    }
}
