package com.carrot.jedis.processor;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: Created by carrot
 * 2020/12/14 14:05
 */
@Component
@Slf4j
@Data
public class MyBean implements InitializingBean{

    private String name;

    @PostConstruct
    public void init01(){
        log.info("【调用MyBean @PostConstruct init方法】");
    }

    public MyBean() {
        log.info("【调用MyBean构造方法】");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("【调用MyBean afterPropertiesSet方法】");
    }
}
