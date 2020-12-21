package com.carrot.jedis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Author: Created by carrot
 * 2020/12/15 11:10
 */
@Component
public class TestInitializerGetProperties implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Object getPropertiesFromContext(){
        Environment environment = applicationContext.getEnvironment();
        String value = environment.getProperty("key1");
        return value;
    }
}
