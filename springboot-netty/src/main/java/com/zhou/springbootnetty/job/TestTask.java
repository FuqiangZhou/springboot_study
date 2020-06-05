package com.zhou.springbootnetty.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/2 3:42 下午
 */
//@Component
public class TestTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestTask.class);

    private static int flag = 0;

    private static boolean canStart = false;

    private static int openDeviceId = 0;

    private static int closeDeviceId = 0;

//    @Scheduled(cron = "0/1 * * * * ?")
    public void myTaskA(){
        if (!canStart){
            flag ++;
            LOGGER.info("myTaskA => flag is {} | openDeviceId is {}", flag, openDeviceId);
            if (flag == 10){
                canStart = true;
                flag = 0;
                openDeviceId ++;
                if (openDeviceId == 3){
                    openDeviceId = 0;
                }
            }
        }else {
            flag ++;
            LOGGER.info("myTaskA => flag is {} | closeDeviceId is {}", flag, closeDeviceId);
            if (flag == 10){
                canStart = false;
                flag = 0;
                closeDeviceId ++;
                if (closeDeviceId == 3){
                    closeDeviceId = 0;
                }
            }
        }

    }
}
