package com.example.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;

    public String queryUserIdByEureka(){
        return restTemplate.getForObject("http://userservice/actuator/info", String.class);
    }
}
