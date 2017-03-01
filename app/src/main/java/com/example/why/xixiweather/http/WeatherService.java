package com.example.why.xixiweather.http;

import com.example.why.xixiweather.model.entity.HttpNowWeatherResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by why on 17-1-6.
 */

public interface WeatherService {
    @GET("now.json")
    Observable<HttpNowWeatherResult> getNowWeather(@Query("key") String key, @Query("location") String location);
}
