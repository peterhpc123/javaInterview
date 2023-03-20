package com.example.springprinciple.bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BeanLifecycle {
    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run(BeanLifecycle.class,args);
        //System.out.println(context.getBean(MyBeanPostProcessor.class));
        context.close();
    }

}
