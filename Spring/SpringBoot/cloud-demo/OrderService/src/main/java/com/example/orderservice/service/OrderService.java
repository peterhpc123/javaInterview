package com.example.orderservice.service;

import com.example.orderservice.clients.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClientl;

    public String queryUserIdByEureka(){
        return userClientl.findById();
    }
}
