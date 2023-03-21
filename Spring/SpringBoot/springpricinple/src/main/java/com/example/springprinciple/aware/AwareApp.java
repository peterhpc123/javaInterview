package com.example.springprinciple.aware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class AwareApp {
    public static void main(String[] args) {
        GenericApplicationContext context=new GenericApplicationContext();
        context.registerBean("myBean", MyBean.class);
        context.refresh();
        context.close();
    }
}
