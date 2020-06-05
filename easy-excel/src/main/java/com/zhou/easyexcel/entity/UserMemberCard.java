package com.zhou.easyexcel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户会员卡信息
 */
@Data
@TableName("user_member_card")
public class UserMemberCard {

        /**
         * 会员卡类型(1,储值卡; 2,畅想卡; 3,体验卡)
         */
        @TableField("card_type")
        private Integer cardType;

        /**
         * 创建时间
         */
        @TableField("create_time")
        private Date createTime;

        /**
         * 状态(1,可用; 2,不可用)
         */
        @TableField("status")
        private Integer status;

        /**
         * 更新时间
         */
        @TableField("update_time")
        private Date updateTime;

        /**
         * 用户code
         */
        @TableField("user_code")
        private String userCode;

        /**
         * 用户会员卡code
         */
        @TableId("user_member_card_code")
        private String userMemberCardCode;

        /**
         * 到期时间
         */
        @TableField("validity_period")
        private Date validityPeriod;

        /**
         * 是否被使用(1,已使用; 2,未使用)
         */
        @TableField("is_used")
        private Integer isUsed;

        /**
         * 使用时间
         */
        @TableField("used_time")
        private Date usedTime;
}