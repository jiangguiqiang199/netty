package com.carrot.jedis.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Author: Created by carrot
 * 2020/12/19 9:32
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Object source = event.getSource();
        ApplicationContext applicationContext = event.getApplicationContext();
        System.out.println("【context-refreshed,"+this.getClass().getName()+"】");
    }
}
