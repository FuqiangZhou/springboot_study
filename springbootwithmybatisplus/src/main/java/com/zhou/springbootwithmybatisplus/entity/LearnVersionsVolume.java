package com.zhou.springbootwithmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-15 21:08
 */
@Data
@Builder
@TableName("learn_versions_volume")
public class LearnVersionsVolume {

    @TableId("ver_vol_code")
    private String verVolCode;

    @TableField("volume_name")
    private String volumeName;

    @TableField("parent_code")
    private String parentCode;

    private Integer status;

    private Integer type;

    @TableField("volume_type")
    private Integer volumeType;

    @TableField("create_time")
    private Date createTime;

    @TableField("image_url")
    private String imageUrl;

    @TableField("version_type")
    private Integer versionType;

    private String operator;

    @TableField("update_time")
    private Date updateTime;

    private Integer ext1;

    private Integer ext2;

    private String ext3;

    private String ext4;
}
