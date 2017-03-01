package com.example.why.xixiweather.presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.why.xixiweather.model.entity.IWeatherInfo;
import com.example.why.xixiweather.model.entity.WeatherInfo;
import com.example.why.xixiweather.view.activity.IView;

import java.util.List;

/**
 * Created by why on 17-1-5.
 */

public class PresenterImpl implements IPresenter {

    private IView mCitiesView;
    private IWeatherInfo mWeatherInfo;

    private Handler mHandler;

    public PresenterImpl(IView citiesView) {
        this.mCitiesView = citiesView;

        //获得主线程的hander,这样可以在这个类中直接修改ui
        mHandler  = new Handler(Looper.getMainLooper());

    }
    @Override
    public List<WeatherInfo> getWeathers(String usl) {

        return null;
    }

    private void getCityInfos() {


    }
}
