package com.carrot.jedis.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: Created by carrot
 * 2020/12/19 9:56
 */
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        Object source = event.getSource();
        SpringApplication springApplication = event.getSpringApplication();
        System.out.println("【application-started,"+this.getClass().getName()+"】");
    }
}
