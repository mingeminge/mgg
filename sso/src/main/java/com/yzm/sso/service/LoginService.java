package com.yzm.sso.service;

import com.yzm.sso.DTO.LoginDTO;
import com.yzm.sso.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    LoginDTO login(HttpServletRequest request, HttpServletResponse response, String userName, String password);

    Boolean logout(HttpServletResponse response,String token);

    User getUserByToken(String token);

    Boolean hasKey(String token);

    void flushExpire(String token);
}
