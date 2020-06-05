package com.zhou.devicecontrolserver.vo;

import lombok.Data;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/27 2:30 下午
 */
@Data
public class DeviceInfoVO {

    /**
     * 设备ID
     */
    private Integer deviceId;

    /**
     * 设备上下线信息
     */
    private Integer status;

}
