package com.carrot.jedis;

import com.carrot.jedis.listener.StartApplicationMainInitializer;
import com.carrot.jedis.listener.StartApplicationMainListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

//@PropertySource({"demo.properties"})
@SpringBootApplication
@MapperScan({"com.carrot.jedis.dao"})
public class JedisApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(JedisApplication.class);
        // 17、默认的属性注入方式
        Properties properties = new Properties();
        properties.setProperty("jiang","jgq");
        springApplication.setDefaultProperties(properties);
        springApplication.run(args);
    }

}
