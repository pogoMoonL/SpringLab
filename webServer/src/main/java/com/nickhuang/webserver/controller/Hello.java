package com.nickhuang.webserver.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


public class Hello {
    @RequestMapping("/")
    public String hello(){
        return "向全世界說聲Spring Boot 很高興認識你!";
    }
}
