package com.zhou.springbootwithshiro.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-12 13:07
 */
@Component
public class ResultMap extends HashMap<String, Object> {

    public ResultMap(){

    }

    public ResultMap success(){
        this.put("code", 200);
        return this;
    }

    public ResultMap success(Integer code){
        this.put("code", code);
        return this;
    }

    public ResultMap fail(){
        this.put("code", 500);
        return this;
    }

    public ResultMap fail(Integer code){
        this.put("code", 500);
        return this;
    }

    public ResultMap messages(String message){
        this.put("messages", message);
        return this;
    }

    public ResultMap data(Object data){
        this.put("data", data);
        return this;
    }
}
