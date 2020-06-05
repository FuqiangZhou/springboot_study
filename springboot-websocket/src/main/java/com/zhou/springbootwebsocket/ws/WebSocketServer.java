package com.zhou.springbootwebsocket.ws;

import com.zhou.springbootwebsocket.bean.PageDataResult;
import com.zhou.springbootwebsocket.encoder.ServerEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/8/27 10:04 上午
 */
@ServerEndpoint(value = "/websocket/{sid}", encoders = {ServerEncoder.class})
@Component
public class WebSocketServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);

    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    private static List<String> sidList = Collections.synchronizedList(new ArrayList<>());

    private static ConcurrentMap<String, Session> sessionConcurrentHashMap = new ConcurrentHashMap<>();

    private Session session;

    private String sid = "";

    private static boolean loginStatus = false;

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        loginStatus = true;
        if (!sidList.contains(sid)) {
            webSocketSet.add(this);
            sessionConcurrentHashMap.putIfAbsent(sid, session);
            addOnlineCount();
            LOGGER.info("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());
            this.sid = sid;
            sidList.add(sid);
            session.getAsyncRemote().sendObject(new PageDataResult<>(1, "连接成功"));
        } else {
            LOGGER.info("当前监听未发生变化:" + sid + ",当前在线人数为" + getOnlineCount());
        }
    }

    @OnClose
    public void onClose() {
        LOGGER.info("{}下线", this.sid);
        int i = 0;
        if (sidList.contains(this.sid)) {
            sidList.remove(this.sid);
            webSocketSet.remove(this);
            sessionConcurrentHashMap.remove(this.sid);
            subOnlineCount();
            LOGGER.info("==========修改登陆状态==========");
        }
        LOGGER.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        LOGGER.info("收到来自窗口" + sid + "的信息:" + message);
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        LOGGER.error("发生错误");
        error.printStackTrace();
    }

    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        LOGGER.info("推送消息到窗口" + sid + "，推送内容:" + message);
        Session session = sessionConcurrentHashMap.get(sid);
        if (session == null){
            //添加到数据库
        }else {
            session.getBasicRemote().sendText(message);
        }
    }

    public static void sendInfo(Object message, @PathParam("sid") String sid) {
        LOGGER.info("推送消息到窗口" + sid + "，推送内容:" + message);
        Session session = sessionConcurrentHashMap.get(sid);
        if (session == null){
            //添加到数据库
        }else {
            session.getAsyncRemote().sendObject(message);
        }
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }


}
