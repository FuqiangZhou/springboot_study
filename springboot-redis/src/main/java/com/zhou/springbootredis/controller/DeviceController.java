package com.zhou.springbootredis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhou.springbootredis.bean.ControlBean;
import com.zhou.springbootredis.bean.LightBean;
import com.zhou.springbootredis.bean.PostBean;
import com.zhou.springbootredis.bean.RetBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-08-14 14:02
 */
@RestController
@RequestMapping("/data")
public class DeviceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceController.class);

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 功能描述: 提交命令
     *
     * @param value1 命令
     * @return
     * @author 周富强
     * @date 2019-08-14 14:19
     */
    @GetMapping(value = "/submitCmd")
    public String submitCmd(@RequestParam(value = "value1") Integer value1) {
        try {
            if (value1 == 1) {
                this.redisTemplate.opsForValue().set("cmd", "open");
            } else {
                this.redisTemplate.opsForValue().set("cmd", "close");
            }
            return "成功";
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return "异常";
        }
    }

    @PostMapping(value = "/carry")
    public void carry(HttpServletRequest request, HttpServletResponse response) {
        String mac = request.getParameter("mac");
        System.out.println(mac);
        RetBean retBean = new RetBean();
        OutputStream out = null;
        try {
            response.setHeader("Connection", "close");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "No-cache");
            response.setContentType("application/json;charset=UTF-8");
            out = new BufferedOutputStream(response.getOutputStream());
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String responseStr = "{\"ret\":\"success\",\"control\":{\"cmd\":\"OCSWITCH\",\"id\":null,\"mac\":\"" + mac + "\",\"run\":false,\"time\":\"" + date + "\",\"value\":\"{\"value1\":\"close\",\"value2\":\"1\"}\"}}";
            System.out.println(responseStr);
            out.write(responseStr.getBytes());
            out.flush();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            retBean.setRet("failure");
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
