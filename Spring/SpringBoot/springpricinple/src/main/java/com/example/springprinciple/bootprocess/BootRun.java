package com.example.springprinciple.bootprocess;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.lang.reflect.Constructor;
import java.util.List;

public class BootRun {
    public static void main(String[] args) throws Exception {
        //添加app监听器
        SpringApplication app=new SpringApplication();
        app.addListeners(event -> System.out.println(event.getClass()));

        //获取事件发送器类名
        List<String> names = SpringFactoriesLoader.loadFactoryNames(SpringApplicationRunListener.class, BootRun.class.getClassLoader());
        for (String name : names) {
            System.out.println(name);
            Class<?> clazz=Class.forName(name);
            Constructor<?> constructor= clazz.getConstructor(SpringApplication.class,String[].class);
            SpringApplicationRunListener publisher= (SpringApplicationRunListener) constructor.newInstance(app,args);
            DefaultBootstrapContext bootstrapContext=new DefaultBootstrapContext();
            publisher.starting(bootstrapContext);//spring boot 启动

        }

        //1.1创建事件发布器 SpringApplicationRunListeners listeners = getRunListeners(args);
        //1.2.springboot启动
        //2.封装参数对象 listeners.starting(bootstrapContext, this.mainApplicationClass)
        //3.创建environment 添加命令行
        //4.松散绑定
        //5.发送事件-环境准备，监听器添加环境后处理器
        //6.绑定配置文件参数
        //7.打印banner
        //8.创建容器
        //9.应用初始化器，增强
        //10.发送事件-容器创建好
        //11.加载beandefinition
        //12.容器refresh
        //13.发送事件-容器准备好
        //14.发送事件-容器运行

        //环境对象，systemproperties systemEnvironment 命令行参数
        ApplicationEnvironment applicationEnvironment=new ApplicationEnvironment();

    }
}
