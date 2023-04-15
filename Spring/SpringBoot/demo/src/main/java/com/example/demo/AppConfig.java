package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


public class AppConfig {
    @Bean
    public Order test() {
        return new Order();
    }
}
