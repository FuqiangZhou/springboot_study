package com.zhou.dynamicdatasource.util;

import java.util.ArrayList;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/9/20 11:15 上午
 */
public class PageDataResult<T> {

    /**
     * 最大页数
     */
    private long total;

    /**
     * 1 成功 2 失败
     */
    private long succeed;

    /**
     * 存放查询出来的结果
     */
    private T data;

    /**
     * 返回信息
     */
    private String message;


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSucceed() {
        return succeed;
    }

    public void setSucceed(long succeed) {
        this.succeed = succeed;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PageDataResult(long succeed, T data, String message) {
        super();
        this.succeed = succeed;
        this.data = data;
        this.message = message;
    }

    public PageDataResult() {
        super();
    }

    public PageDataResult(long total, long succeed, T data, String message) {
        this.total = total;
        this.succeed = succeed;
        this.data = data;
        this.message = message;
    }

    @SuppressWarnings("unchecked")
    public PageDataResult(long succeed, String message){
        super();
        this.total = 0;
        this.succeed = succeed;
        this.message = message;
        this.data = (T) new ArrayList<>();
    }
}
