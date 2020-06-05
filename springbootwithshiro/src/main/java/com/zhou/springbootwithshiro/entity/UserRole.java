package com.zhou.springbootwithshiro.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-12 09:59
 */
@Data
@Builder
@TableName("user_role")
public class UserRole {

    @TableId("id")
    private String id;

    @TableField("user_id")
    private String userId;

    @TableField("role_id")
    private String roleId;
}
