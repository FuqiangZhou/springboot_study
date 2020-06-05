package com.zhou.springbootwebsocket.hanler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/18 10:55 上午
 */
//@Service
public class MyHandshake implements HandshakeInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyHandshake.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (serverHttpRequest instanceof ServletServerHttpRequest){
            LOGGER.info("===============握手之前===============");
            HttpServletRequest request = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
            String username = (String) request.getSession().getAttribute("WEBSOCKET_USERNAME");
            map.put("WEBSOCKET_USERNAME", username);
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        LOGGER.info("===============握手之后===============");
    }
}
