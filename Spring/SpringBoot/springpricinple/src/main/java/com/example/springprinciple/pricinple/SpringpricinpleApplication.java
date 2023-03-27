package com.example.springprinciple.pricinple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class SpringpricinpleApplication{

    @Configuration
    static class Config{
        @Bean
        Bean1 getBean1(){
            return new Bean1();
        }

        @Bean
        Bean2 getBean2(){
            return new Bean2();
        }
    }


    @Component
    static class Bean2{

        @Autowired
        Bean1 bean1;

        public Bean1 getBean1() {
            return bean1;
        }

        public void setBean1(Bean1 bean1) {
            this.bean1 = bean1;
        }
    }
    @Controller
    static class Bean1{
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringpricinpleApplication.class, args);
        /*
        * beanfactory实现
        * */


        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        //beandefinition
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("config",beanDefinition);

        //add beanfactorpostprocessors
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        //实现后处理器功能
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().stream().forEach(beanFactoryPostProcessor -> {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        });
        for(String name:beanFactory.getBeanDefinitionNames()){
            System.out.println(name);
        }

        //Bean 后处理器，针对bean生命周期各个阶段提供扩展，例如@Autowired @Resource...
//        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanPostProcessor -> {
//            beanFactory.addBeanPostProcessor(beanPostProcessor);
//        });
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanFactory::addBeanPostProcessor);
        for(String name:beanFactory.getBeanDefinitionNames()){
            System.out.println(name);
        }
        System.out.println(">>>>>>>>>>>");
        System.out.println(beanFactory.getBean(Bean2.class).getBean1());//beanfactory默认延迟实例化，只有调用的时候才创建

        /*
        * beanfactory特点：
        * 1.不会主动调用beanfactory后处理器
        * 2.不会主动调用bean后处理器
        * 3.不会主动初始化单例
        * 4.不会解析beanfactory ${} #{}
        * bean 后处理器会有排序逻辑
        * */



    }

}
