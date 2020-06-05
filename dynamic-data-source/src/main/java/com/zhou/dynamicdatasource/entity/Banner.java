package com.zhou.dynamicdatasource.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * banner图信息
 */
@Data
@TableName("banner")
public class Banner {

        /**
         * banner图code
         */
        @TableId("banner_code")
        private String bannerCode;

        /**
         * banner图标题
         */
        @TableField("banner_title")
        private String bannerTitle;

        /**
         * banner图URL
         */
        @TableField("banner_img_url")
        private String bannerImgUrl;

        /**
         * 排序
         */
        @TableField("sort")
        private Integer sort;

        /**
         * 跳转链接
         */
        @TableField("href_url")
        private String hrefUrl;

        /**
         * 状态(1,开启; 2,关闭)
         */
        @TableField("status")
        private Integer status;

        /**
         * 创建时间
         */
        @TableField("create_time")
        private Date createTime;

        /**
         * 更新时间
         */
        @TableField("update_time")
        private Date updateTime;

}