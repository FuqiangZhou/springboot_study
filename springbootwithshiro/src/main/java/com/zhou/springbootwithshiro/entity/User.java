package com.zhou.springbootwithshiro.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-12 09:54
 */
@Data
@Builder
@TableName("user")
public class User {

    @TableId("id")
    @ApiModelProperty(name = "id", value = "用户ID")
    private String id;

    @TableField("name")
    @ApiModelProperty(name = "name", value = "用户名")
    private String name;

    @TableField("password")
    @ApiModelProperty(name = "password", value = "密码")
    private String password;

}
