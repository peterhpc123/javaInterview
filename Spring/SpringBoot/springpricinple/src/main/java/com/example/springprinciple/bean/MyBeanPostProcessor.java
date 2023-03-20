package com.example.springprinciple.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {
    private static final Logger log= LoggerFactory.getLogger(MyBeanPostProcessor.class);
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if(beanName.equals("lifecycleBean")){
            log.debug("<<<销毁前执行，如@PreDestory");
        }
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(beanName.equals("lifecycleBean")){
            log.debug("<<<实例化前执行，这里返回的对象会替换原来的bean");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if(beanName.equals("lifecycleBean")){
            log.debug("<<<实例化后执行，这里返回的false会跳过依赖注入");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if(beanName.equals("lifecycleBean")){
            log.debug("<<<依赖注入执行，@Autowired @value @Resource" );
        }
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("lifecycleBean")){
            log.debug("<<<依赖注入后初始化前执行，这里返回对象会替换原来的bean，如@PostContruct @ConfigurationProperties" );
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("lifecycleBean")){
            log.debug("<<<初始化后执行，这里返回对象会替换原来的bean，如代理增强" );
        }
        return bean;
    }
}
