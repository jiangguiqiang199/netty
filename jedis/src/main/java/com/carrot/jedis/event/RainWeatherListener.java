package com.carrot.jedis.event;

/**
 * @Author: Created by carrot
 * 2020/12/15 12:50
 */
public class RainWeatherListener implements WeatherListener {

    @Override
    public void onWeatherEvent(WeatherEvent event) {
        if(event instanceof RainWeatherEvent){
           System.out.println("hello "+event.getWeather());
        }
    }
}
