package com.zhou.devicecontrolserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/2 9:51 上午
 */
@Data
@TableName("device_record")
public class DeviceRecord {

    /**
     * 记录id
     */
    @TableId("id")
    private String id;

    /**
     * 设备ID
     */
    @TableField("device_id")
    private Integer deviceId;

    /**
     * 记录类型(1,上线; 0,掉线)
     */
    @TableField("record_type")
    private Integer recordType;

    /**
     * 记录时间
     */
    @TableField("record_time")
    private Date recordTime;
}
