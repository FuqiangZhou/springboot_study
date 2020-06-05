package com.zhou.springbootsecurity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/2/12 5:41 下午
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {

    /**
     * 用户ID
     */
    @TableId("user_id")
    private Integer userId;

    /**
     * 权限ID
     */
    @TableId("role_id")
    private Integer roleId;
}
