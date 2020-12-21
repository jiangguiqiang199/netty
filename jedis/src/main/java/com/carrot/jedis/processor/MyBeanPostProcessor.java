package com.carrot.jedis.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: Created by carrot
 * 2020/12/14 14:00
 */
@Component
@Slf4j
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("myBean")){
            log.info("【实例化{}之前调用】",beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("myBean")){
            log.info("【实例化{}之后调用】",beanName);
            MyBean obj = (MyBean)bean;
        }
        return bean;
    }
}
