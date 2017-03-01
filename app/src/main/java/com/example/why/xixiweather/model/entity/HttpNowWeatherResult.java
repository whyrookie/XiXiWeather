package com.example.why.xixiweather.model.entity;

import java.util.List;

/**
 * Created by why on 17-1-6.
 */

public class HttpNowWeatherResult {
    List<WeatherInfo> results;

    public List<WeatherInfo> getResults() {
        return results;
    }

    public void setResults(List<WeatherInfo> results) {
        this.results = results;
    }
}
