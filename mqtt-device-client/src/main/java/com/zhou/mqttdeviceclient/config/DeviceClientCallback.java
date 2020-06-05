package com.zhou.mqttdeviceclient.config;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/8/26 1:58 下午
 */
@Component
public class DeviceClientCallback implements MqttCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceClientCallback.class);

    @Resource
    private DeviceMqttClient client;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;


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
        s = s.substring(s.lastIndexOf("/") + 1);
        String statusStr = statusStr(mqttMessage.getPayload(), s);
        this.redisTemplate.opsForValue().set(s, statusStr);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        LOGGER.info("消息发送成功 -> " + iMqttDeliveryToken.isComplete());
    }

    /**
     * 功能描述: 组装灯的状态
     *
     * @param ledControl 控灯数据
     * @param key key
     * @return
     * @author 周富强
     * @date 2019/8/26 2:37 下午
     */
    private String statusStr(byte[] ledControl, String key) {

        Object obj = this.redisTemplate.opsForValue().get(key);
        char[] chars;
        if (obj == null){
            chars = new char[24];
            Arrays.fill(chars, '0');
        }else {
            String res = (String) obj;
            String trim = res.substring(res.lastIndexOf(",") + 1).trim();
            String[] split = trim.split(" ");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = split.length; i > 0; i--){
                stringBuilder.append(split[i - 1].substring(split[i - 1].lastIndexOf("x") + 1));
            }
            int i1 = Integer.parseInt(stringBuilder.toString(), 16);
            String s = Integer.toBinaryString(i1);
            char[] array = s.toCharArray();
            chars = new char[24];
            Arrays.fill(chars, '0');
            int length = array.length;
            int length1 = chars.length;
            for (int i = length; i > 0 ; i--){
                chars[length1 - 1] = array[i - 1];
                length1 --;
            }
        }

        String[] status = new String[]{"P[0]=0x", "P[1]=0x", "P[2]=0x"};
        if (ledControl[1] == 0x01) {
            chars[chars.length - 1 - ledControl[0]] = '1';
        }
        if (ledControl[1] == 0x00) {
            chars[chars.length - 1 - ledControl[0]] = '0';
        }
        StringBuilder stringBuilder = new StringBuilder("T=" + new Date() + ",t=1024s,R=0, ");
        for (int i = 0; i < status.length; i++) {
            String p = getString(i, chars);
            status[i] = status[i] + p;
            if (i == status.length - 1) {
                stringBuilder.append(status[i]);
            } else {
                stringBuilder.append(status[i]).append(" ");
            }
        }
        return stringBuilder.toString();
    }

    private String getString(int i, char[] chars) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = (2 - i) * 8; index < chars.length - (i * 8); index++) {
            stringBuilder.append(chars[index]);
        }
        String p = Integer.toHexString(Integer.parseInt(stringBuilder.toString(), 2));
        if (p.length() < 2) {
            p = "0" + p;
        }
        return p;
    }
}
