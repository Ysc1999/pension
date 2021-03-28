package com.graduation.ylservice.utils.exception;

import com.graduation.ylservice.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.reflections.Reflections.log;

@ControllerAdvice
public class GlobalExceptionHandler {

    //自定义异常处理类
    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public R error(GlobalException e) {
        e.printStackTrace();
        return R.error(e.getCode(),e.getMsg());
    }
}
