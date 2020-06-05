package com.zhou.mqttdeviceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class MqttDeviceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqttDeviceServerApplication.class, args);
    }

}
