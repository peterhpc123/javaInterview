package com.example.orderservice.service;

import com.example.feignapi.clients.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    public String queryUserIdByEureka(){
        return userClient.findById();
    }
}
