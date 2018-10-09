package com.yzm.sso.enums;

import lombok.Getter;

@Getter
public enum ResultEnum implements CodeEnum {
    SUCCESS(0, "成功"),
    FAILED(1, "失败"),
    NOT_ALLOW(2, "未登录"),
    ERROR(3, "服务器异常"),
    TIME_OUT(4, "登录超时"),
    IS_EMPTY(5, "无数据"),
    PASSWORD_ERROR(6, "密码错误"),
    USER_DOES_NOT_EXIST(7,"用户不存在")
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
