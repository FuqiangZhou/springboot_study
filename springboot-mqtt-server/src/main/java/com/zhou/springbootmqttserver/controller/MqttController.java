package com.zhou.springbootmqttserver.controller;

import com.zhou.springbootmqttserver.config.MyMqttServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-08-20 11:31
 */
@RestController
@RequestMapping("/mqtt")
public class MqttController {

//    private static final String TOPIC = "/00C57A71_CONTROLLER/CTRL_LED";
//    private static final String TOPIC = "/CTRL_LED/00C578ED_CONTROLLER";

    private static final String TOPIC = "/CTRL_LED/00C57A25_CONTROLLER";

    private static final String[] TOPIC_ARR = {"/CTRL_LED/00C57D63_CONTROLLER", "/CTRL_LED/00C57A76_CONTROLLER", "/CTRL_LED/00C57129_CONTROLLER", "/CTRL_LED/00C573B7_CONTROLLER"};
//    private static final String TOPIC = "MQTT";

    @Resource
    private MyMqttServer myMqttServer;

    @GetMapping("/sendMsg")
    public String pushMsg(@RequestParam(value = "lightId") Integer lightId,
                          @RequestParam(value = "status") Integer status,
                          @RequestParam(value = "deviceNum", defaultValue = "0") Integer deviceNum){
        byte[] led_control = new byte[]{lightId.byteValue(), status.byteValue()};
        if (deviceNum > TOPIC_ARR.length - 1 || deviceNum < 0){
            return "参数错误";
        }
        this.myMqttServer.publish(TOPIC_ARR[deviceNum], led_control);
        return "发送成功";
    }

    @GetMapping("/testMqtt")
    public String testMqtt(@RequestParam(value = "lightId") Integer lightId,
                           @RequestParam(value = "status") Integer status){
        byte[] led_control = new byte[]{lightId.byteValue(), status.byteValue()};
        this.myMqttServer.publish(TOPIC, led_control);
        return "发送成功";
    }

    @GetMapping("/openLight")
    public String openLight(){
        byte[] led_control = new byte[]{0x00, 0x01};
        this.myMqttServer.publish(TOPIC, led_control);
        return "发送成功";
    }

    @GetMapping("/closeLight")
    public String closeLight(){
        byte[] led_control = new byte[]{0x00, 0x00};
        this.myMqttServer.publish(TOPIC, led_control);
        return "发送成功";
    }

    @GetMapping("/initDeviceOpen")
    public String initDeviceOpen(@RequestParam(value = "deviceNum") Integer deviceNum, @RequestParam(value = "status") Integer status){
        if (deviceNum > TOPIC_ARR.length - 1 || deviceNum < 0){
            return "参数错误";
        }
        for (int i = 0; i < 20; i ++){
            this.myMqttServer.publish(TOPIC_ARR[deviceNum], new byte[]{(byte)i, status.byteValue()});
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "SUCCESS";
    }

}
