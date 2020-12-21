package com.carrot.jedis.startup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: Created by carrot
 * 2020/12/16 8:55
 */
@Slf4j
@Component
@Order(2)
public class SecondApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 模拟springboot启动banner
//        log.info("\u001B[32m >> startup second application runner <<<");
    }
}
