package com.zhou.dynamicdatasource.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/10/30 11:22 上午
 */
@Data
@TableName("db_config")
public class DbConfigEntity {

    @TableId("id")
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("url")
    private String url;

    @TableField("driver")
    private String driver;
}
