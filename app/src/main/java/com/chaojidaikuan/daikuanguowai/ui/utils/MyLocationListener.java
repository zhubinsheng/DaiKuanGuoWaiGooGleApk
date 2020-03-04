package com.chaojidaikuan.daikuanguowai.ui.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import java.io.IOException;
import java.util.List;

public class MyLocationListener implements LocationListener {
    private String latLongString;

    //当定位位置改变的调用的方法
    //Location : 当前的位置
    private Context context;
    public onBack onBack;

    public void setOnBack(MyLocationListener.onBack onBack) {
        this.onBack = onBack;
    }

    public MyLocationListener(Context context1) {
        context = context1;
    }
    @Override
    public void onLocationChanged(Location location) {
        float accuracy = location.getAccuracy();//获取精确位置
        double altitude = location.getAltitude();//获取海拔
        final double latitude = location.getLatitude();//获取纬度，平行
        final double longitude = location.getLongitude();//获取经度，垂直
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Address> addsList = null;
                Geocoder geocoder = new Geocoder(context);
                try {
                    addsList = geocoder.getFromLocation(latitude, longitude, 10);//得到的位置可能有多个当前只取其中一个
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (addsList != null && addsList.size() > 0) {
                    for (int i = 0; i < addsList.size(); i++) {
                        final Address ads = addsList.get(i);
                        latLongString = ads.getLocality();//拿到城市
                        latLongString = ads.getAddressLine(0);//拿到地址
                        if (latLongString.equals("")) {
                            onBack.onError();
                        } else {
                            onBack.backAdd(latLongString);
                        }
                    }
                }
            }
        }).start();
    }

    //当定位状态发生改变的时候调用的方式
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    //当定位可用的时候调用的方法
    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    //当定位不可用的时候调用的方法
    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
        onBack.onError();
    }

    public interface onBack {
        void backAdd(String location);

        void onError();
    }
}