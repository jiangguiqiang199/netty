package com.carrot.jedis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

/**
 * springBoot启动初始化实验
 * @Author: Created by carrot
 * 2020/12/13 12:59
 */
@Order(5)
@Slf4j
public class StartApplicationMainInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
         log.info("【StartApplicationMainInitializer初始化完成...............】");
    }
}
