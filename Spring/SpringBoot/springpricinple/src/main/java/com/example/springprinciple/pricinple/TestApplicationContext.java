package com.example.springprinciple.pricinple;

import com.example.springprinciple.webconfig.WebConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class TestApplicationContext {
    public static void main(String[] args) {
//        testClassPathXml();
   //     testAnnotationConfig();
        testAnnotationWebConfig();
    }
    static class Bean1{

    }
    static class Bean2{
        private Bean1 bean1;

        public Bean1 getBean1() {
            return bean1;
        }

        public void setBean1(Bean1 bean1) {
            this.bean1 = bean1;
        }
    }
    private static void testClassPathXml(){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("test.xml");
        System.out.println(context.getBean(Bean2.class).getBean1());
    }

    private static void testAnnotationConfig(){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Config.class);
        System.out.println(context.getBean(Bean2.class).getBean1());
    }
    @Configuration
    static class Config{
        @Bean
        public Bean1 bean1(){
            return new Bean1();
        }
        @Bean
        public Bean2 bean2(){
            Bean2 bean2=new Bean2();
            bean2.setBean1(bean1());
            return bean2;
        }
    }


    public static void testAnnotationWebConfig(){
        AnnotationConfigServletWebServerApplicationContext context=new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
    }
}
