package com.example.springprinciple.webconfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties({WebMvcProperties.class, ServerProperties.class})
public class WebConfig{
    //内置web容器
    @Bean
    public ServletWebServerFactory servletWebServerFactory(ServerProperties serverProperties){
        serverProperties.setPort(8888);
        return new TomcatServletWebServerFactory(serverProperties.getPort());
    }
    //创建dispatcherservlet
    @Bean
    public DispatcherServlet dispatcherServlet(){
        return new DispatcherServlet();
    }
    //注册dispatcherservlet，spring mvc入口
    @Bean
    public DispatcherServletRegistrationBean registrationBean(DispatcherServlet dispatcherServlet, WebMvcProperties webMvcProperties){
        DispatcherServletRegistrationBean dispatcherServletRegistrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        webMvcProperties.getServlet().setLoadOnStartup(100);//>0，在tomcat启动时就初始化dispatcherservlet
        dispatcherServletRegistrationBean.setLoadOnStartup(webMvcProperties.getServlet().getLoadOnStartup());
        return dispatcherServletRegistrationBean;
    }
    @Bean("/hello")
    public Controller controller(){
        return new Controller() {
            @Override
            public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
                response.getWriter().println("hello world!");
                return null;
            }
        };
    }

}