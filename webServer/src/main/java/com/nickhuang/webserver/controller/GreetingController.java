package com.nickhuang.webserver.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;


//import reactor.core.publisher.Mono;

@RestController
public class GreetingController {
    @RequestMapping("/")
    public String hello(){
        return "向全世界說聲Spring Boot 很高興認識你!";
    }


    @GetMapping("/hello")
    public Mono nick() {
        return Mono.just("Hello Spring WebFlux RestController");
    }
}
