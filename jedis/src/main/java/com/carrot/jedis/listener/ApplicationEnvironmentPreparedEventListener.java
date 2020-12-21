package com.carrot.jedis.listener;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Author: Created by carrot
 * 2020/12/18 13:31
 */
public class ApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        ConfigurableBootstrapContext bootstrapContext = event.getBootstrapContext();
        System.out.println("【environment-prepared,"+this.getClass().getName()+"】");
    }
}
