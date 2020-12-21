package com.carrot.jedis.event;

/**
 * @Author: Created by carrot
 * 2020/12/15 12:49
 */
public class SnowWeatherListener implements WeatherListener {
    @Override
    public void onWeatherEvent(WeatherEvent event) {
        if(event instanceof SnowWeatherEvent){
            System.out.println("hello "+event.getWeather());
        }
    }
}
