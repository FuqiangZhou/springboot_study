package com.zhou.springbootmqtt.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.util.concurrent.TimeUnit;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-08-20 09:29
 */
@Component
public class MyMqttClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyMqttClient.class);

    private static final String TOPIC = "MQTT";

    private static MqttClient client;

    private static MqttConnectOptions options;

    @Value("${mqtt.client.host}")
    public String host;

    @Value("${mqtt.client.client-id}")
    public String clientId;

    @Value("${mqtt.client.username}")
    public String username;

    @Value("${mqtt.client.password}")
    public String password;

    @Resource
    private ClientPushCallback clientPushCallback;

    @PostConstruct
    public void start() {
        try {
            client = new MqttClient(host, clientId, new MemoryPersistence());
            options = new MqttConnectOptions();
           /*TrustManager[] trustAllCerts = new TrustManager[1];
            TrustManager tm = new MyTM();
            trustAllCerts[0] = tm;
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, null);
            SocketFactory factory = sc.getSocketFactory();
            options.setSocketFactory(factory);*/
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(20);
            client.setCallback(clientPushCallback);
            client.connect(options);

            subscribe();
        } catch (Exception e) {
            LOGGER.error("mqtt启动失败 -> ", e);
        }
    }

    /**
     * 功能描述: 消息订阅
     *
     * @author 周富强
     * @date 2019-08-20 09:45
     */
    private static void subscribe() {
        try {
            int[] qos = {0, 0};
            String[] topic = new String[]{"/STATUS/#", "/LWT/#"};
//            String[] topic = {TOPIC};
            client.subscribe(topic, qos);
        } catch (Exception e) {
            LOGGER.error("mqtt订阅消息失败 -> ", e);
        }
    }

    /**
     * 功能描述: 重新连接
     *
     * @author 周富强
     * @date 2019-08-20 09:50
     */
    public synchronized void startReconnect() {
        if (!client.isConnected()) {
            while (!client.isConnected()) {
                LOGGER.info("mqtt开始尝试连接");
                try {
                    TimeUnit.SECONDS.sleep(2);
                    client.connect(options);

                    LOGGER.info("mqtt重连成功");
                    break;
                } catch (Exception e) {
                    LOGGER.error("mqtt重连失败，继续重连中");
                }
            }
        } else {
            LOGGER.info("mqtt已经连接，无需重连");
        }
    }

}
