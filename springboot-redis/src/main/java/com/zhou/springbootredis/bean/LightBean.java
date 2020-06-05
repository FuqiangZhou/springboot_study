package com.zhou.springbootredis.bean;

import lombok.Data;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-08-14 14:09
 */
@Data
public class LightBean {

    /**
     * 操作类型(open, close)
     */
    private String value1;

    /**
     * 线路开关地址
     */
    private String value2;
}
