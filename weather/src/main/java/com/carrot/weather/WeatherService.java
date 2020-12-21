package com.carrot.weather;

/**
 * @Author: Created by carrot
 * 2020/12/19 15:55
 */
public class WeatherService {

    private WeatherSource weatherSource;

    public WeatherService(WeatherSource weatherSource) {
        this.weatherSource = weatherSource;
    }

    public String getType(){
        return weatherSource.getType();
    }

    public String getRate(){
        return weatherSource.getRate();
    }
}
