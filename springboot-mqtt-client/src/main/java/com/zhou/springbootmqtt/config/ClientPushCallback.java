package com.zhou.springbootmqtt.config;

import com.zhou.springbootmqtt.ws.WebSocketServer;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-08-20 09:33
 */
@Component
public class ClientPushCallback implements MqttCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientPushCallback.class);

    @Resource
    private MyMqttClient client;

    @Override
    public void connectionLost(Throwable throwable) {
        LOGGER.error("连接断开，正常尝试重连 -> ", throwable);
        client.startReconnect();
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        LOGGER.info("接收的消息主题: " + s);
        LOGGER.info("接收的消息Qos: " + mqttMessage.getQos());
        LOGGER.info("接收消息内容: " + new String(mqttMessage.getPayload()));
        String res = new String(mqttMessage.getPayload());
//        System.out.println(res);
        if ("STATUS".equals(s.substring(1, s.lastIndexOf("/")))){
            printLightStatus(res, s.substring(s.lastIndexOf("/") + 1));
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        LOGGER.info("消息发送成功 -> " + iMqttDeliveryToken.isComplete());
    }

    private void printLightStatus(String res, String sid){
        String trim = res.substring(res.lastIndexOf(",") + 1).trim();
        String[] split = trim.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = split.length; i > 0; i--){
            stringBuilder.append(split[i - 1].substring(split[i - 1].lastIndexOf("x") + 1));
        }
        if (stringBuilder.toString().length() == 6){
            int i1 = Integer.parseInt(stringBuilder.toString(), 16);
            String s = Integer.toBinaryString(i1);
            char[] chars = new char[24];
            Arrays.fill(chars, '0');
            char[] array = s.toCharArray();
            int length = array.length;
            int length1 = chars.length;
            for (int i = length; i > 0 ; i--){
                chars[length1 - 1] = array[i - 1];
                length1 --;
            }
            int light = 0;
            StringBuilder builder = new StringBuilder("<ol>");
            for (int i = 24; i > 4; i--) {
                builder.append("<li>[").append(light).append("] => ");
                if ('1' == chars[i - 1]) {
                    builder.append("<span style=\"color:green\">开启<span>");
                } else {
                    builder.append("<span style=\"color:red\">关闭<span>");
                }
                light ++;
            }
            builder.append("</ol>");
            try {
                WebSocketServer.sendInfo(builder.toString(), sid);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
