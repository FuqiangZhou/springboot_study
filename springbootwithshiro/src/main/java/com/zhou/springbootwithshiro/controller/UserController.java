package com.zhou.springbootwithshiro.controller;

import com.zhou.springbootwithshiro.model.ResultMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-12 14:10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private ResultMap resultMap;

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap getMessage() {
        return resultMap.success().messages("您拥有用户权限，可以获得该接口的信息！");
    }
}
