package com.zhou.springbootmqttserver.config;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-08-20 10:29
 */
@Component
public class ServerPushCallback implements MqttCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerPushCallback.class);

    @Resource
    private MyMqttServer server;

    @Override
    public void connectionLost(Throwable throwable) {
        LOGGER.error("连接断开，正常尝试重连 -> ", throwable);
        server.startReconnect();
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        LOGGER.info("接收的消息主题: " + s);
        LOGGER.info("接收的消息Qos: " + mqttMessage.getQos());
        LOGGER.info("接收消息内容: " + new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        LOGGER.info("消息发送成功 -> " + iMqttDeliveryToken.isComplete());
    }
}
