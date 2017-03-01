package com.example.why.xixiweather.presenter;

import com.example.why.xixiweather.model.entity.WeatherInfo;

import java.util.List;

/**
 * Created by why on 17-1-5.
 */

public interface IPresenter {

    public List<WeatherInfo> getWeathers(String usl);



}
