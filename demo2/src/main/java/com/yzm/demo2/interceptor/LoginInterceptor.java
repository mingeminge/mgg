package com.yzm.demo2.interceptor;

import com.yzm.demo2.util.HttpClientUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer requestURL = request.getRequestURL();
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("token")) {
                    token = cookies[i].getValue();
                }
            }
        }

        if (token == null) {
            response.sendRedirect("http://localhost:8086/login?redirect=" + requestURL);
            return false;
        }
        String userStr = HttpClientUtil.doGet("http://localhost:8086/sso/getUserByToken/" + token);

        return true;
    }
}
