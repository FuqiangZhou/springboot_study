package com.zhou.springbootwithshiro.handler;

import com.zhou.springbootwithshiro.model.ResponseData;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-07-28 11:29
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseData<List> MethodArgumentNotValidHandler(MethodArgumentNotValidException ex){
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        List<String> resList = allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseData<>(400, resList, "参数错误");
    }
}
