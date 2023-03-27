package com.example.springprinciple.bootprocess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.support.GenericApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class BootInitialization {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("1.获取bean definition 源");
        SpringApplication spring=new SpringApplication(BootInitialization.class);
        spring.setSources(Set.of("classpath:boot.xml"));
        System.out.println("2.推断应用类型");
        Method deduceFromClasspath = WebApplicationType.class.getDeclaredMethod("deduceFromClasspath");
        deduceFromClasspath.setAccessible(true);
        System.out.println("应用类型为："+deduceFromClasspath.invoke(null));
        System.out.println("3.application context初始化器");
        spring.addInitializers(applicationContext -> {
            if(applicationContext instanceof GenericApplicationContext gac){
                gac.registerBean("bean3",Bean3.class);
            }
        });
        System.out.println("4.监听器与事件");
        spring.addListeners(event -> {
            System.out.println("事件为："+event.getClass());
        });
        System.out.println("5.启动类推断");
        Method deduceMainApplicationClass = SpringApplication.class.getDeclaredMethod("deduceMainApplicationClass");
        deduceMainApplicationClass.setAccessible(true);
        System.out.println("主类是："+deduceMainApplicationClass.invoke(spring));
    }
    static class Bean3{

    }
}
