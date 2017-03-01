package com.example.why.xixiweather.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.why.xixiweather.R;
import com.example.why.xixiweather.view.MainActivity;

import java.util.List;

/**
 * Created by why on 17-2-7.
 */

public class ExpandableCityAdapter extends BaseExpandableListAdapter {


    private Context mContext;
    private List<String> mProvinces;
    private List<List<String>> mCities;

    public ExpandableCityAdapter(Context context, List<String> provinces, List<List<String>> cities) {
        mContext = context;
        mProvinces = provinces;
        mCities = cities;
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mCities.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ProvinceViewHolder provinceViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expandable_province_item,parent,false);
            provinceViewHolder = new ProvinceViewHolder();
            provinceViewHolder.textView = (TextView)convertView.findViewById(R.id.expandable_tv_province);
            convertView.setTag(provinceViewHolder);
        }else {
            provinceViewHolder = (ProvinceViewHolder)convertView.getTag();
        }

        provinceViewHolder.textView.setText(mProvinces.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        CityViewHolder cityViewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expandable_city_item,parent,false);
            cityViewHolder = new CityViewHolder();
            cityViewHolder.textView = (TextView)convertView.findViewById(R.id.expandable_tv_city);
            convertView.setTag(cityViewHolder);
        }else {
            cityViewHolder = (CityViewHolder)convertView.getTag();
        }

        cityViewHolder.textView.setText(mCities.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mProvinces.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return mProvinces.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mCities.get(groupPosition).size();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    static class ProvinceViewHolder{
        TextView textView;
    }

    static class CityViewHolder{
        TextView textView;
    }
}
