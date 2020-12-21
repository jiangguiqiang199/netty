package com.carrot.jedis;

import com.carrot.weather.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Created by carrot
 * 2020/12/19 16:08
 */
@Slf4j
@SpringBootTest
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    public void getWeatherTest(){
        System.out.println(weatherService.getType()+","+weatherService.getRate());
    }

}
