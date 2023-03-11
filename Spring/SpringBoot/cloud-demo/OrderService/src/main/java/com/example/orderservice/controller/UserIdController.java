package com.example.orderservice.controller;

import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserIdController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/actuator/info")
    public String getUserIdByRemoteAccess(){
        return orderService.queryUserIdByEureka();
    }
}
