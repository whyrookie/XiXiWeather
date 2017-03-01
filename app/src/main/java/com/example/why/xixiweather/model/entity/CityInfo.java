package com.example.why.xixiweather.model.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by why on 17-1-5.
 */
@Entity
public class CityInfo {

    @Id
    private Long id;
    private String cityEn;
    private String cityZh;
    private String countryCode;
    private String countryEn;
    private String countryZh;
    private String provinceEn;
    private String provinceZh;
    private String leaderEn;
    private String leaderZh;
    private String lat;
    private String lon;
    @Generated(hash = 69148619)
    public CityInfo(Long id, String cityEn, String cityZh, String countryCode,
            String countryEn, String countryZh, String provinceEn,
            String provinceZh, String leaderEn, String leaderZh, String lat,
            String lon) {
        this.id = id;
        this.cityEn = cityEn;
        this.cityZh = cityZh;
        this.countryCode = countryCode;
        this.countryEn = countryEn;
        this.countryZh = countryZh;
        this.provinceEn = provinceEn;
        this.provinceZh = provinceZh;
        this.leaderEn = leaderEn;
        this.leaderZh = leaderZh;
        this.lat = lat;
        this.lon = lon;
    }
    @Generated(hash = 300452937)
    public CityInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCityEn() {
        return this.cityEn;
    }
    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }
    public String getCityZh() {
        return this.cityZh;
    }
    public void setCityZh(String cityZh) {
        this.cityZh = cityZh;
    }
    public String getCountryCode() {
        return this.countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getCountryEn() {
        return this.countryEn;
    }
    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }
    public String getCountryZh() {
        return this.countryZh;
    }
    public void setCountryZh(String countryZh) {
        this.countryZh = countryZh;
    }
    public String getProvinceEn() {
        return this.provinceEn;
    }
    public void setProvinceEn(String provinceEn) {
        this.provinceEn = provinceEn;
    }
    public String getProvinceZh() {
        return this.provinceZh;
    }
    public void setProvinceZh(String provinceZh) {
        this.provinceZh = provinceZh;
    }
    public String getLeaderEn() {
        return this.leaderEn;
    }
    public void setLeaderEn(String leaderEn) {
        this.leaderEn = leaderEn;
    }
    public String getLeaderZh() {
        return this.leaderZh;
    }
    public void setLeaderZh(String leaderZh) {
        this.leaderZh = leaderZh;
    }
    public String getLat() {
        return this.lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLon() {
        return this.lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }
}
