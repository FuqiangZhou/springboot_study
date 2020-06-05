package com.zhou.simplehellostarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/20 5:34 下午
 */
@Data
@ConfigurationProperties(prefix = "simple.hello")
public class SimpleHelloProperties {

    private String hello;

    private String hi;

    private Test test;

    @Data
    public static class Test{
        private int type;
    }
}
