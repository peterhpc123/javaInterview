package com.example.springprinciple.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class LifecycleBean {
    private static final Logger log= LoggerFactory.getLogger(LifecycleBean.class);
    LifecycleBean(){
        log.debug("构造");
    }
    @Autowired
    public void autowire(@Value("${java_home}") String name){
        log.debug("依赖注入，{}",name);
    }
    @PostConstruct
    public void init(){
        log.debug("初始化");
    }
    @PreDestroy
    public void destory(){
        log.debug("销毁");
    }
}
