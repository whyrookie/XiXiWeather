package com.example.why.xixiweather.presenter;

import com.example.why.xixiweather.model.entity.CityInfo;
import com.google.gson.JsonArray;

import rx.Subscriber;

/**
 * Created by why on 17-1-5.
 */

public interface ICityPresenter {

    public void getCitiesInfo(Subscriber<JsonArray> subscriber);

    public void setCityPickerValues(String provinceZh);

    public void setRegionPickerValues(String cityZh);

}
