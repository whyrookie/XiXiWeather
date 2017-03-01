package com.example.why.xixiweather.presenter;

import android.content.Context;
import android.util.Log;

import com.example.why.xixiweather.App;
import com.example.why.xixiweather.http.CityService;
import com.example.why.xixiweather.model.entity.CityInfo;
import com.example.why.xixiweather.model.entity.CityInfoDao;
import com.example.why.xixiweather.model.entity.DaoSession;
import com.example.why.xixiweather.view.activity.CitiesActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by why on 17-1-5.
 */

public class CityPresenterImpl implements ICityPresenter {

    private CityInfo mCityInfo;
//    private CityInfoDao mCityInfoDao;
//    private org.greenrobot.greendao.query.Query<CityInfo> mCityQUery;
    private CitiesActivity mCitiesActivity;
    private CityService mCityServiceImpl;

    private Subscriber<JsonArray> mSubscriber;
    private Retrofit mRetrofit;
    private CityService mCityService;
    private DaoSession mDaoSession;
    private RxDao<CityInfo,Long> mCityInfoRxDao;
    private RxQuery<CityInfo> mCityInfoRXQuery;

    private static final String TAG = "CityPresenterImpl";

    public CityPresenterImpl(Context context){
        mCitiesActivity = ((CitiesActivity)context);

        mDaoSession = ((App)(mCitiesActivity.getApplication())).getDaoSession();
        mCityInfoRxDao = mDaoSession.getCityInfoDao().rx();

        mCityInfoRXQuery = mDaoSession.getCityInfoDao().queryBuilder().orderAsc(CityInfoDao.Properties.Id).rx();

        String baseUrl = "http://files.heweather.com/";

        Gson gson = new GsonBuilder().setLenient().create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mCityService = mRetrofit.create(CityService.class);

        mSubscriber = new Subscriber<JsonArray>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "error" + e.getMessage());
            }

            @Override
            public void onNext(JsonArray cityInfos) {
                Log.e(TAG,"返回数据长度" + cityInfos.size());

                //这里取得是JsonArray,其实可以使用List<CityInfo> 只是GreenDao的Id的类型是Long,而Json的是string
                for (int i = 0; i < cityInfos.size(); i++) {

                    JsonObject jsonObject = cityInfos.get(i).getAsJsonObject();
                    Log.d(TAG,"jsonObject数据"+jsonObject.get("cityEn").toString());
                    CityInfo cityInfo = new CityInfo(null,replaceDoubleQuote(jsonObject.get("cityEn").toString()),
                            replaceDoubleQuote(jsonObject.get("cityZh").toString()),replaceDoubleQuote(jsonObject.get("countryCode").toString()),
                            replaceDoubleQuote(jsonObject.get("countryEn").toString()),replaceDoubleQuote(jsonObject.get("countryZh").toString()),
                            replaceDoubleQuote(jsonObject.get("provinceEn").toString()),replaceDoubleQuote(jsonObject.get("provinceZh").toString()),
                            replaceDoubleQuote(jsonObject.get("leaderEn").toString()),replaceDoubleQuote(jsonObject.get("leaderZh").toString()),
                            replaceDoubleQuote(jsonObject.get("lat").toString()),replaceDoubleQuote(jsonObject.get("lon").toString()));

                    insertCityInfo(cityInfo);
                }
            }
        };
        //从网络中获取城市信息
//        getCitiesInfo(mSubscriber);
//        queryCitiesInfo();
//        queryCitiesByLeaderZh("福建");


    }

    private String replaceDoubleQuote(String str){
        return str.replace("\"","");
    }

    @Override
    public void getCitiesInfo(Subscriber<JsonArray> subscriber) {
        mCityService.getCityInfos()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private void insertCityInfo(CityInfo cityInfo) {
        mCityInfoRxDao.insert(cityInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CityInfo>() {
                    @Override
                    public void call(CityInfo cityInfo) {
                        Log.d(TAG,"插入城市名" + cityInfo.getCityZh());
                    }
                });
    }

    /**
     * 查询所以城市列表
     */
    private void queryCitiesInfo(){
        mCityInfoRXQuery.list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<CityInfo>>() {
                    @Override
                    public void call(List<CityInfo> cityInfos) {
                        for (int i = 0; i< cityInfos.size(); i++) {
                            Log.d(TAG,"所有的城市cityZh"+cityInfos.get(i).getCityZh());
                        }
                    }
                });
    }

    //根据上级城市中文名查询,比如滨江的上级城市是杭州
    private void queryCitiesByLeaderZh(String leader){

        RxQuery<CityInfo> query = mCityInfoRxDao.getDao().queryBuilder().where(CityInfoDao.Properties.LeaderZh.eq(leader)).build().__InternalRx();

        query.list().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<CityInfo>>() {
                    @Override
                    public void call(List<CityInfo> cityInfos) {
                        String[] regions = new String[cityInfos.size()];
                        for (int i = 0; i< regions.length; i++) {
                            regions[i] = cityInfos.get(i).getCityZh();
                        }
                        mCitiesActivity.setRigionPickerValues(regions);
                    }
                });

    }

    /**
     * 根据省份查询城市包括下属的县
     * @param province
     */
    private void queryCityInfosByProvinceZh(String province){
        RxQuery<CityInfo> query = mCityInfoRxDao.getDao().queryBuilder().where(CityInfoDao.Properties.ProvinceZh.eq(province)).build().__InternalRx();

        query.list().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<CityInfo>>() {
                    @Override
                    public void call(List<CityInfo> cityInfos) {
                        List<String> cityNamesZhList = new ArrayList();
                        for (int i = 0; i < cityInfos.size(); i++) {
                            if (cityInfos.get(i).getCityZh().equals(cityInfos.get(i).getLeaderZh())){
                                cityNamesZhList.add(cityInfos.get(i).getCityZh());
                            }
                        }

                        String[] cityNamesZhArray = new String[cityNamesZhList.size()];
                        for (int i = 0; i < cityNamesZhList.size(); i++) {
                            cityNamesZhArray[i] = cityNamesZhList.get(i);
                        }
                        mCitiesActivity.setCityPickerValues(cityNamesZhArray);
                    }
                });

    }

    /**
     * 设置省下属的地级市
     * @param provinceZh
     */
    @Override
    public void setCityPickerValues(String provinceZh) {
        queryCityInfosByProvinceZh(provinceZh);
    }

    /**
     * 设置地级市下属的区/县/县级市
     * @param cityZh
     */
    @Override
    public void setRegionPickerValues(String cityZh) {
        queryCitiesByLeaderZh(cityZh);
    }
}
