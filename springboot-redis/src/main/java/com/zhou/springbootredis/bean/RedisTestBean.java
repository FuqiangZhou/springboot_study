package com.zhou.springbootredis.bean;

import lombok.Data;

import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-07-02 18:54
 */
@Data
public class RedisTestBean {

    private String code;

    private String name;

    private List<String> list;
}
