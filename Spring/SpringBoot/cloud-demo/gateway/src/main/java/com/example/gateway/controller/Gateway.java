package com.example.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Gateway {
    @GetMapping("/test")
    public String test(){
        return "This is gateway!";
    }
}
