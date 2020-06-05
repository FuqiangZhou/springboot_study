package com.zhou.mqttdeviceserver.task;

import com.zhou.mqttdeviceserver.config.DeviceMqttServer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/8/26 2:19 下午
 */
@Component
@Async
public class MqttTask {

    @Resource
    private DeviceMqttServer server;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Scheduled(cron = "0/5 * * * * ?")
    public void pushStatus(){
        String[] topicArr = new String[]{"/STATUS/MY00C57A71_CONTROLLER", "/STATUS/MY00C57A72_CONTROLLER", "/STATUS/MY00C57A73_CONTROLLER"};
        for (String topic : topicArr){
            Object res = this.redisTemplate.opsForValue().get(topic.substring(topic.lastIndexOf("/") + 1));
            if (res != null){
                this.server.publish(topic, String.valueOf(res).getBytes());
            }
        }

    }
}
