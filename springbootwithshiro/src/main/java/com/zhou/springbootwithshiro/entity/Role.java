package com.zhou.springbootwithshiro.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhou.springbootwithshiro.annotation.CheckDateFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-12 09:56
 */
@Data
@Builder
@TableName("role")
public class Role {

    @TableId("id")
    @ApiModelProperty(name = "id", value = "角色ID")
    @NotEmpty(message = "id不能为空")
    private String id;

    @TableField("name")
    @ApiModelProperty(name = "id", value = "角色名称")
    @NotEmpty(message = "name不能为空")
    private String name;

    @CheckDateFormat(message = "时间不符合规范")
    private String date;
}
