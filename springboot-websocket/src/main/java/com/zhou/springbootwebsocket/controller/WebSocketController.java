package com.zhou.springbootwebsocket.controller;

import com.zhou.springbootwebsocket.bean.PageDataResult;
import com.zhou.springbootwebsocket.ws.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/8/27 10:18 上午
 */
@RestController
@RequestMapping("/wsController")
public class WebSocketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketController.class);

    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam(value = "message") String message, @RequestParam(value = "sid") String sid){
        try {
            PageDataResult<Map<String, Object>> result = new PageDataResult<>();
            Map<String, Object> map = new HashMap<>();
            map.put("light1", "open");
            map.put("light2", "close");
            result.setData(map);
            result.setSucceed(1);
            result.setMessage(message);
            WebSocketServer.sendInfo(result, sid);
            return "SUCCESS";
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
