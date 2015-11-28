package team.house.cn.HuoseApp.activity;


import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.adapter.ServiceTypeAdapter;
import team.house.cn.HuoseApp.application.HouseApplication;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.CityUtil;
import team.house.cn.HuoseApp.utils.DialogUtil;
import team.house.cn.HuoseApp.utils.PreferenceUtil;
import team.house.cn.HuoseApp.utils.UserUtil;


/**
 * Created by kenan on 2015/10/15.
 */
public class MainActivity extends BaseActivity {
    private GridView mGridViewServiceType;
    private static final String TAG = "MainActivity";
    private final int mChooseCityRequestCode = 1;
    private String realCityName;
    private String chooseCityName;
    private double lat;
    private double lon;

    public LocationClient mLocationClient;
    private BDLocationListener mLocationListener;

    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_mian);
        mGridViewServiceType = (GridView) findViewById(R.id.gv_servicetype);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UserUtil.getUseridFromSharepreference() == 0){
            mRightView.setText("登陆");
        } else {
            mRightView.setText("退出");
        }

        chooseCityName = CityUtil.getCityName(AppConfig.Preference_ChooseCityNameFromService);
        mCityView.setText(chooseCityName);
    }

    @Override
    protected void initData() {
        super.initData();
        SDKInitializer.initialize(this);
        InitLocation();
        startlocation();
        chooseCityName = CityUtil.getCityName(AppConfig.Preference_ChooseCityNameFromService);
        mCityView.setText(chooseCityName);
        //图片的文字标题
        String[] titles = new String[]
                {"钟点工", "长期钟点工", "保姆", "月嫂"};
        //图片ID数组
        int[] images = new int[]{
                R.drawable.clock, R.drawable.cala, R.drawable.peo,
                R.drawable.bot
        };
        ServiceTypeAdapter adapter = new ServiceTypeAdapter(titles, images, this);
        mGridViewServiceType.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mGridViewServiceType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent;
                int loginSate = UserUtil.getUseridFromSharepreference();
                if (loginSate == 0) {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                } else {
                    intent = new Intent(MainActivity.this, ReservationServiceActivity.class);
                    intent.putExtra("position", position + 1);
                }
                startActivity(intent);
            }
        });
        mCityView.setOnClickListener(this);
    }


    private void showCitynfo(){
        realCityName = CityUtil.getCityName(AppConfig.Preference_RealCityNameFromService);
        chooseCityName = CityUtil.getCityName(AppConfig.Preference_ChooseCityNameFromService);
        mCityView.setText(chooseCityName);
        if (!TextUtils.isEmpty(realCityName)){
            // 选择定位不一致 弹出对话框提示用户选择城市
            if (!realCityName.equals(chooseCityName)) {
                showCityDialog();
            }

        } else {
            // 当前定位城市没有开通 自动跳转到选择城市页面
            startCityListActivity();
        }

    }
    private void showCityDialog() {
        DialogUtil.getInstance().createAlertDialog(this, "定位城市与选择城市不一致", "是否切换城市?", "切换", null, "取消", null);
    }

    private void showRightText() {
        mRightView.setText("登陆");
    }
    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        if (v.getId() == R.id.tv_right) {
            if (mRightView.getText().toString().equals("登陆")) {
                this.startActivity(new Intent(this, LoginActivity.class));
            } else {
                UserUtil.exitLogin();
                onResume();

            }
        }
        if (v.getId() == R.id.tv_city){
            startCityListActivity();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == mChooseCityRequestCode) {

            }
        }
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        showRightText();
    }

    /**
     * getLocation from service and save
     */
    public void getLocationFromService(String locationname) {
//        String locationname = PreferenceUtil.getString(HouseApplication.getHuoYunApplicationContext(), AppConfig.Preference_LocaCityName);
        Map paraments = new HashMap<String, String>();
        paraments.put("city_name", locationname);
        BaseRequest.instance(MainActivity.this).doRequest(TAG, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_POSSTION, paraments, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                if (responseBean != null) {
                    int code = responseBean.getCode();
                    String msg = responseBean.getMsg();

                    if (code == 0) {
                        try {
                            JSONObject jsonData = new JSONObject(responseBean.getData());
                            int upid = jsonData.getInt("upid");
                            int id = jsonData.getInt("id");
                            String name = jsonData.getString("name");

                            CityUtil.saveCity(upid, id, name, AppConfig.Preference_RealCityNameFromService);

                            String chooseCityName = CityUtil.getCityName(AppConfig.Preference_ChooseCityNameFromService);
                            if (TextUtils.isEmpty(chooseCityName)) {
                                CityUtil.saveCity(upid, id, name, AppConfig.Preference_ChooseCityNameFromService);
                            }


                            showCitynfo();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            startCityListActivity();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        startCityListActivity();

                    }
                }

            }

            @Override
            public void failure(VolleyError error) {

                Toast.makeText(MainActivity.this, "定位失败,请手动选择城市信息!", Toast.LENGTH_SHORT).show();
                startCityListActivity();
            }

        });

    }

    /**
     * 跳转到城市列表页面
     */
    private void startCityListActivity() {
        this.startActivityForResult(new Intent(this, CityListActivity.class), mChooseCityRequestCode);
    }



    public void InitLocation() {
        mLocationClient = new LocationClient(this.getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度
        option.setScanSpan(1000 * 60 * 2); //设置发起定位请求的时间间隔为2分钟
        option.setOpenGps(true); // 设置是否打开gps，默认是不打开gps
        option.setAddrType("all");
        mLocationClient.setLocOption(option);
        mLocationListener = new BDLocationListener() {

            @Override
            public void onReceiveLocation(BDLocation location) {
                try {
                    lat = location.getLatitude();
                    lon = location.getLongitude();
                    // 网络异常，没有成功向服务器发起请求
                    if (location == null || lat == 0 || lon == 0 || 63 == location.getLocType()
                            || (location.getLocType() >= 162 && location.getLocType() <= 167)) {
                        startCityListActivity();
                    } else {
                        PreferenceUtil.putString(HouseApplication.getHuoYunApplicationContext(), AppConfig.Preference_LocaCityName, location.getCity());
                        getLocationFromService(location.getCity());
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
