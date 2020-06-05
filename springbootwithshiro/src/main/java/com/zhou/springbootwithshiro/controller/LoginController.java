package com.zhou.springbootwithshiro.controller;

import com.zhou.springbootwithshiro.model.ResultMap;
import com.zhou.springbootwithshiro.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-12 13:36
 */
@RestController
@Api(value = "登陆模块", tags = {"登陆模块"})
public class LoginController {

    @Autowired
    private ResultMap resultMap;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public ResultMap notLogin() {
        return resultMap.success().messages("您尚未登陆！");
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public ResultMap notRole() {
        return resultMap.success().messages("您没有权限！");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResultMap logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return resultMap.success().messages("成功注销！");
    }

    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultMap login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        Set<String> roles = userService.getRolesByName(username);
        if (roles.contains("user")) {
            return resultMap.success().messages("欢迎登陆");
        }
        if (roles.contains("admin")) {
            return resultMap.success().messages("欢迎来到管理员页面");
        }
        return resultMap.fail().messages("权限错误！");
    }
}
