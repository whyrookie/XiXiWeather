package com.example.why.xixiweather.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.why.xixiweather.R;
import com.example.why.xixiweather.presenter.WeatherPresenterImpl;
import com.example.why.xixiweather.view.activity.CitiesActivity;
import com.example.why.xixiweather.view.activity.IView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements IView,View.OnClickListener {

    @BindView(R.id.img_weather)
    ImageView mimgWeather;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.btn_add_floating)
    FloatingActionButton mAddFloatingBtn;
    @BindView(R.id.activity_main)
    CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAddFloatingBtn.setOnClickListener(this);
        WeatherPresenterImpl weatherPresenter = new WeatherPresenterImpl();
        weatherPresenter.getNowWeather();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_floating:
                startActivity(new Intent(MainActivity.this, CitiesActivity.class));
                break;
        }
    }

}


