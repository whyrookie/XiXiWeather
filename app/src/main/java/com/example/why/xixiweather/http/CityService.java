package com.example.why.xixiweather.http;

import com.example.why.xixiweather.model.entity.CityInfo;
import com.example.why.xixiweather.model.entity.WeatherInfo;
import com.google.gson.JsonArray;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by why on 17-1-5.
 */

public interface CityService {

    @GET("china-city-list.json")
    Observable<JsonArray> getCityInfos();
}
