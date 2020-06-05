package com.zhou.springbootwithmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-15 21:19
 */
@Data
@TableName("learn_unti_code")
public class LearnUntiCode {

    @TableId("ver_vol_code")
    private String verVolCode;

    @TableField("volume_name")
    private String volumeName;

    @TableField("word_spell")
    private String wordSpell;

    @TableField("word_sort")
    private Integer wordSort;

    @TableField("is_experience")
    private Integer isExperience;

    private Integer status;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    private Integer ext1;

    private Integer ext2;

    private String ext3;

    private String ext4;
}
