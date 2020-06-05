package com.zhou.springbootwithshiro.controller;

import com.zhou.springbootwithshiro.model.ResultMap;
import org.apache.shiro.authc.AccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-12 13:57
 */
@RestControllerAdvice
public class ExceptionController {

    @Resource
    private ResultMap resultMap;

    @ExceptionHandler(AccountException.class)
    public ResultMap handlerShiroException(Exception e){
        return resultMap.fail().messages(e.getMessage());
    }
}
