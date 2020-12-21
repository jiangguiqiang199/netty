package com.carrot.jedis.event;

/**
 * @Author: Created by carrot
 * 2020/12/15 12:47
 */
public class RainWeatherEvent extends WeatherEvent {
    @Override
    public String getWeather() {
        return "下雨天气";
    }
}
