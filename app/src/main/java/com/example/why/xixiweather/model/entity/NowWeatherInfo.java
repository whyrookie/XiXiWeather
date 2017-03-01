package com.example.why.xixiweather.model.entity;

/**
 * Created by why on 17-1-6.
 */

public class NowWeatherInfo {
    private String text;
    private String code;
    private String temperature;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
