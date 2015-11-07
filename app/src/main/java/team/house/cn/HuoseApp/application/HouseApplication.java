package team.house.cn.HuoseApp.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.HashMap;

import java.util.List;
import java.util.Map;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

//import im.amomo.volley.BuildConfig;
//import im.amomo.volley.toolbox.OkVolley;
import org.json.JSONException;
import org.json.JSONObject;

import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.CityUtil;
import team.house.cn.HuoseApp.utils.PreferenceUtil;


/**
 * Created by kenan on 2015/10/15.
 */
public class HouseApplication extends Application {
    public GeoCoder geoCoder = null;
    public LocationClient mLocationClient;
    private BDLocationListener mLocationListener;
    private double lat;
    private double lon;
    public static RequestQueue mQueue = null;
    private static Context mContext;// 全局context


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mQueue = Volley.newRequestQueue(this.getApplicationContext());

//        OkVolley.getInstance().init(this)
//                .setUserAgent(OkVolley.generateUserAgent(this))
//                .trustAllCerts();
//        VolleyLog.DEBUG = BuildConfig.DEBUG;
//        InitLocation();
//        initGeoCoder();
//        startlocation();


    }

    public static Context getHuoYunApplicationContext() {
        return mContext;
    }
    /**
     * 初始化 geoCoder
     */
    private void initGeoCoder() {
        geoCoder = GeoCoder.newInstance(); // geoCoder可能为空
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                // 获取经纬度地理位置
                List<PoiInfo> poiInfo = result.getPoiList();
                if (poiInfo != null && poiInfo.size() > 0) {
                    String cityName = poiInfo.get(0).city;
                    PreferenceUtil.putString(getHuoYunApplicationContext(), AppConfig.Preference_LocaCityName, cityName);
                    CityUtil.getLocationFromService();
//                    saveAddress(poiInfo.get(0).address, poiInfo.get(0).name);
                    return;
                }
                /*
                if (!StringUtils.isEmpty(result.getAddress())) {
                    AddressComponent addDetail = result.getAddressDetail();
                    setAddressInfo(addDetail);
                }*/
            }

            @Override
            public void onGetGeoCodeResult(GeoCodeResult arg0) {
                //根据地理位置获取经纬度
            }
        });
    }

    public void InitLocation() {
        mLocationClient = new LocationClient(this.getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度
        option.setScanSpan(1000 * 60 * 2); //设置发起定位请求的时间间隔为2分钟
        option.setOpenGps(true); // 设置是否打开gps，默认是不打开gps
        mLocationClient.setLocOption(option);
        mLocationListener = new BDLocationListener() {

            @Override
            public void onReceiveLocation(BDLocation location) {
                try {

//                    printLocalInfo(location);

                    lat = location.getLatitude();
                    lon = location.getLongitude();

     /*     lat = 23.024;
          lon = 113.134;*/
                    // 网络异常，没有成功向服务器发起请求
                    if (location == null || lat == 0 || lon == 0 || 63 == location.getLocType()
                            || (location.getLocType() >= 162 && location.getLocType() <= 167)) {
                    } else {


                        Map<String, String> latLng = new HashMap<String, String>();
                     /*   latLng.put(PreferenceHelper.KEY_LAT, lat + "");
                        latLng.put(PreferenceHelper.KEY_LON, lon + "");
                        PreferenceUtil.putStringArray(HuoYunApplication.this, latLng);*/

                        LatLng point = new LatLng(lat, lon);
                        ReverseGeoCodeOption rgco = new ReverseGeoCodeOption();
                        geoCoder.reverseGeoCode(rgco.location(point));

            /*
             * if (StringUtils.isEmpty(location.getAddrStr())) {
             *
             * LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
             *
             * ReverseGeoCodeOption rgco = new ReverseGeoCodeOption();
             * geoCoder.reverseGeoCode(rgco.location(point));
             *
             * } else {
             *
             * setAddressInfo(location); }
             */
                    }
                } catch (Exception e) {
                    Log.e("local", e.getMessage());
                }

            }

            @Override
            public void onReceivePoi(BDLocation bdLocation) {

            }
        };
        mLocationClient.registerLocationListener(mLocationListener);
    }

//    public void startGeoCoder() {
//        if (geoCoder != null && !geoCoder.) {
//            mLocationClient.start();
//        }
//    }
    public void startlocation() {
        if (mLocationClient != null && !mLocationClient.isStarted()) {
            mLocationClient.start();
        }
    }

    public void stopLocation() {

        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.stop();
        }

    }
}
