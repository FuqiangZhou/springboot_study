package com.zhou.devicecontrolserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/27 1:19 下午
 */
@Data
@TableName("cmd_info")
public class CmdInfo {

    /**
     * 指令id
     */
    @TableId("id")
    private String id;

    /**
     * 设备id
     */
    @TableField("device_id")
    private Integer deviceId;

    /**
     * 设备端口
     */
    @TableField("device_port")
    private Integer devicePort;
    
    /**
     * 指令(1,开灯; 0,关灯)
     */
    @TableField("execute_cmd")
    private Integer executeCmd;

    /**
     * 执行结果
     */
    @TableField("execute_res")
    private Integer executeRes;

    /**
     * 重试次数
     */
    @TableField("retry_num")
    private Integer retryNum;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 回执时间
     */
    @TableField("res_time")
    private Date resTime;
}
