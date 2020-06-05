package com.zhou.springbootwithmybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhou.springbootwithmybatisplus.mapper")
public class SpringbootwithmybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootwithmybatisplusApplication.class, args);
    }

}
