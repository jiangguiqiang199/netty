package com.carrot.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Created by carrot
 * 2020/12/19 15:57
 */
@Configuration
@EnableConfigurationProperties(WeatherSource.class) // 开启自动化配置
@ConditionalOnProperty(name = "weather.enable",havingValue = "true")// 使用方必须配置weather.enable=true才能生效
public class WeatherConfiguration {

    @Autowired
    private WeatherSource weatherSource;

    @Bean
    @ConditionalOnMissingBean(WeatherService.class) // 当使用方没有显示注入WeatherService时，才会注入
    public WeatherService weatherService(){
        return new WeatherService(weatherSource);
    }

}
