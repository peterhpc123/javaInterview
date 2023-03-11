package com.example.feignapi.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("userservice")
public interface UserClient {
    @GetMapping("actuator/info")
    String findById();
}
