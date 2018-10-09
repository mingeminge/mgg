package com.yzm.sso.controller;

import com.yzm.sso.DTO.LoginDTO;
import com.yzm.sso.VO.RestResult;
import com.yzm.sso.enums.ResultEnum;
import com.yzm.sso.model.User;
import com.yzm.sso.service.LoginService;
import com.yzm.sso.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/sso")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public RestResult login(HttpServletRequest request, HttpServletResponse response, String userName, String password) {
        LoginDTO user = loginService.login(request, response, userName, password);
        return ResultUtil.success(ResultEnum.SUCCESS, user.getToken());
    }

    @GetMapping("/logout/{token}")
    public RestResult logout(HttpServletResponse response, @PathVariable String token) {
        Boolean logout = loginService.logout(response, token);
        if (logout) {
            return ResultUtil.success(ResultEnum.SUCCESS);
        }
        return ResultUtil.fail(ResultEnum.FAILED);
    }

    @GetMapping("/getUserByToken/{token}")
    public RestResult getUserByToken(@PathVariable String token) {
        User userByToken = loginService.getUserByToken(token);
        return ResultUtil.success(ResultEnum.SUCCESS, userByToken);
    }

    @GetMapping("/hasKey/{token}")
    public RestResult hasKey(@PathVariable String token) {
        Boolean hasKey = loginService.hasKey(token);
        return ResultUtil.success(ResultEnum.SUCCESS, hasKey);
    }

    @GetMapping("/flushExpire/{token}")
    public RestResult flushExpire(@PathVariable String token) {
        loginService.flushExpire(token);
        return ResultUtil.success(ResultEnum.SUCCESS);
    }
}
