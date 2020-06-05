package com.zhou.springbootnetty.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zhou.springbootnetty.server.MyContext;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/26 3:22 下午
 */
@RestController
@RequestMapping("/netty")
public class NettyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyController.class);

    @PostMapping("/nettyInfo")
    public String nettyInfo(HttpServletRequest request, HttpServletResponse response){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                stringBuilder.append(line);
            }
            LOGGER.info("请求数据 = {}", stringBuilder.toString());
            Map<String, Object> map = JSON.parseObject(stringBuilder.toString(), new TypeReference<Map<String, Object>>() {
            });
            System.out.println(map);
            ChannelHandlerContext ctx = MyContext.getContext((Integer) map.get("id"));
            Map<String, String> resMap = new HashMap<>();
            if (ctx != null){
                MyContext.writeMessage(map.get("msg").toString().getBytes(), ctx);
                resMap.put("code", "200");
                resMap.put("result", "SUCCESS");
            }else {
                resMap.put("code", "404");
                resMap.put("result", "NOT FOUND CHANNEL");
            }
            return JSON.toJSONString(resMap);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
