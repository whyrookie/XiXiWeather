package com.example.why.xixiweather.presenter;

import android.util.Log;

import com.example.why.xixiweather.config.GlobalStr;
import com.example.why.xixiweather.http.WeatherService;
import com.example.why.xixiweather.model.entity.CityLocationInfo;
import com.example.why.xixiweather.model.entity.HttpNowWeatherResult;
import com.example.why.xixiweather.model.entity.NowWeatherInfo;
import com.example.why.xixiweather.model.entity.WeatherInfo;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by why on 17-1-6.
 */

public class WeatherPresenterImpl implements IWeatherPresenter{
    private static final String TAG = "WeatherPresenterImpl";
    @Override
    public void getNowWeather() {
        String baseUrl = "https://api.thinkpage.cn/v3/weather/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        WeatherService weatherService = retrofit.create(WeatherService.class);

        weatherService.getNowWeather(GlobalStr.API_KEY,"北京")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpNowWeatherResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"错误信息" + e.getMessage());
                    }

                    @Override
                    public void onNext(HttpNowWeatherResult httpNowWeatherResult) {
                        Log.d(TAG,"请求天气结果" + httpNowWeatherResult.getResults().toString());
                        List<WeatherInfo> weatherInfos = httpNowWeatherResult.getResults();

                        WeatherInfo weatherInfo = weatherInfos.get(0);

                        if (weatherInfo!= null) {
                            Log.d(TAG,"请求天气结果" + weatherInfo.getLast_update());
                            CityLocationInfo cityLocationInfo = weatherInfo.getLocation();
                            Log.d(TAG,"城市信息" + cityLocationInfo.getCountry());

                            NowWeatherInfo nowWeatherInfo = weatherInfo.getNow();
                            Log.d(TAG,"天气信息" + nowWeatherInfo.getText());
                        }

//                        if (weatherInfos != null) {
//                            for(int i = 0; i < weatherInfos.size(); i++) {
//
//                                CityLocationInfo cityLocationInfo = weatherInfos.get(i).getCityLocationInfo();
//
//                                Log.d(TAG,"天气信息" +cityLocationInfo.getName());
//                                Log.d(TAG,"天气信息" +cityLocationInfo.getCountry());
//                                Log.d(TAG,"天气信息" +cityLocationInfo.getPath());
//                                Log.d(TAG,"天气信息" +cityLocationInfo.getTimezone());
//                                Log.d(TAG,"天气信息" +cityLocationInfo.getTimezone_offset());
//                            }
//                        }

                    }
                });



    }
}
