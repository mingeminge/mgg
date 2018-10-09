package com.yzm.sso.utils;

import com.yzm.sso.VO.RestResult;
import com.yzm.sso.enums.ResultEnum;

public class ResultUtil {

    public static RestResult fail(Integer status, String msg) {
        RestResult result = new RestResult();
        result.setSuccess(false);
        result.setStatus(status);
        result.setMsg(msg);
        return result;
    }

    public static RestResult fail(ResultEnum resultEnum) {
        RestResult result = new RestResult();
        result.setSuccess(false);
        result.setStatus(resultEnum.getCode());
        result.setMsg(resultEnum.getMessage());
        return result;
    }

    public static RestResult success() {
        RestResult result = new RestResult();
        result.setSuccess(true);
        result.setStatus(0);
        return result;
    }

    public static RestResult success(ResultEnum resultEnum) {
        RestResult result = new RestResult();
        result.setSuccess(true);
        result.setStatus(resultEnum.getCode());
        result.setMsg(resultEnum.getMessage());
        return result;
    }

    public static RestResult success(ResultEnum resultEnum, Object data) {
        RestResult result = new RestResult();
        result.setSuccess(true);
        result.setStatus(resultEnum.getCode());
        result.setMsg(resultEnum.getMessage());
        result.setData(data);
        return result;
    }

}
