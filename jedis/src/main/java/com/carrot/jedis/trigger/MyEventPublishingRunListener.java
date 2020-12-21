package com.carrot.jedis.trigger;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Author: Created by carrot
 * 2020/12/18 10:52
 */
@Slf4j
@Order(1)
public class MyEventPublishingRunListener  implements SpringApplicationRunListener {

    private final SpringApplication application;

    private final String[] args;

    private final SimpleApplicationEventMulticaster initialMulticaster;

    public MyEventPublishingRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
        this.initialMulticaster = new SimpleApplicationEventMulticaster();
        for (ApplicationListener<?> listener : application.getListeners()) {
            System.out.println("【spring factories默认事件监听器：{"+listener.getClass().getName()+"}】");
            this.initialMulticaster.addApplicationListener(listener);
        }
    }

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        log.info("【自定义springBoot启动事件触发器，触发starting】");
        System.out.println("【自定义springBoot启动事件触发器，触发starting】");
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        log.info("【自定义springBoot启动事件触发器，触发environmentPrepared】");
        System.out.println("【自定义springBoot启动事件触发器，触发environmentPrepared】");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("【自定义springBoot启动事件触发器，触发contextPrepared】");
        System.out.println("【自定义springBoot启动事件触发器，触发contextPrepared】");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("【自定义springBoot启动事件触发器，触发contextLoaded】");
        System.out.println("【自定义springBoot启动事件触发器，触发contextLoaded】");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        log.info("【自定义springBoot启动事件触发器，触发started】");
        System.out.println("【自定义springBoot启动事件触发器，触发started】");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        log.info("【自定义springBoot启动事件触发器，触发running】");
        System.out.println("【自定义springBoot启动事件触发器，触发running】");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("【自定义springBoot启动事件触发器，触发failed】");
        System.out.println("【自定义springBoot启动事件触发器，触发failed】");
    }
}
