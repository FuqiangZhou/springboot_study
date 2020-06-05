package com.zhou.springbootwithshiro.controller;

import com.zhou.springbootwithshiro.annotation.CheckDateFormat;
import com.zhou.springbootwithshiro.entity.Role;
import com.zhou.springbootwithshiro.entity.User;
import com.zhou.springbootwithshiro.model.ResponseData;
import com.zhou.springbootwithshiro.model.ResultMap;
import com.zhou.springbootwithshiro.model.ReturnMap;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-12 12:02
 */
@RestController
@RequestMapping("/guest")
public class GuestController {

    @Resource
    private ResultMap resultMap;


    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public ResultMap login() {
        return resultMap.success().messages("欢迎进入，您的身份是游客");
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap submitLogin() {
        return new ResultMap().success().messages("您拥有获得该接口的信息的权限！");
    }

    @ApiOperation(value = "post提交测试1", notes = "post提交测试", response = User.class)
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User")
    @RequestMapping(value = "/postDemo1", method = RequestMethod.POST)
    public ResultMap postDemo1(@RequestBody User user) {
        return new ResultMap().success().data(user);
    }

    @ApiOperation(value = "post提交测试2", notes = "post提交测试(负责人: 周富强)")
    @ApiImplicitParam(name = "role", value = "用户信息", required = true, dataType = "Role")
    @RequestMapping(value = "/postDemo2", method = RequestMethod.POST)
    public ResponseData<Role> postDemo2(@RequestBody @Validated Role role) {
        return new ResponseData<>(200, role, "abc");
    }

    @ApiOperation(value = "集合参数测试", notes = "集合参数测试(负责人: 周富强)")
    @RequestMapping(value = "/listDemo", method = RequestMethod.GET)
    public ResponseData<List<String>> listDemo(@RequestParam(value = "role") List<String> role) {
        ResponseData<List<String>> data = new ResponseData<>();
        data.setCode(200);
        data.setData(role);
        data.setMessage("abc");
        return data;
    }


    @ApiOperation(value = "post提交测试4", notes = "post提交测试(负责人: 周富强)")
    @RequestMapping(value = "/postDemo4", method = RequestMethod.GET)
    public ReturnMap<Integer, String, List<String>> postDemo3(@RequestParam(value = "role") List<String> role) {
        return new ReturnMap<Integer, String, List<String>>().success(200).message("").data(role);
    }

    @GetMapping("/testDate")
    @ApiOperation(value = "自定义参数时间参数校验", notes = "测试自定义参数时间参数校验")
    @ApiImplicitParam(name = "date", value = "日期时间")
    public String testDate(@RequestParam(value = "date") String date){
        return date;
    }

}
