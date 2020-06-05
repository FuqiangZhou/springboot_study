package com.zhou.springbootwithshiro.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-12 10:09
 */
@Data
@Builder
@TableName("role_permission")
public class RolePermission {

    @TableId("id")
    private String id;

    @TableField("role_id")
    private String roleId;

    @TableField("permission_id")
    private String permissionId;
}
