package com.carrot.jedis.listener;

import org.springframework.boot.BootstrapContext;
import org.springframework.boot.BootstrapContextClosedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: Created by carrot
 * 2020/12/18 14:28
 */
public class BootstrapContextClosedEventListener implements ApplicationListener<BootstrapContextClosedEvent> {

    @Override
    public void onApplicationEvent(BootstrapContextClosedEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        BootstrapContext bootstrapContext = event.getBootstrapContext();
        System.out.println("【bootstrap上下文初始化完毕bootstrapContext.close(context)"+this.getClass().getName()+"】");
    }
}
