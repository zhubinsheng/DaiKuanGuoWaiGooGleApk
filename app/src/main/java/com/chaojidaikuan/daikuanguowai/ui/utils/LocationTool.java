package com.chaojidaikuan.daikuanguowai.ui.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.provider.Settings;

import com.lykj.aextreme.afinal.utils.Debug;

public class LocationTool {
    public onBackLoCtion onBackLoCtion;
    Context context;

    public LocationTool(onBackLoCtion onBackLoCtion1, Context constants) {
        this.onBackLoCtion = onBackLoCtion1;
        this.context = constants;
    }

    LocationManager locationManager;

    @SuppressLint("MissingPermission")
    public void startLocation() {
        locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        boolean gpsIsOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsIsOpen) {
            // 转到手机设置界面，用户设置GPS
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return;
        }
        Debug.e("-------通过此处");
        myLocationListener = new MyLocationListener(context);
        myLocationListener.setOnBack(new MyLocationListener.onBack() {
            @Override
            public void backAdd(String location) {
                locationManager.removeUpdates(myLocationListener);
                onBackLoCtion.backAdd(location);
            }

            @Override
            public void onError() {
                onBackLoCtion.onError();
            }
        });
        locationManager.requestLocationUpdates("network", 0, 0, myLocationListener);
    }

    MyLocationListener myLocationListener;

    public interface onBackLoCtion {
        void backAdd(String location);

        void onError();
    }

    private String mLongitude = ""; // 经度
    private String mLatitude = ""; // 维度

    private void startLocation1() {
        // 为获取地理位置信息时设置查询条件 是按GPS定位还是network定位
        String bestProvider = getProvider();
//        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(bestProvider);
//        mLongitude = String.valueOf(location.getLongitude());
//        mLatitude = String.valueOf(location.getLatitude());
    }

    /**
     * 定位查询条件
     * 返回查询条件 ，获取目前设备状态下，最适合的定位方式
     */
    private String getProvider() {
        // 构建位置查询条件
        Criteria criteria = new Criteria();
        // 设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
        //Criteria.ACCURACY_FINE,当使用该值时，在建筑物当中，可能定位不了,建议在对定位要求并不是很高的时候用Criteria.ACCURACY_COARSE，避免定位失败
        // 查询精度：高
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        // 设置是否要求速度
        criteria.setSpeedRequired(false);
        // 是否查询海拨：否
        criteria.setAltitudeRequired(false);
        // 是否查询方位角 : 否
        criteria.setBearingRequired(false);
        // 是否允许付费：是
        criteria.setCostAllowed(false);
        // 电量要求：低
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        // 返回最合适的符合条件的provider，第2个参数为true说明 , 如果只有一个provider是有效的,则返回当前provider
        return locationManager.getBestProvider(criteria, true);
    }
}
