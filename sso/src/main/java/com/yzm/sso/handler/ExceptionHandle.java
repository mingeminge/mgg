package com.yzm.sso.handler;


import com.yzm.sso.exception.CustomException;
import com.yzm.sso.VO.RestResult;
import com.yzm.sso.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(CustomException.class)
    public RestResult myException(CustomException e) {
        return ResultUtil.fail(e.getCode(), e.getMessage());
    }


}
