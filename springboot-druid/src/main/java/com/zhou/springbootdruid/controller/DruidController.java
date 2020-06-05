package com.zhou.springbootdruid.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/4 4:36 下午
 */
@RestController
//@RequestMapping("/druid")
public class DruidController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DruidController.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/druidData")
    public String druidData() {
        String sql = "SELECT COUNT(*) FROM user_role";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        LOGGER.info("count=={}", count);
        return "SUCCESS";
    }
}
