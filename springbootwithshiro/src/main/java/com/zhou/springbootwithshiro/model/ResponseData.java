package com.zhou.springbootwithshiro.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-07-09 13:42
 */
@Data
public class ResponseData<T> {

    @ApiModelProperty(name = "code", value = "状态码")
    private Integer code;

    @ApiModelProperty(name = "data", value = "返回的数据体")
    private T data;

    @ApiModelProperty(name = "message", value = "返回的信息")
    private String message;

    public ResponseData() {
    }

    public ResponseData(Integer code, T data, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    public ResponseData(Integer code, String message){
        this.code = code;
        this.message = message;
        this.data = (T) new ArrayList<T>();
    }

}
