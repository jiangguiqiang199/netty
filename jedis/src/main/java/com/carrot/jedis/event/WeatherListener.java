package com.carrot.jedis.event;

/**
 * @Author: Created by carrot
 * 2020/12/15 12:48
 */
public interface WeatherListener {
    void onWeatherEvent(WeatherEvent event);
}
