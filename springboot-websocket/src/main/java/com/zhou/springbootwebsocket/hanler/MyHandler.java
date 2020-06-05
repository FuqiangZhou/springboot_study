package com.zhou.springbootwebsocket.hanler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/18 11:11 上午
 */
//@Service
public class MyHandler implements WebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyHandler.class);

    private final static List<WebSocketSession> SESSIONS = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        LOGGER.info("===============连接成功===============");
        SESSIONS.add(webSocketSession);
        String username = (String) webSocketSession.getAttributes().get("WEBSOCKET_USERNAME");
        if (username != null){
            JSONObject obj = new JSONObject();
            obj.put("count", SESSIONS.size());
            users(obj);
            webSocketSession.sendMessage(new TextMessage(obj.toJSONString()));
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        LOGGER.info("===============处理发送的信息===============");
        JSONObject msg = JSON.parseObject(webSocketMessage.getPayload().toString());
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private void users(JSONObject obj){
        List<String> usernames = new ArrayList<>();
        for (WebSocketSession session : SESSIONS) {
            usernames.add((String) session.getAttributes().get("WEBSOCKET_USERNAME"));
        }
        obj.put("users", usernames);
    }
}
