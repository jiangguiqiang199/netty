package com.carrot.jedis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author: Created by carrot
 * 2020/12/18 12:32
 */
@Slf4j
public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        ConfigurableBootstrapContext bootstrapContext = event.getBootstrapContext();
        System.out.println("【starting,"+this.getClass().getName()+"】");
    }
}
