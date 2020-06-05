package com.zhou.devicecontrolserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/27 1:23 下午
 */
@Data
@TableName("device_info")
public class DeviceInfo {

    /**
     * 设备id
     */
    @TableId("device_id")
    private Integer deviceId;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
}
