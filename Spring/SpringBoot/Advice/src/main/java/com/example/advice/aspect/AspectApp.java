package com.example.advice.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import java.lang.reflect.Method;

@SpringBootApplication
public class AspectApp {
    public static void main(String[] args) {
        GenericApplicationContext context=new GenericApplicationContext();
        context.registerBean("aspect1", Aspect1.class);
        context.registerBean("config", Config.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.refresh();
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }
    static class Target1{
        public void foo(){
            System.out.println("This is foo");
        }
    }
    @Aspect
    static class Aspect1{
        @Before("execution(* foo()")
        public void before(){
            System.out.println("aspect1 before");
        }
        @After("execution(* foo())")
        public void after(){
            System.out.println("aspect1 after");
        }
    }

    @Configuration
    static class Config{
        @Bean
        public Advisor advisor3(MethodInterceptor advice3){
            AspectJExpressionPointcut pointcut=new AspectJExpressionPointcut();
            pointcut.setExpression("execution(* foo())");
            return new DefaultPointcutAdvisor(pointcut,advice3);
        }
        @Bean
        public MethodInterceptor advice3(){
            return invocation -> {
                System.out.println("advice3 before ...");
                Object proceed = invocation.proceed();
                System.out.println("advice3 after ...");
                return proceed;
            };
        }
    }
}
