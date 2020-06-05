package com.zhou.devicecontrolserver.vo;

import lombok.Data;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/27 2:35 下午
 */
@Data
public class CmdExecResVO {

    /**
     * 指令ID
     */
    private String id;

    /**
     * 结果
     */
    private Integer res;

    /**
     * 重试次数
     */
    private Integer retryNum;
}
