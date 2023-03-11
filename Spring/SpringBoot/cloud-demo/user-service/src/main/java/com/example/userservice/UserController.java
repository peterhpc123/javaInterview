package com.example.userservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping("/")
    public String access(){
        System.out.println("Hello world!");
        return "hello world!";
    }
    @RequestMapping("/actuator/info")
    public String error(){
        System.out.println("error");
        return "Oops! System error!";
    }
    @GetMapping("/userId")
    public String getUserId(){
        return "123";
    }
}
