package com.zhou.springbootwithshiro.model;

import java.util.HashMap;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-07-13 11:16
 */
public class ReturnMap<C, M, D> extends HashMap<String, Object> {

    public ReturnMap(){}

    public <C> ReturnMap success(C code){
        this.put("code", code);
        return this;
    }

    public <M> ReturnMap message(M message){
        this.put("messages", message);
        return this;
    }

    public <D> ReturnMap data(D data){
        this.put("data", data);
        return this;
    }

}
