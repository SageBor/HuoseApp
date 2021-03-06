package team.house.cn.HuoseApp.activity;


import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.adapter.ServiceTypeAdapter;
import team.house.cn.HuoseApp.adapter.VerticalViewPageAdapter;
import team.house.cn.HuoseApp.application.HouseApplication;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.bean.CityBean;
import team.house.cn.HuoseApp.bean.NoticeBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.ActivityManager;
import team.house.cn.HuoseApp.utils.CityUtil;
import team.house.cn.HuoseApp.utils.DialogUtil;
import team.house.cn.HuoseApp.utils.DialogUtil.INegativeButtonDialogListener;
import team.house.cn.HuoseApp.utils.JSONUtils;
import team.house.cn.HuoseApp.utils.PreferenceUtil;
import team.house.cn.HuoseApp.utils.UserUtil;
import team.house.cn.HuoseApp.views.VerticalViewPager.CycleViewPageHandler;
import team.house.cn.HuoseApp.views.VerticalViewPager.VerticalViewPager;


/**
 * Created by kenan on 2015/10/15.
 */
public class MainActivity extends BaseActivity {
    private GridView mGridViewServiceType;
    private VerticalViewPager verticalViewPager;
    private VerticalViewPageAdapter verticalViewPageAdapter;
    private List<NoticeBean> noticeBeanList;
    private int WHEEL_SIGNAL = 100; // 转动信号
    private int time = 5000; // 轮播时间
    private int currentPosition = 0; // 轮播当前位置
    private boolean isWheel = false; // 是否轮播
    private static final String TAG = "MainActivity";
    private final int mChooseCityRequestCode = 1;
    private String realCityName;
    private String chooseCityName;
    private double lat;
    private double lon;

    public LocationClient mLocationClient;
    private BDLocationListener mLocationListener;
    private CycleViewPageHandler.UnleakHandler handler;


    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_mian);
        mGridViewServiceType = (GridView) findViewById(R.id.gv_servicetype);
        verticalViewPager = (VerticalViewPager) findViewById(R.id.vp_notice);
        verticalViewPageAdapter = new VerticalViewPageAdapter(this);
        verticalViewPager.setAdapter(verticalViewPageAdapter);
        verticalViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mMain.setSelected(true);
        if (noticeBeanList.size() == 0) {
            getNoticeFromService();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UserUtil.getUseridFromSharepreference() == 0){
            mRightView.setText("登陆");
        } else {
            mRightView.setText("退出");
        }
//        showCityDialog();
        chooseCityName = CityUtil.getCityName(AppConfig.Preference_ChooseCityNameFromService);
        mCityView.setText(chooseCityName);
    }

    @Override
    protected void initData() {
        super.initData();
        mMain.setSelected(true);
//        mMain.set
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
        noticeBeanList = new ArrayList<NoticeBean>();
        handler = new CycleViewPageHandler.UnleakHandler(this) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int count = noticeBeanList.size();
                if (msg.what == WHEEL_SIGNAL && count != 0) {
                    currentPosition = verticalViewPager.getCurrentItem();
//                    if (!viewPager.getScrollX()) {
                    int max = count + 1;
                    int position = (currentPosition + 1) % count;
                    verticalViewPager.setCurrentItem(position, true);
                    if (position == max) { // 最后一页时回到第一页
                        verticalViewPager.setCurrentItem(1, false);
                    }
//                    }
                    handler.removeCallbacks(runnable);
                    handler.postDelayed(runnable, time);
                }


            }
        };

        getNoticeFromService();
//        ifmain=1;
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
                    startActivity(intent);
                } else {
                    CityBean mCityBean = CityUtil.getCity(AppConfig.Preference_ChooseCityNameFromService);
                    if(mCityBean==null||mCityBean!=null&&mCityBean.getCityName()==null||"".equals(mCityBean!=null&&mCityBean.getCityName()==null)){
                        DialogUtil.getInstance().createAlertDialog(MainActivity.this, "您还没有选择服务城市", "是否选择城市?", "确定", new DialogUtil.IPositiveButtonDialogListener() {
                            @Override
                            public void onPositiveButtonClicked(int requestCode) {
                                startCityListActivity();
                            }
                        }, "取消", new INegativeButtonDialogListener() {
                            @Override
                            public void onNegativeButtonClicked(int requestCode) {

                            }
                        });
                    }else{
                        intent = new Intent(MainActivity.this, ReservationServiceActivity.class);
                        intent.putExtra("position", position + 1);
                        startActivity(intent);
                    }

                }

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
        DialogUtil.getInstance().createAlertDialog(this, "定位城市与选择城市不一致", "是否切换城市?", "切换", new DialogUtil.IPositiveButtonDialogListener() {
            @Override
            public void onPositiveButtonClicked(int requestCode) {
                startCityListActivity();
            }
        }, "取消", new INegativeButtonDialogListener() {
            @Override
            public void onNegativeButtonClicked(int requestCode) {

            }
        });
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
                Toast.makeText(MainActivity.this, "您已退出", Toast.LENGTH_SHORT).show();
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
    final Runnable runnable = new Runnable() {

        @Override
        public void run() {
            if (this != null && isWheel) {
                // viewpager依旧静止的话开始滑动
//                if (!isScrollingOrPressed) {
                handler.sendEmptyMessage(WHEEL_SIGNAL);
//                } else {
//                    handler.removeCallbacks(runnable);
//                }
            }
        }
    };
    /**
     * 设置是否轮播，默认不轮播,轮播一定是循环的
     *
     * @param isWheel 是否滚动
     */
    public void setWheel(boolean isWheel) {
        this.isWheel = isWheel;
        if (isWheel) {
//            isCycle = true;
            handler.postDelayed(runnable, time);
        }
    }
    private void getNoticeFromService() {
        BaseRequest.instance().doRequest(TAG, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_NOTICE, null, new BaseResponse() {

            @Override
            public void successful(ResponseBean responseBean) {
                if (responseBean != null) {
                    int code = responseBean.getCode();
                    String msg = responseBean.getMsg();

                    if (code == 0) {
                        try {
                            JSONArray data = new JSONArray(responseBean.getData());
                            if (data != null && data.length() > 0) {
                                noticeBeanList.clear();
                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject noticeJson = data.getJSONObject(i);
                                    String art_title = JSONUtils.getString(noticeJson, "art_title", "");
                                    String art_source = JSONUtils.getString(noticeJson, "art_source", "");
                                    NoticeBean noticeBean = new NoticeBean();
                                    noticeBean.setArt_source(art_source);
                                    noticeBean.setArt_title(art_title);
                                    noticeBeanList.add(noticeBean);
                                }
                                if (noticeBeanList.size() > 0) {
                                    verticalViewPageAdapter.setData(noticeBeanList);
                                    setWheel(true);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //  startCityListActivity();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        //   startCityListActivity();

                    }
                }
            }

            @Override
            public void failure(VolleyError error) {

            }
        });
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
                          //  startCityListActivity();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                     //   startCityListActivity();

                    }
                }

            }

            @Override
            public void failure(VolleyError error) {

                Toast.makeText(MainActivity.this, "定位失败,请手动选择城市信息!", Toast.LENGTH_SHORT).show();
              //  startCityListActivity();
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
//        option.setScanSpan(1000 * 60 * 2); //设置发起定位请求的时间间隔为2分钟
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
//                    Log.e("local", e.getMessage());
                }
            }

            @Override
            public void onReceivePoi(BDLocation bdLocation) {

            }
        };
        mLocationClient.registerLocationListener(mLocationListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocation();
        BaseRequest.instance().cancelRequst(TAG);
    }

    @Override
    public void onBackPressed() {
        DialogUtil.getInstance().createAlertDiaog(this, "是否退出" + getString(R.string.app_name), "确定",
                new DialogUtil.IPositiveButtonDialogListener() {

                    @Override
                    public void onPositiveButtonClicked(int requestCode) {
                        ActivityManager.getInstance().exist();
                    }
                }, "取消");
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
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                // 将系统当前的时间赋值给exitTime
                exitTime = System.currentTimeMillis();
            } else {
                exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitApp() {
        this.finish();
        System.exit(0);
    }
}
