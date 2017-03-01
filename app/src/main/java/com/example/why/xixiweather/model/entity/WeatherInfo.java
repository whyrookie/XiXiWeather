package com.example.why.xixiweather.model.entity;

import com.example.why.xixiweather.model.entity.CityLocationInfo;
import com.example.why.xixiweather.model.entity.IWeatherInfo;
import com.example.why.xixiweather.model.entity.NowWeatherInfo;

/**
 * Created by why on 17-1-5.
 */

public class WeatherInfo implements IWeatherInfo {
    private CityLocationInfo location;
    private NowWeatherInfo now;
    private String last_update;


    public CityLocationInfo getLocation() {
        return location;
    }

    public void setLocation(CityLocationInfo location) {
        this.location = location;
    }

    public NowWeatherInfo getNow() {
        return now;
    }

    public void setNow(NowWeatherInfo now) {
        this.now = now;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
