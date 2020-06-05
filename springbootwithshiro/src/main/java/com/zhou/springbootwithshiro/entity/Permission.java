package com.zhou.springbootwithshiro.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-12 09:57
 */
@Data
@Builder
@TableName("permission")
public class Permission {

    @TableId("id")
    private String id;

    @TableField("name")
    private String name;

    @TableField("url")
    private String url;
}
