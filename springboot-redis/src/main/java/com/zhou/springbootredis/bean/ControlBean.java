package com.zhou.springbootredis.bean;

import lombok.Data;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-08-14 14:03
 */
@Data
public class ControlBean<T> {

    /**
     * 命令
     */
    private String cmd;

     /**
     * id
     */
    private String id;

    /**
     * mac地址
     */
    private String mac;

    /**
     * run
     */
    private Boolean run;

    /**
     * 时间(yyyy-MM-dd HH:mm:ss)
     */
    private String time;

    /**
     * 值
     */
    private T value;
}
