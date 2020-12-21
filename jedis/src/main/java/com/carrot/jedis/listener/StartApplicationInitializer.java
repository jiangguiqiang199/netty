package com.carrot.jedis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * springBoot启动初始化实验
 * @Author: Created by carrot
 * 2020/12/13 12:59
 */
@Order(1)
@Slf4j
public class StartApplicationInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        // 获取环境配置
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        // 设置必备属性检查，如果没有的话，启动失败
        environment.setRequiredProperties("jiangguiqiang");
        // spring容器刷新之前设置自定义属性值
        Map<String,Object> map = new HashMap<>();
        map.put("key1","value1");
        MapPropertySource mapPropertySource = new MapPropertySource("startApplicationInitializer", map);
        environment.getPropertySources().addLast(mapPropertySource);
        log.info("【StartApplicationInitializer初始化完成...............】");
    }
}
