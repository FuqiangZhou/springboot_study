package com.zhou.springbootwithmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/14 3:40 下午
 */
@Data
@TableName("menu")
public class Menu {

    @TableId("id")
    private String id;

    @TableField("name")
    private String name;

    @TableField("parent_id")
    private String parentId;

    @TableField(exist = false)
    private List<Menu> menus;
}
