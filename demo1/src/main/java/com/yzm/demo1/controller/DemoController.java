package com.yzm.demo1.controller;

import com.yzm.demo1.util.HttpClientUtil;
import com.yzm.demo1.util.JsonUtils;
import com.yzm.demo1.util.RestResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DemoController {
    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/getName")
    @ResponseBody
    public RestResult getUserName(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }
        String result = HttpClientUtil.doGet("http://localhost:8086/sso/getUserByToken/" + token);
        if (result != "") {
            RestResult restResult = JsonUtils.jsonToPojo(result, RestResult.class);
            if (restResult.getSuccess()) {
                return restResult;
            }
        }

        return null;

    }
}
