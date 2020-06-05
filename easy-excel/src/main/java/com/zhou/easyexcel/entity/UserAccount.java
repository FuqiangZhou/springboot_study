package com.zhou.easyexcel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户账户
 */
@Data
@TableName("user_account")
public class UserAccount {

        /**
         * 账户余额
         */
        @TableField("account_balance")
        private Double accountBalance;

        /**
         * 账户code
         */
        @TableId("account_code")
        private String accountCode;

        /**
         * 账户状态(1, 激活; 2, 未激活)
         */
        @TableField("account_status")
        private Integer accountStatus;

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

        /**
         * 用户code
         */
        @TableField("user_code")
        private String userCode;



}