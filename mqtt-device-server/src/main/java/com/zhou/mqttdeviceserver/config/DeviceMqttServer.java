package com.zhou.mqttdeviceserver.config;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/8/26 2:05 下午
 */
@Component
public class DeviceMqttServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceMqttServer.class);

    private static MqttClient client;

    private static MqttConnectOptions options;

    private ConcurrentHashMap<String, MqttTopic> topicMap = new ConcurrentHashMap<>();

    @Value("${mqtt.server.host}")
    public String host;

    @Value("${mqtt.server.client-id}")
    public String clientId;

    @Value("${mqtt.server.username}")
    public String username;

    @Value("${mqtt.server.password}")
    public String password;

    @Resource
    private DeviceServerCallback deviceServerCallback;

    @PostConstruct
    public void start(){
        try {
            client = new MqttClient(host, clientId, new MemoryPersistence());

            options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(20);
            client.setCallback(deviceServerCallback);
            client.connect(options);
        }catch (Exception e){
            LOGGER.error("mqtt启动失败 -> ", e);
        }
    }

    public void publish(String topic, byte[] payload){
        try {
            MqttTopic mqttTopic = topicMap.get(topic);
            if (mqttTopic == null){
                topicMap.putIfAbsent(topic, client.getTopic(topic));
            }
            mqttTopic = topicMap.get(topic);
            final MqttMessage message = new MqttMessage(payload);
            message.setQos(0);
            MqttDeliveryToken token = mqttTopic.publish(message);
            token.waitForCompletion();
            if (token.isComplete()){
                LOGGER.info("mqtt发布消息成功: messageId:{}", token.getMessageId());
            }else {
                LOGGER.info("mqtt发布消息失败: messageId:{}", token.getMessageId());
            }
        }catch (Exception e){
            LOGGER.error("mqtt发布消息失败 -> ", e);
        }
    }

    /**
     * 功能描述: 重新连接
     * @author 周富强
     * @date 2019-08-20 09:50
     */
    public synchronized void startReconnect(){
        if (!client.isConnected()){
            while (!client.isConnected()){
                LOGGER.info("mqtt开始尝试连接");
                try {
                    TimeUnit.SECONDS.sleep(2);
                    client.connect(options);
                    LOGGER.info("mqtt重连成功");
                    break;
                }catch (Exception e){
                    LOGGER.error("mqtt重连失败，继续重连中");
                }
            }
        }else {
            LOGGER.info("mqtt已经连接，无需重连");
        }
    }



}
