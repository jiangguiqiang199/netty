package com.carrot.jedis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * main方法
 * @Author: Created by carrot
 * 2020/12/13 13:20
 */
@Slf4j
public class StartApplicationMainListener implements ApplicationListener{

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("【当前事件:{}】",event.getClass());
    }

//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        log.info("【当前事件，main方法配置监听器:{}】",event.getClass());
//    }
}
