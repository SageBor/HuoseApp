package team.house.cn.HuoseApp.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import team.house.cn.HuoseApp.R;

/**
 * 首页
 */
public class MainPageFragment extends Fragment implements OnClickListener {

  private View mRootView;
  private ListView mCarsList;
  private LinearLayout mNoLocation;
  private LinearLayout mRequestloadingSuccess;

  private Handler mHandler;
  private RelativeLayout mNearbycarLayout;// 附近车辆布局
  private boolean isActive;// 是否在前端显示
  private TextView mTitle;
  private ImageButton mRightBtn;
  private DataGetterReceiver mDataGetterReceiver;// 监听网络变化
  private String activitiesList;//活动列表数据

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    if (mRootView == null) {
      mRootView = inflater.inflate(R.layout.layout_fragment_main, container, false);
      initView();
      initTileView();
    }
    initData(savedInstanceState);
    initEvent();
    ViewGroup parent = (ViewGroup) mRootView.getParent();
    if (parent != null) {
      parent.removeView(mRootView);
    }

    return mRootView;

  }

  private void initView() {
//    mLeftView = (DrawableCenterButton) mRootView.findViewById(R.id.left_btn);
//    mRightBtn = (ImageButton) mRootView.findViewById(R.id.right_btn);
//    mTitle = (TextView) mRootView.findViewById(R.id.title_text);
//    mCarsList = (ListView) mRootView.findViewById(R.id.list_mainfragment_content);
//    mNoLocation = (LinearLayout) mRootView.findViewById(R.id.layout_nolocation);
//    mRequestloadingSuccess = (LinearLayout) mRootView.findViewById(R.id.requestloading_success);
//    mRequestLoadingWeb = new RequestLoadingWeb(mRootView);
//    mNearbycarLayout = (RelativeLayout) mRootView.findViewById(R.id.r_fujincheliang);
//    mTxtCarNum = (RiseNumberTextView) mRootView.findViewById(R.id.txt_carnum);
  }

  private void initTileView() {
//    mTitle.setText(R.string.fill_order_title);
//    mLeftView.setDrawable(getResources().getDrawable(R.drawable.bg_choosecity_selector), 2);
  }

  private void initData(Bundle savedInstanceState) {
//    mCarTypeAdapter = new CarTypeAdapter(getActivity());
//    mCarsList.setAdapter(mCarTypeAdapter);
//    mDataGetterReceiver = new DataGetterReceiver();
//    mDataGetterReceiver.RegisterReceiver();
//    mHandler = new Handler();
//    mCarHelper = (CarHelper) HelperFactory.createHelper("CarHelper");
//    mCarHelper.initMembers(getActivity());
//    mCityHelper = (CityHelper) HelperFactory.createHelper("CityHelper");
//    mCityHelper.setServiceDataListener(this);
//    isActive = true;
//    if(savedInstanceState != null){
//      //restore data
//      activitiesList = savedInstanceState.getString("activitiesList");
//    }
    refreshData();
  }

  private void initEvent() {
//    mLeftView.setOnClickListener(this);
    mRightBtn.setOnClickListener(this);
    mCarsList.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        CarTypeBean cBean = (CarTypeBean) parent.getItemAtPosition(position);
        /*
         * int carId = cBean.getmCarId(); int cityId = CityHelper.BEIJING_CITY_ID; if (mLocalCity !=
         * null) { String cityIdStr = mLocalCity.getmCityId(); cityId = cityIdStr.equals("") ?
         * CityHelper.BEIJING_CITY_ID : Integer.parseInt(cityIdStr); }
         */
        // 重庆 - 金杯 - 提示用户“金杯车型中包含小型厢货”
        // if
        // (mCityHelper.isShowDialog(MainPageFragment.this.getActivity(),
        // carId, cityId)) {
        // showNotice(cBean);
        // } else {
//        startFillOrderActivty(cBean);
        // }
      }
    });

//    mRequestLoadingWeb.setAgainListener(new OnClickListener() {
//
//      @Override
//      public void onClick(View v) {
//        if (mRequestLoadingWeb.getStatus() == RequestLoadingWeb.STATUS_ERROR) {
//          refreshData();
//        }
//      }
//    });
    mNearbycarLayout.setOnClickListener(this);
  }

 @Override
public void onSaveInstanceState(Bundle outState) {
  super.onSaveInstanceState(outState);
  outState.putString("activitiesList", activitiesList);
}

//  private void startFillOrderActivty(CarTypeBean cBean) {
//    Intent intent = new Intent(getActivity(), FillOrderActivity.class);
//    // intent.putExtra("cx", cBean.getmCarId());
//    // intent.putExtra("isLowPriceCarry", cBean.getmIsLowPriceCarry() == 0 ?
//    // false : true);
//    // intent.putExtra("explainText", cBean.getmEwyqDetail());
//    intent.putExtra("carBean", cBean);
//    getActivity().startActivity(intent);
//  }

  private void refreshData() {
//    mLocalCity = mCityHelper.getCityBean(getActivity());// 获取历史选择的城市
//    // 发起定位
//    mCityHelper.getLocationCityFromServer(getActivity());
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
//    // 有用户主观选择的城市数据
//    if (data != null && (CityBean) data.getSerializableExtra("citybean") != null) {
//      CityBean location = (CityBean) data.getSerializableExtra("citybean");
//      if (!CityHelper.CityEquals(location, mLocalCity)) {
//        // 不相等
//        updateUI(location, false);
//      }
//    } else {
//      // 定位失败
//      updateUI(mLocalCity, true);
//    }
  }

  @Override
  public void onDestroy() {
    // if (mCarTypeAdapter != null) {
    // mCarTypeAdapter.destroyLoading();
    // }
    Picasso.with(getActivity()).cancelTag(this);
    mDataGetterReceiver.UnRegisterReceiver();
    super.onDestroy();
  }

  /**
   * 从服务端获取城市车型和价格信息，页面没有数据显示旋转等待框 有数据就不显示等待框
   *
   */
 /* private void getDataFromService(final CityBean city,final boolean hasHistoryData) {
    *//*
     * if (city == null) { statuesToError(); return; } Map<Object, Object> paramMap = new
     * HashMap<Object, Object>(); paramMap.put("realcityid", city.getmCityId());
     *//*
    if(hasHistoryData){
      //存在历史数据，则取服务器请求首页弹屏内容
      getActivityContent();
    }
    CommonPostTask getInfoTask =
        new CommonPostTask(getActivity(), APPConfig.URLS.URL_CARTYPE, null, new ResultCallBack() {

          @Override
          public void ComTaskResult(CommonBean bean) {
            try {
              if (bean.isNull() || bean.getCode() != 0) {
                statuesToError(hasHistoryData);
              } else {
                // 请求成功
                statuesToNormal();
                mLocalCity = city;
                mCityHelper.saveCityBean(mLocalCity, MainPageFragment.this.getActivity());
                // city 为 mLocalCity,则先加载本地数据，再异步更新
                mLeftView.setText(mLocalCity.getmCityName());
                getNearbyCars(mLocalCity);
                if(!hasHistoryData){
                  getActivityContent();
                }
                JSONArray jArray = (JSONArray) bean.getData().nextValue();
                String data = jArray.toString();
                if (jArray.length() == 0) {
                  // 未开通
                  mNoLocation.setVisibility(View.VISIBLE);
                  mRequestloadingSuccess.setVisibility(View.GONE);
                  clearDataBase();
                } else {
                  // 已开通
                  mRequestloadingSuccess.setVisibility(View.VISIBLE);
                  mNoLocation.setVisibility(View.GONE);
                  mCarTypeAdapter.clear();
                  for (int i = 0; i < jArray.length(); i++) {
                    JSONObject jCarTypeBean = jArray.getJSONObject(i);
                    mCarTypeAdapter.addItem(new CarTypeBean(jCarTypeBean));
                  }
                  mCarTypeAdapter.notifyDataSetChanged();
                  insertDataToDataBase(data);// 异步插入数据库
                }

              }
            } catch (Exception e) {
              statuesToError(hasHistoryData);
            }
          }
        });
    statuesToInLoading(hasHistoryData);
    getInfoTask.execute();
  }*/

  /**
   * 更新界面
   */
/*  private void updateUI(CityBean city, boolean isLocal) {
    if (city == null) {
      // 重新从服务器加载,显示异常
      mRequestLoadingWeb.statuesToError();
      return;
    }
    // 3.2添加 将选择城市进行存储
    mCityHelper.saveCityBean(city, this.getActivity());
    // city 为 mLocalCity,则先加载本地数据，再异步更新
    if (isLocal) {
      getDataFromLocalAndService(city);
    } else {// 直接从服务器读取
      boolean hasHistoryData = mCarTypeAdapter != null && !mCarTypeAdapter.isEmpty();
      getDataFromService(city,hasHistoryData);
    }
    isHasReadActive(city.getmCityId());
  }*/

  /**
   * 先从本地读取，然后从service端读取，并更新界面
   */
/*  private void getDataFromLocalAndService(CityBean city) {
    int size = getDataFromLocal();// 从本地读取数据并展示在界面上
    getDataFromService(city,size>0);// 然后从网络读取数据
  }*/

  @Override
  public void onResume() {
    super.onResume();
   /* APPYOUMENG.onPageStart("下单:MainPageFragment");
    if (!isActive) {
      // 在前端显示
      isActive = true;
      if (mLocalCity != null) {
        // 退回后台太久，再进入时，mLocalCity可能被系统回收！
        getNearbyCars(mLocalCity);
        APPYOUMENG.sendLog(getActivity(), mLocalCity.getmCityId());
      } else {
        APPYOUMENG.onEvent(getActivity(), "mLocalCity == null");
      }
    }
    CommonTools.showDialogWhenFisrtInApp(this.getActivity());*/
  }

  @Override
  public void onPause() {
    super.onPause();
//    APPYOUMENG.onPageEnd("下单:MainPageFragment");
  }

  @Override
  public void onStop() {
    super.onStop();
//    if (!CommonTools.isAppOnForeground(getActivity())) {
//      isActive = false;
//    }
  }

 /* *//***
   * 将数据插入本地数据库
   *//*
  private void insertDataToDataBase(String data) {
    String local_data = PreferenceHelper.getInstance().setContext(getActivity()).getCityMD5();
    String data_md5 = MD5.MD532(data);
    if (!local_data.equals(data_md5)) {
      PreferenceHelper.getInstance().setCityMD5(data_md5);
      mHandler.post(insertTask);// 异步插入数据库
    }
  }

  *//**
   * 当城市未开通的时候清空本地数据库
   *//*
  private void clearDataBase() {
    PreferenceHelper.getInstance().setCityMD5("");
    mCarHelper.clearData();
  }

  *//**
   * 从本地读数据，并展示在界面上
   *//*
  private int getDataFromLocal() {
    List<CarTypeBean> carList = mCarHelper.getDataFromLocal();
    mCarTypeAdapter.clear();
    mCarTypeAdapter.setmCarTypeBean(carList);
    mCarTypeAdapter.notifyDataSetChanged();
    if (mLocalCity != null) {
      mLeftView.setText(mLocalCity.getmCityName());
    }
    return carList.size();
  }

  *//**
   * 根据城市来决定是否显示附近车辆。
   *
   * @param cityBean
   *//*
  private void getNearbyCars(CityBean cityBean) {
    if (cityBean == null || !cityBean.showCarNumLayout()) {
      // 无城市信息或该城市不需要展示“附近车辆”
      mNearbycarLayout.setVisibility(View.GONE);
      return;
    }
    String lat = String.valueOf(cityBean.getLat());
    String lng = String.valueOf(cityBean.getLng());
    // 定位城市和用户选择的城市一致的时候，经纬度为当前用户定位的
    if (mCityHelper.LocalCityEqualsLocationCity(getActivity())) {
      PreferenceHelper.getInstance().setContext(getActivity());
      lat = PreferenceHelper.getInstance().getLat();
      lng = PreferenceHelper.getInstance().getLon();
    }
    // 需要展示“附近车辆”的城市
    Map<Object, Object> paramMap = new HashMap<Object, Object>();
    paramMap.put("cityid_selected", cityBean.getmCityId());
    paramMap.put("lat", lat);
    paramMap.put("lng", lng);
    CommonPostTask nearbyCarTask =
        new CommonPostTask(getActivity(), APPConfig.URLS.URL_GETNEARBYCARS, paramMap,
            new ResultCallBack() {

              @Override
              public void ComTaskResult(CommonBean bean) {
                if (!bean.isNull() && bean.getCode() == 0) {
                  // 请求成功。拿到附近车辆数量
                  try {
                    JSONObject jCarNum = (JSONObject) bean.getData().nextValue();
                    getNearbyCarHelper(mNearbycarLayout.getVisibility(), jCarNum.getInt("count"));
                  } catch (Exception e) {
                    e.printStackTrace();
                  }
                }
              }
            });
    nearbyCarTask.execute();
  }*/

//  private void getNearbyCarHelper(int nearbycarlayoutVisiblity, final int carnum) {
//    if (nearbycarlayoutVisiblity == View.VISIBLE) {
//      // 动画地从零增长
//      mTxtCarNum.withNumber(carnum).setDuration(1500).start();
//      return;
//    }
//    if (nearbycarlayoutVisiblity == View.GONE) {
//      // 准备工作
//      final ViewGroup.LayoutParams lp = mNearbycarLayout.getLayoutParams();
//      lp.height = 0;
//      mNearbycarLayout.setLayoutParams(lp);
//      mNearbycarLayout.setVisibility(View.VISIBLE);
//      // mTxtCarNum.setText(String.valueOf(carnum));
//      // 动画地展开布局
//      ValueAnimator animator =
//          ValueAnimator.ofInt(1, CommonTools.dip2px(getActivity(), 50)).setDuration(1000);
//      animator.addUpdateListener(new AnimatorUpdateListener() {
//
//        @Override
//        public void onAnimationUpdate(ValueAnimator value) {
//          lp.height = (Integer) value.getAnimatedValue();
//          mNearbycarLayout.setLayoutParams(lp);
//        }
//      });
//      animator.addListener(new AnimatorListenerAdapter() {
//        @Override
//        public void onAnimationEnd(Animator arg0) {
//          mTxtCarNum.withNumber(carnum).setDuration(1500).start();
//        }
//      });
//      animator.start();
//    }
//  }

  /**
   * 异步将网络数据插入数据库
   */
  Runnable insertTask = new Runnable() {

    @Override
    public void run() {
//      if (mCarTypeAdapter == null)
//        return;
//      List<CarTypeBean> cars = mCarTypeAdapter.getCarTypeBeans();
//      mCarHelper.clearAllAndInsert(cars);
    }
  };

  @Override
  public void onClick(View v) {

  }

  /**
   * 监听网络变化，当网络通顺时主动更新数据
   *
   * @author xiayong
   *
   */
  private class DataGetterReceiver {
    private IntentFilter mIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    private int lastNetType = 0;// 保存上一次网络状态，-1表示断网
    private Handler mHandler = new Handler() {
      @Override
      public void handleMessage(Message msg) {
        // mCityHelper.getLocationCity();
        // getNearbyCars(mLocalCity);
        if (msg.what == 0) {
          refreshData();
        } else if (msg.what == 1) {
//          getNearbyCars(mLocalCity);
        }
      };
    };
    private BroadcastReceiver mConnectivityChanged = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(ConnectivityManager.CONNECTIVITY_ACTION, intent.getAction())) {
          // 获得网络连接服务
          ConnectivityManager connManager =
              (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
          NetworkInfo info = connManager.getActiveNetworkInfo();
          if (info == null || !connManager.getBackgroundDataSetting()) {
            // 网络中断
            lastNetType = -1;
          } else if (info.isConnected() && info.isAvailable()) {
            // 网络良好
            if (lastNetType == -1) {
              // 上次为断网状态
              mHandler.sendEmptyMessage(0);
            }
            lastNetType = info.getType();
          }
          return;
        }
        if (TextUtils.equals(Intent.ACTION_USER_PRESENT, intent.getAction())) {
          // 手机从锁屏状态解锁
          mHandler.sendEmptyMessage(1);
          return;
        }
      }

    };

    /**
     * 构造函数
     */
    public DataGetterReceiver() {
      mIntentFilter.addAction(Intent.ACTION_USER_PRESENT);
    }

    public void RegisterReceiver() {
      try {
        getActivity().registerReceiver(mConnectivityChanged, mIntentFilter);
      } catch (Exception e) {

      }
    }

    public void UnRegisterReceiver() {
      try {
        getActivity().unregisterReceiver(mConnectivityChanged);
      } catch (Exception e) {

      }
    }
  }

 /* private void handleCityReveived(final CityBean locationcity) {
    if (locationcity == null || TextUtils.isEmpty(locationcity.getmCityName())) {
      // 定位失败
      if (mLocalCity == null) {
        // 不存在历史选择城市数据
        startCityLocationActivity();
        APPYOUMENG.sendLog(getActivity(), "");
      } else {
        // 存在历史选择城市数据
        updateUI(mLocalCity, true);
        APPYOUMENG.sendLog(getActivity(), mLocalCity.getmCityId());
      }
      CityHelper.saveLocationCityBean(getActivity(), new CityBean());// 定位失败，更新本地库
      return;
    }
    // 定位成功
    CityHelper.saveLocationCityBean(getActivity(), locationcity);// GPS定位的城市更新本地库
    // 与历史选择城市比较
    if (CityHelper.CityEquals(locationcity, mLocalCity) || CityHelper.isNullCity(mLocalCity)) {
      // 一致或历史城市是null
      // mLocalCity = locationcity;
      updateUI(locationcity, true);
      // mCityHelper.setCity(locationcity).saveCityBean(MainPageFragment.this.getActivity());
      APPYOUMENG.sendLog(getActivity(), locationcity.getmCityId());
    } else {
      APPYOUMENG.sendLog(getActivity(), locationcity.getmCityId());
      // 不一致，弹出对话框询问是否切换
      DialogUtil.getInstance().createAlertDiaog(getActivity(),
          "当前定位城市是" + locationcity.getmCityName() + ",是否切换", "确定",
          new DialogUtil.IPositiveButtonDialogListener() {

            @Override
            public void onPositiveButtonClicked(int requestCode) {
              // mCityHelper.setCity(locationcity).saveCityBean(MainPageFragment.this.getActivity());
              // mLocalCity = locationcity;
              updateUI(locationcity, false);
            }
          }, "取消", new DialogUtil.INegativeButtonDialogListener() {

            @Override
            public void onNegativeButtonClicked(int requestCode) {
              // mCityHelper.setCity(mLocalCity);
              updateUI(mLocalCity, true);
            }
          });
    }
  }
*/
 /* @Override
  public void onServiceDataReceived(CommonBean bean) {

    if (bean.urlEquals(APPConfig.URLS.URL_CITYINFO)) {
      CityBean locationCity = null;
      if (!bean.isNull() && bean.getCode() == 0) {
        try {
          JSONObject jCity = (JSONObject) bean.getData().nextValue();
          locationCity = new CityBean(jCity);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      handleCityReveived(locationCity);
    }
  }

  @Override
  public void onClick(View v) {
    int v_id = v.getId();
    switch (v_id) {
    // 切换城市界面
      case R.id.left_btn:
        startCityLocationActivity();
        break;
      case R.id.r_fujincheliang:
        startNearByCarsActivity();
        break;
      case R.id.right_btn:
        if(!TextUtils.isEmpty(activitiesList)){
          //ensure data available
          ActivityPopupDialog.newInstance(activitiesList).showAllowingStateLoss(getFragmentManager(), "ActivityPopupDialog");
          //友盟统计
          APPYOUMENG.onEvent(getActivity(), APPYOUMENG.PopUpScreen);
        }
        break;
      default:
        break;
    }
  }*/

  /**
   * 跳转到附近车辆页面
   *//*
  private void startNearByCarsActivity() {
    Intent intent = new Intent(getActivity(), NearbyCarsActivity.class);
    CityBean city = new CityBean(mLocalCity);
    if (mCityHelper.LocalCityEqualsLocationCity(getActivity())) {
      // 当前城市和定位城市相等
      PreferenceHelper.getInstance().setContext(getActivity());
      String lat = PreferenceHelper.getInstance().getLat();
      String lng = PreferenceHelper.getInstance().getLon();
      String address = PreferenceHelper.getInstance().getAddress();
      String addressDetails = PreferenceHelper.getInstance().getAddressDetails();
      if (!TextUtils.isEmpty(lat) && !TextUtils.isEmpty(lng) && !TextUtils.isEmpty(address)) {
        city.setAddress(address);
        city.setAddressDetails(addressDetails);
        city.setLatLng(new LatLngPoints(lat, lng));
        // city.setLat(Double.parseDouble(lat));
        // city.setLng(Double.parseDouble(lng));
      }
    }
    intent.putExtra("city", city);
    startActivity(intent);
  }*/

  /*private void startCityLocationActivity() {
    Intent intent = new Intent(getActivity(), CityLocationActivity.class);
    startActivityForResult(intent, CityLocationActivity.CITY_CODE_MAINFRAGMENT);
    getActivity().overridePendingTransition(R.anim.slide_in_bottom, android.R.anim.fade_out);

  }

  *//**
   * 正在等待
   *//*
  private void statuesToInLoading(boolean hasHistoryDaya) {
    if (hasHistoryDaya) {
      // 有数据
      mRequestLoadingWeb.statuesToNormal();
    } else {
      mRequestLoadingWeb.statuesToInLoading();
    }
  }
*/
 /* *//**
   * 错误页面
   *//*
  private void statuesToError(boolean hasHistoryData) {
    if (hasHistoryData) {
      // 有数据
      mRequestLoadingWeb.statuesToNormal();
      if (!CommonTools.isNetWorkDialogShowing()) {
        // 不能和显示网络异常的对话框同时显示
        CommonTools.showcustomAlert(getActivity(), getActivity().getString(R.string.net_work_fail));
      }
    } else {
      mRequestLoadingWeb.statuesToError();
    }
  }

  private void statuesToNormal() {
    mRequestLoadingWeb.statuesToNormal();
  }

  *//**
   * 从服务器获取首页弹屏内容
   *//*
  private void getActivityContent() {
//    Map<Object, Object> paramMap = new HashMap<Object, Object>();
    CommonGetTask getActivityContentTask = new CommonGetTask(getActivity(), null, APPConfig.URLS.URL_GET_FIRSTPAGE_ICON, new ResultCallBack() {

      @Override
      public void ComTaskResult(CommonBean bean) {
        if(!bean.isNull() && bean.getCode() == 0){
          //成功
          try {
            JSONObject jsonData = (JSONObject) bean.getData().nextValue();
            int popDialogFlag = jsonData.getInt("is_flag");
            activitiesList = jsonData.getString("firstIconDtoList");
            JSONArray jsonViewPager = new JSONArray(activitiesList);
            if(jsonViewPager != null && jsonViewPager.length() >0){
              //至少有一页
              if(popDialogFlag == 1){
                //友盟统计
                APPYOUMENG.onEvent(getActivity(), APPYOUMENG.PopUpScreen_Auto);
                ActivityPopupDialog.newInstance(activitiesList).showAllowingStateLoss(getFragmentManager(), "ActivityPopupDialog");
              }
              mRightBtn.setVisibility(View.VISIBLE);
            }
          } catch (JSONException e) {
            mRightBtn.setVisibility(View.INVISIBLE);
            activitiesList = "";
          }
        }else{
          //获取数据失败
          mRightBtn.setVisibility(View.INVISIBLE);
          activitiesList = "";
        }
      }
    });
    getActivityContentTask.execute();

  }

  private void isHasReadActive(final String cityId) {
    Map<Object, Object> paramMap = new HashMap<Object, Object>();
    paramMap.put("realcityid", cityId);
    CommonPostTask getInfoTask =
        new CommonPostTask(getActivity(), APPConfig.URLS.URL_ISHASREADACTIVE, paramMap,
            new ResultCallBack() {
              public void ComTaskResult(CommonBean bean) {
                if (!bean.isNull() && bean.getCode() == 0) {

                  // 请求成功
                  try {
                    JSONObject jsonData = (JSONObject) bean.getData().nextValue();
                    if (jsonData != null) {
                      boolean have_unread = JSONUtils.getBoolean(jsonData, "have_unread", true);
                      if (have_unread) {
                        ((com.wuba.huoyun.activity.FragmentTabPager) MainPageFragment.this
                            .getActivity()).changeTabIcon(2, true);
                      } else {
                        ((com.wuba.huoyun.activity.FragmentTabPager) MainPageFragment.this
                            .getActivity()).changeTabIcon(2, false);
                      }
                      ((com.wuba.huoyun.activity.FragmentTabPager) MainPageFragment.this
                          .getActivity()).refreshTabs(2);
                    }
                  } catch (Exception e) {
                    e.printStackTrace();
                  }
                }
              }
            });
    getInfoTask.execute();
  }

  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (!hidden) {
      if (mLocalCity != null) {
        isHasReadActive(mLocalCity.getmCityId());
      }
    }
  }*/


}
