package com.zhou.springbootnetty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/2/17 4:26 下午
 */
@RestController
public class URIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(URIController.class);

    @Value("${count.uri.enable}")
    private boolean countUriEnable;

    @Resource
    private WebApplicationContext applicationContext;

    @GetMapping("/getAllUri")
    public Object getAllUri() {
        if (!countUriEnable) {
            return "401 Not Allowed";
        }
        try {
            RequestMappingHandlerMapping handlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
            List<Map<String, String>> list =  new ArrayList<>();
            for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
                Map<String, String> map = new HashMap<>();
                RequestMappingInfo info = entry.getKey();
                HandlerMethod method = entry.getValue();
                PatternsRequestCondition patternsCondition = info.getPatternsCondition();
                for (String url : patternsCondition.getPatterns()) {
                    map.put("url", url);
                }
                map.put("className", method.getMethod().getDeclaringClass().getName());
                map.put("method", method.getMethod().getName());
                RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
                if (methodsCondition.getMethods().isEmpty()) {
                    continue;
                }
                for (RequestMethod requestMethod  : methodsCondition.getMethods()) {
                    map.put("type", requestMethod.toString());
                }
                list.add(map);
            }
            return list;
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
