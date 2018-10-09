package com.yzm.demo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
    @RequestMapping("/index2")
    public String showIndex(){
        return "index";
    }
}
