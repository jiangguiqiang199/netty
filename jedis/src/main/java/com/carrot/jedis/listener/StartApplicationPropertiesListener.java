package com.carrot.jedis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 配置文件中配置监听器
 * @Author: Created by carrot
 * 2020/12/13 13:20
 */
@Slf4j
public class StartApplicationPropertiesListener implements ApplicationListener{

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("【当前事件:{}】",event.getClass());
    }

//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        log.info("【当前事件，配置文件配置监听器:{}】",event.getClass());
//    }
}
