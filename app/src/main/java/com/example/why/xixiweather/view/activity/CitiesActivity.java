package com.example.why.xixiweather.view.activity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.example.why.xixiweather.R;
import com.example.why.xixiweather.presenter.CityPresenterImpl;
import com.example.why.xixiweather.presenter.ICityPresenter;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Query;

public class CitiesActivity extends AppCompatActivity implements IView, NumberPicker.OnValueChangeListener{


    @BindView(R.id.picker_province)
    NumberPicker mPickerProvince;
    @BindView(R.id.picker_city)
    NumberPicker mPickerCity;
    @BindView(R.id.picker_region)
    NumberPicker mPickerRegion;
    @BindView(R.id.layout_city_picker)
    LinearLayout mLayoutCityPicker;
    private String[] mProvinces = new String[]{
            "北京","上海","天津","重庆","香港","澳门","台湾","黑龙江","吉林","辽宁","内蒙古","河北","河南",
            "山西","山东","江苏","浙江","福建","江西","安徽","湖北","湖南","广东","广西","海南","贵州","云南",
            "四川","西藏","陕西","宁夏","甘肃","青海","新疆"
    };
    private String[] mProvincialCapitals = new String[]{
            "北京","上海","天津","重庆","香港","澳门","台北","哈尔滨","长春","沈阳","呼和浩特","石家庄","郑州",
            "太原","济南","南京","杭州","福州","南昌","合肥","武汉","长沙","广州","南宁","海口","贵阳","昆明",
            "成都","拉萨","西安","银川","兰州","西宁","乌鲁木齐"
    };
    private String[] mCities;
    private String[] mRegions;
    private ImageView imageView;

    private ICityPresenter mCityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities2);
        ButterKnife.bind(this);

        mCityPresenter = new CityPresenterImpl(this);
        initPickers();
//        setNumberPicker();
//        mExpandableListView.setAdapter(new ExpandableCityAdapter(this,mProvinces,mCities));
    }

    private void initPickers(){
        mPickerProvince.setDisplayedValues(mProvinces);
        mPickerProvince.setMinValue(0);
        mPickerProvince.setMaxValue(mProvinces.length-1);

        mPickerCity.setMinValue(0);
        mPickerRegion.setMinValue(0);
        //设置字体和分割线的颜色
        setPickerDividerColor();
        mCityPresenter.setCityPickerValues(mProvinces[17]);
    }

    /**
     * 设置地级市列表
     * @param cities
     */
    public void setCityPickerValues(String[] cities){
        mCities = cities;
        for (int i = 0; i< cities.length; i++) {
            Log.d("城市名称","---"+cities[i]);
        }
        mPickerCity.setDisplayedValues(cities);
        mPickerCity.setMaxValue(cities.length-1);
        mCityPresenter.setRegionPickerValues(mCities[mPickerCity.getValue()]);
    }

    /**
     * 设置地级市下属的区/县/县级市
     * @param regions
     */
    public void setRigionPickerValues(String[] regions){
        mRegions = regions;
        mPickerRegion.setDisplayedValues(regions);
        mPickerRegion.setMaxValue(regions.length - 1);
    }
    private void setPickerDividerColor() {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try{
                    pf.set(mPickerProvince,new ColorDrawable(Color.BLUE));
                    pf.set(mPickerCity,new ColorDrawable(Color.BLUE));
                    pf.set(mPickerRegion,new ColorDrawable(Color.BLUE));
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        switch (picker.getId()){
            case R.id.picker_province:
                break;

            case R.id.picker_city:

                break;

            case R.id.picker_region:

                break;
        }

    }

    /*private void setNumberPicker(){
        mPickerProvince.setMaxValue(mProvinces.length-1);
        mPickerProvince.setDisplayedValues(mProvinces);
    }*/
}
