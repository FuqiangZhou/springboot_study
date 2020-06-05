package com.zhou.springbootredis.bean;

import lombok.Data;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-08-14 14:20
 */
@Data
public class PostBean {

    private String mac;

    private String cmd;

    private String content;

    private String key;

}
