package com.carrot.jedis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.Bootstrapper;

/**
 * @Author: Created by carrot
 * 2020/12/13 13:25
 */
@Slf4j
public class StartApplicationBootstrapper implements Bootstrapper {
    @Override
    public void intitialize(BootstrapRegistry registry) {
        log.info("【Bootstrapper初始化完成...............】");
    }
}
