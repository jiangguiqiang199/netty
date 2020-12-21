package com.carrot.weather;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: Created by carrot
 * 2020/12/19 15:51
 */

@ConfigurationProperties(prefix = "weather")
public class WeatherSource {

    // 天气类型 下雨|下雪
    private String type;

    // 天气级别，严重|轻微
    private String rate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
