package com.carrot.jedis.startup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: Created by carrot
 * 2020/12/16 8:50
 */
@Slf4j
@Component
@Order(2)
public class SecondCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
         // 模拟springboot启动banner
        log.info("\u001B[32m >> startup second runner <<<");
    }
}
