package com.zhou.springbootsecurity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/2/12 5:37 下午
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {

    /**
     * ID
     */
    @TableId("id")
    private Integer id;

    /**
     * 用户名
     */
    @TableField("name")
    private String name;

    /**
     * 密码
     */
    @TableField("password")
    private String password;
}
