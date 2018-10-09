package com.yzm.sso.constant;

/**
 * redis常量
 */
public interface RedisConstant {

    String TOKEN_PREFIX = "token_%s";

    Integer EXPIRE = 1800; //半小时
}
