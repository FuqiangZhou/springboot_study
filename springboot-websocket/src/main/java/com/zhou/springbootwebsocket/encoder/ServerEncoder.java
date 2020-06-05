package com.zhou.springbootwebsocket.encoder;

import com.alibaba.fastjson.JSON;
import com.zhou.springbootwebsocket.bean.PageDataResult;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/21 4:06 下午
 */
public class ServerEncoder<T> implements Encoder.Text<PageDataResult<T>> {

    @Override
    public String encode(PageDataResult<T> pageDataResult) throws EncodeException {
        return JSON.toJSONString(pageDataResult);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
