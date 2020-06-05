package com.zhou.simplehellostarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/20 5:41 下午
 */
@Configuration
@ConditionalOnClass(SimpleHelloService.class)
@EnableConfigurationProperties(SimpleHelloProperties.class)
public class SimpleHelloAutoConfiguration {

    @Resource
    private SimpleHelloProperties simpleHelloProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "simple.hello", value = "enabled", havingValue = "true")
    public SimpleHelloService simpleHelloService(){
        return new SimpleHelloService(simpleHelloProperties.getHello(), simpleHelloProperties.getHi(), simpleHelloProperties.getTest());
    }
}
