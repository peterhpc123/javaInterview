package com.example.advice;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;


@SpringBootApplication
public class AdviceApplication {

    public static void main(String[] args) throws NoSuchMethodException {
        AspectJExpressionPointcut pt1=new AspectJExpressionPointcut();
        pt1.setExpression("execution(* bar())");
        System.out.println(pt1.matches(T1.class.getMethod("foo"), T1.class));
        System.out.println(pt1.matches(T1.class.getMethod("bar"), T1.class));


    }
    static class T1{
        @Transactional
        public void foo(){

        }
        public void bar(){}
    }
}
