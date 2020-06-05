package com.zhou.springbootquartz.controller;

import com.zhou.springbootquartz.config.SchedulerConfig;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/2 6:16 下午
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Resource
    private SchedulerConfig schedulerConfig;

    @GetMapping("/addJob")
    public String addJob(){
        try {
            this.schedulerConfig.addJob();
            return "SUCCESS";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @GetMapping("/pushJob")
    public String pushJob(){
        try {
            this.schedulerConfig.pushJob();
            return "SUCCESS";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @GetMapping("/resumeJob")
    public String resumeJob(){
        try {
            this.schedulerConfig.resumeJob();
            return "SUCCESS";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

}
