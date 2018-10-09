package com.yzm.sso.exception;


import com.yzm.sso.enums.ResultEnum;

public class CustomException extends RuntimeException {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public CustomException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
    public CustomException(Integer code, String msg){
        super(msg);
        this.code=code;
    }
}
