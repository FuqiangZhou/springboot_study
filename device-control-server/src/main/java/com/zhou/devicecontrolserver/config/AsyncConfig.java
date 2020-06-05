package com.zhou.devicecontrolserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/2 3:26 下午
 */
@Configuration
public class AsyncConfig {

    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(200);
        executor.setQueueCapacity(20);
        executor.initialize();
        return executor;
    }

}
