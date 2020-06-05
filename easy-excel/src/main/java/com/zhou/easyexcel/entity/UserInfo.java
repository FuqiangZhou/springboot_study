package com.zhou.easyexcel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 店铺信息
 */
@Data
@TableName("user_info")
public class UserInfo {

        /**
         * 创建时间
         */
        @TableField("create_time")
        private Date createTime;

        /**
         * 用户头像(用户头像地址URL)
         */
        @TableField("hend_img_url")
        private String hendImgUrl;

        /**
         * 邀请码
         */
        @TableField("invitation_code")
        private String invitationCode;

        /**
         * 用户昵称
         */
        @TableField("nickname")
        private String nickname;

        /**
         * 问卷项目
         */
        @TableField("questionnaire_item")
        private String questionnaireItem;

        /**
         * 更新时间
         */
        @TableField("update_time")
        private Date updateTime;

        /**
         * 用户code
         */
        @TableId("user_code")
        private String userCode;

        /**
         * 用户手机号
         */
        @TableField("user_phone")
        private String userPhone;

        /**
         * 微信openId
         */
        @TableField("wx_open_id")
        private String wxOpenId;

        /**
         * 微信unionId
         */
        @TableField("wx_union_id")
        private String wxUnionId;

}