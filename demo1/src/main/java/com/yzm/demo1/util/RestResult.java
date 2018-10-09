package com.yzm.demo1.util;

import lombok.Data;

@Data
public class RestResult<T> {

    private Integer status;

    private String msg;

    private T data;

    private Boolean success;

}
