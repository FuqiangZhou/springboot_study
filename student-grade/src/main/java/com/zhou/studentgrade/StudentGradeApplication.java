package com.zhou.studentgrade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.zhou.studentgrade.dao")
@EnableTransactionManagement
public class StudentGradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentGradeApplication.class, args);
    }

}
