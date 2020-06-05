package com.zhou.studentgrade.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/3/10 5:28 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;

    private String resultMsg;

    private T data;

    public CommonResult(Integer code, String resultMsg) {
        this(code, resultMsg, null);
    }
}
