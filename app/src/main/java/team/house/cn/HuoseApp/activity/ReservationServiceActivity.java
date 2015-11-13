package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.anton46.stepsview.StepsView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.adapter.ServiceContentAdaper;
import team.house.cn.HuoseApp.adapter.ServiceModelAdapter;
import team.house.cn.HuoseApp.adapter.ServiceToolsAdapter;
import team.house.cn.HuoseApp.adapter.ServiceWeekAdapter;
import team.house.cn.HuoseApp.adapter.ToggleButton_ViewHolder;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.bean.ChoosePriceBean;
import team.house.cn.HuoseApp.bean.CityBean;
import team.house.cn.HuoseApp.bean.CommitReservationServiceBean;
import team.house.cn.HuoseApp.bean.HourBean;
import team.house.cn.HuoseApp.bean.ServiceContentBean;
import team.house.cn.HuoseApp.bean.ServiceEmploymentMonthBean;
import team.house.cn.HuoseApp.bean.ServiceModelBean;
import team.house.cn.HuoseApp.bean.ServiceBseTimeBean;
import team.house.cn.HuoseApp.bean.ServiceToolsBean;
import team.house.cn.HuoseApp.bean.ServiceTryDayBean;
import team.house.cn.HuoseApp.bean.ServiceWeekBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.proviews.DateTimeDialog;
import team.house.cn.HuoseApp.utils.CityUtil;

/**
 * Created by kenan on 15/11/7.
 * 预约服务
 */
public class ReservationServiceActivity extends BaseActivity {
    private CityBean mCityBean;
    private CommitReservationServiceBean mCommitReservationServiceBean;
    private int mPosition;
    private int mProvinceId;
    private int mCityId;
    private int indus_id; // 服务小类ID
    private RelativeLayout mServiceAddressRelativeLayout;
    private TextView mServiceAddressTextView;
    private GridView mServiceContentGridView; // 服务内容
    private ServiceContentAdaper mServiceContentAdaper; // 服务内容适配器
    private List<ServiceContentBean> mServiceContentBeanList; // 服务内容集合

    private LinearLayout mSerivceWeekLinearlayout;// 服务频率父容器
    private GridView mServiceWeekGridView; // 服务频率
    private ServiceWeekAdapter mServiceWeekAdapter;// 服务频率适配器
    private List<ServiceWeekBean> mServiceWeekBeanList; // 服务频率集合

    private LinearLayout mServiceModelLinearLayout;// 服务模式父容器
    private GridView mServiceModelGridView;// 服务模式
    private ServiceModelAdapter mServiceModelAdapter; //服务模式适配器
    private List<ServiceModelBean> mServiceModelBeanList; //服务模式数据集合

    private TextView mServiceTimeTextView;// 服务起步时间及起步价
    private List<ServiceBseTimeBean> mServiceTimeBeanList;
    private LinearLayout mServiceTryTimeLinearLayout;// 试用时间

    private RelativeLayout mServiceStartTimeRelativeLayout; // 开始时间
    private List<HourBean> startHourBeanList;// 小时工 长时工
    private LinearLayout mServiceTimeLinearLayout;// 雇佣时间
    private List<ServiceEmploymentMonthBean> mServiceEmploymentMonthBeanList;
    private LinearLayout mServiceOverTimeLinearLayout;// 结束时间
    private List<HourBean> endHourBeanList;// 小时工 长时工
    private StepsView stepsView;
    private List<ChoosePriceBean> mChoosePriceBeanList;// 自选价格集合

    private LinearLayout mServiceToolsLinearLayout;// 随手带保洁用品 父容器
    private GridView mServiceToolsGradView; // 随手带保洁用品
    private ServiceToolsAdapter mServiceToolsAdapter; // 保洁用品适配器
    private List<ServiceToolsBean> mServiceToolsBeanList;// 保洁用品集合

    private List<ServiceTryDayBean> serviceTryDayBeanList;
    private ServiceContentBean serviceContentBean;


    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_reservationservice);
        mServiceAddressRelativeLayout = (RelativeLayout) findViewById(R.id.rl_serviceAddress);
        mServiceAddressTextView = (TextView) findViewById(R.id.tv_serviceAddress);
        mServiceContentGridView = (GridView) findViewById(R.id.gv_serviceInfo);
        mServiceContentBeanList = new ArrayList<ServiceContentBean>();
        mServiceContentAdaper = new ServiceContentAdaper(mServiceContentBeanList, this);
        mServiceContentGridView.setAdapter(mServiceContentAdaper);

        mSerivceWeekLinearlayout = (LinearLayout) findViewById(R.id.ll_serviceweek);
        mServiceWeekGridView = (GridView) findViewById(R.id.gv_serviceweek);
        mServiceWeekBeanList = new ArrayList<ServiceWeekBean>();
        mServiceWeekAdapter = new ServiceWeekAdapter(mServiceWeekBeanList, this);
        mServiceWeekGridView.setAdapter(mServiceWeekAdapter);

        mServiceModelLinearLayout = (LinearLayout) findViewById(R.id.ll_serviceModel);
        mServiceModelGridView = (GridView) findViewById(R.id.gv_serviceModel);
        mServiceModelBeanList = new ArrayList<ServiceModelBean>();
        mServiceModelAdapter = new ServiceModelAdapter(mServiceModelBeanList, this);
        mServiceModelGridView.setAdapter(mServiceModelAdapter);

        mServiceTimeTextView = (TextView) findViewById(R.id.tv_serviceTime);
        mServiceTimeBeanList = new ArrayList<ServiceBseTimeBean>();

        mServiceTryTimeLinearLayout = (LinearLayout) findViewById(R.id.ll_serviceTryTime);
        mServiceStartTimeRelativeLayout = (RelativeLayout) findViewById(R.id.rl_serviceStarTime);
        mServiceTimeLinearLayout = (LinearLayout) findViewById(R.id.ll_servicereTime);
        mServiceOverTimeLinearLayout = (LinearLayout) findViewById(R.id.ll_serviceOverTime);
        stepsView = (StepsView) findViewById(R.id.stepsView);
        mChoosePriceBeanList = new ArrayList<ChoosePriceBean>();

        mServiceToolsLinearLayout = (LinearLayout) findViewById(R.id.ll_servicetools);
        mServiceToolsGradView = (GridView) findViewById(R.id.gv_servicetools);
        mServiceToolsBeanList = new ArrayList<ServiceToolsBean>();
        mServiceToolsAdapter = new ServiceToolsAdapter(mServiceToolsBeanList, this);
        mServiceToolsGradView.setAdapter(mServiceToolsAdapter);


    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        mCommitReservationServiceBean = new CommitReservationServiceBean();
        mCityBean = CityUtil.getCity(AppConfig.Preference_ChooseCityNameFromService);
        mProvinceId = mCityBean.getProvinceId();
        mCityId = mCityBean.getCityId();
        mPosition = intent.getIntExtra("position", 1);
        mCommitReservationServiceBean.setIndus_pid(mPosition);
        showViewBymPosition();
        getConfigInfoFromService();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mServiceAddressRelativeLayout.setOnClickListener(this);
//        mServiceContentGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                ToggleButton_ViewHolder viewHolder = (ToggleButton_ViewHolder) view.getTag();
//                if (viewHolder.mToggleButton.isChecked()) {
//
//                }
//
//            }
//        });
        mServiceTryTimeLinearLayout.setOnClickListener(this);
        mServiceStartTimeRelativeLayout.setOnClickListener(this);
        mServiceTimeLinearLayout.setOnClickListener(this);
        mServiceOverTimeLinearLayout.setOnClickListener(this);
    }

//    private void updateServiceContent(int mPosition) {
//
//        for (int j = 0; j < mServiceModelBeanList.size(); j++) {
//            if (j != mPosition) {
//                mServiceContentBeanList.get(j).setIsChecked(false);
//            }
//
//        }
//        mServiceContentAdaper.notifyDataSetChanged();
//    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        int viewId = v.getId();
        if (viewId == R.id.tv_serviceAddress) {
            // 进入选择地址页面
            this.startActivityForResult(new Intent(this, ChooseAddressActivity.class), 1);
        }
        // 试用时间
        if (viewId == R.id.ll_serviceTryTime) {
            if (serviceTryDayBeanList != null) {
                DateTimeDialog dateTimeDialog = new DateTimeDialog(this, serviceTryDayBeanList, null);
                dateTimeDialog.init();
                dateTimeDialog.setOnDateTimeChanged(new DateTimeDialog.DateTimeChange() {

                    @Override
                    public void onDateTimeChange(String date, String time, int dayItem, int hourItem) {

                    }
                });
            } else {
                Toast.makeText(this, "数据获取中", Toast.LENGTH_SHORT).show();
            }
        }
        //开始时间
        if (viewId == R.id.rl_serviceStarTime) {
            if (startHourBeanList != null) {
                DateTimeDialog dateTimeDialog = new DateTimeDialog(this, startHourBeanList);
                dateTimeDialog.init();
                dateTimeDialog.setOnDateTimeChanged(new DateTimeDialog.DateTimeChange() {

                    @Override
                    public void onDateTimeChange(String date, String time, int dayItem, int hourItem) {
                        String startTime = date + time + ":00";
                    }
                });
            } else {
                Toast.makeText(this, "数据获取中", Toast.LENGTH_SHORT).show();
            }
        }
        // 雇佣时间
        if (viewId == R.id.ll_servicereTime) {
            if (mServiceEmploymentMonthBeanList != null) {
                DateTimeDialog dateTimeDialog = new DateTimeDialog(this, mServiceEmploymentMonthBeanList, 0);
                dateTimeDialog.init();
                dateTimeDialog.setOnDateTimeChanged(new DateTimeDialog.DateTimeChange() {

                    @Override
                    public void onDateTimeChange(String date, String time, int dayItem, int hourItem) {

                    }
                });
            } else {
                Toast.makeText(this, "数据获取中", Toast.LENGTH_SHORT).show();
            }
        }

        //结束时间
        if (viewId == R.id.ll_serviceOverTime) {
            if (endHourBeanList != null) {
                DateTimeDialog dateTimeDialog = new DateTimeDialog(this, endHourBeanList);
                dateTimeDialog.init();
                dateTimeDialog.setOnDateTimeChanged(new DateTimeDialog.DateTimeChange() {

                    @Override
                    public void onDateTimeChange(String date, String time, int dayItem, int hourItem) {
                        String endTime = date + time + ":00";
                    }
                });
            } else {
                Toast.makeText(this, "数据获取中", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            String addressId = data.getStringExtra("address_id");
            String addressInfo = data.getStringExtra("address_info");
            if (TextUtils.isEmpty(addressId) && TextUtils.isEmpty(addressInfo)) {
                mServiceAddressTextView.setText(addressInfo);
            }
        }
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("预约服务");
    }

    private void showViewBymPosition() {
        switch (mPosition) {

            // 长期工
            case 2:
                mSerivceWeekLinearlayout.setVisibility(View.VISIBLE);
                // 小时工
            case 1:
                mServiceOverTimeLinearLayout.setVisibility(View.VISIBLE);
                mServiceToolsLinearLayout.setVisibility(View.VISIBLE);
                break;
            // 保姆
            case 3:
                // 月嫂
            case 4:
                mServiceModelLinearLayout.setVisibility(View.VISIBLE);
                mServiceTryTimeLinearLayout.setVisibility(View.VISIBLE);
                mServiceTimeLinearLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 从服务端获取预约服务配置数据
     */
    private void getConfigInfoFromService() {
        Map paraments = new HashMap<String, String>();
        paraments.put("dis_upid", mProvinceId);
        paraments.put("dis_id", mCityId);
        paraments.put("indus_pid", mPosition);
        BaseRequest.instance().doRequest(Request.Method.GET, AppConfig.WebHost + AppConfig.Urls.URL_GET_CONFIG, paraments, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                if (responseBean != null) {
                    int code = responseBean.getCode();
                    String msg = responseBean.getMsg();

                    if (code == 0) {
                        try {
                            JSONObject jsonData = new JSONObject(responseBean.getData());
                            // 服务内容
                            JSONArray indus = jsonData.getJSONArray("indus");// 服务小类
                            if (indus != null && indus.length() > 0) {
                                for (int i = 0; i < indus.length(); i++) {
                                    JSONObject serviceContentJson = indus.getJSONObject(i);
                                    ServiceContentBean serviceContentBean = new ServiceContentBean(serviceContentJson.getInt("indus_id"), serviceContentJson.getString("indus_name"), false);
                                    mServiceContentBeanList.add(serviceContentBean);

                                }
                                if (mServiceContentBeanList.size() > 0) {
                                    mServiceContentAdaper.addItems(mServiceContentBeanList);
                                    mServiceContentAdaper.notifyDataSetChanged();
                                }
                            }
                            // 服务起步时间及单价
                            JSONArray basic_limit = jsonData.getJSONArray("basic_limit");
                            if (basic_limit != null && basic_limit.length() > 0)
                                for (int i = 0; i < basic_limit.length(); i++) {
                                    JSONObject serviceTimeJson = basic_limit.getJSONObject(i);
                                    ServiceBseTimeBean serviceTimeBean = new ServiceBseTimeBean(serviceTimeJson.getInt("basic_price"), serviceTimeJson.getInt("basic_hours"), serviceTimeJson.getInt("indus_id"));
                                    mServiceTimeBeanList.add(serviceTimeBean);
                                    // 点击服务小类时 根据不同小类显示起步价
//                                        mServiceTimeTextView.setText("基础价格" + serviceTimeBean.getBasic_price() + "元/小时," + serviceTimeBean.getBasic_hours() + "小时起雇");
                                }
                            // 自选价格列表
                            JSONArray select_price = jsonData.getJSONArray("select_price");
                            if (select_price != null && select_price.length() > 0) {
                                for (int i = 0; i < select_price.length(); i++) {
                                    JSONObject priceJson = select_price.getJSONObject(i);
                                    ChoosePriceBean choosePriceBean = new ChoosePriceBean(priceJson.getInt("price"), priceJson.getString("price_name"), priceJson.getInt("indus_id"));
                                    mChoosePriceBeanList.add(choosePriceBean);
                                }
                            }
                            //保洁用品
                            JSONArray select_supplies = jsonData.getJSONArray("select_supplies");
                            if (select_supplies != null && select_supplies.length() > 0) {
                                for (int i = 0; i < select_supplies.length(); i++) {
                                    JSONObject toolsJson = select_supplies.getJSONObject(i);
                                    ServiceToolsBean serviceToolsBean = new ServiceToolsBean(toolsJson.getInt("supplies_id"), toolsJson.getString("supplies_name"), toolsJson.getInt("indus_id"));
                                    mServiceToolsBeanList.add(serviceToolsBean);
                                }

                            }

                            // 服务频率
                            JSONArray weeks = jsonData.getJSONArray("weeks");
                            if (weeks != null && weeks.length() > 0) {
                                for (int i = 0; i < weeks.length(); i++) {
                                    JSONObject serviceWeek = weeks.getJSONObject(i);
                                    ServiceWeekBean serviceWeekBean = new ServiceWeekBean(serviceWeek.getInt("week_id"), serviceWeek.getString("week_name"), serviceWeek.getInt("indus_id"));
                                    mServiceWeekBeanList.add(serviceWeekBean);
                                }

                            }
                            // 服务模式
                            JSONArray employment_typ = jsonData.getJSONArray("employment_typ");
                            if (employment_typ != null && employment_typ.length() > 0) {
                                for (int i = 0; i < employment_typ.length(); i++) {
                                    JSONObject modelJson = employment_typ.getJSONObject(i);
                                    ServiceModelBean serviceModelBean = new ServiceModelBean(modelJson.getInt("employment_typ"), modelJson.getString("employment_typ_name"));
                                    mServiceModelBeanList.add(serviceModelBean);
                                }
                                if (mServiceModelBeanList.size() > 0) {
                                    mServiceModelAdapter.addItems(mServiceModelBeanList);
                                    mServiceModelAdapter.notifyDataSetChanged();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
//                        Toast.makeText(MainActivity.this, codeMsg, Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void failure(VolleyError error) {

            }

        });

    }

    private void stepsView() {
        List<ChoosePriceBean> choosePriceBeanList = new ArrayList<ChoosePriceBean>();
        if (mChoosePriceBeanList != null)  {
            for (ChoosePriceBean choosePriceBean : mChoosePriceBeanList) {

                if (indus_id == choosePriceBean.getIndus_id()){
                    choosePriceBeanList.add(choosePriceBean);
                }
            }
        }
        String[] labels = new String[choosePriceBeanList.size()];
        for (int i = 0; i < mChoosePriceBeanList.size(); i++){
            labels[i] = mChoosePriceBeanList.get(i).getPrice_name();
        }
        stepsView.setLabels(labels)
                .setBarColorIndicator(this.getContext().getResources().getColor(R.color.material_blue_grey_800))
                .setProgressColorIndicator(this.getContext().getResources().getColor(R.color.orange))
                .setLabelColorIndicator(this.getContext().getResources().getColor(R.color.orange))
                .setCompletedPosition(0)
                .drawView();
    }
    // Adaper 点击回调
    public void serviceContentGridView_ItemChecked(ServiceContentBean _serviceContentBean) {
        serviceContentBean = _serviceContentBean;
        indus_id = serviceContentBean.getIndus_id();
        getTimeFromServiceAndUpdateView(1);
        stepsView();
        switch (mPosition) {
            // 长期工
            case 2:
                mSerivceWeekLinearlayout.setVisibility(View.VISIBLE);
                if (mServiceWeekBeanList.size() > 0) {
                    List<ServiceWeekBean> indus_id_ServiceWeekBean = new ArrayList<ServiceWeekBean>();
                    for (ServiceWeekBean serviceWeekBean : mServiceWeekBeanList) {
                        if (indus_id == serviceWeekBean.getIndus_id()) {
                            indus_id_ServiceWeekBean.add(serviceWeekBean);
                        }

                    }
                    mServiceWeekAdapter.addItems(indus_id_ServiceWeekBean);
                    mServiceWeekAdapter.notifyDataSetChanged();
                }
                // 小时工
            case 1:
                mServiceOverTimeLinearLayout.setVisibility(View.VISIBLE);
                mServiceToolsLinearLayout.setVisibility(View.VISIBLE);
                getTimeFromServiceAndUpdateView(2);
                if (mServiceToolsBeanList.size() > 0) {
                    List<ServiceToolsBean> serviceToolsBeanList = new ArrayList<ServiceToolsBean>();
                    for (ServiceToolsBean serviceToolsBean : mServiceToolsBeanList) {
                        if (indus_id == serviceToolsBean.getIndus_id()) {
                            serviceToolsBeanList.add(serviceToolsBean);
                        }
                    }
                    mServiceToolsAdapter.addItems(serviceToolsBeanList);
                    mServiceToolsAdapter.notifyDataSetChanged();
                }

                if (mServiceTimeBeanList != null) {
                    for (ServiceBseTimeBean serviceBseTimeBean : mServiceTimeBeanList) {
                        if (indus_id == serviceBseTimeBean.getIndus_id())
                            mServiceTimeTextView.setText("基础价格" + serviceBseTimeBean.getBasic_price() + "元/小时," + serviceBseTimeBean.getBasic_hours() + "小时起雇");
                    }
                }
                break;
            // 保姆
            case 3:
            // 月嫂
            case 4:
                mServiceModelLinearLayout.setVisibility(View.VISIBLE);
                mServiceTryTimeLinearLayout.setVisibility(View.VISIBLE);
                getTryDaysFromService();
                mServiceTimeLinearLayout.setVisibility(View.VISIBLE);
                getMonthFromService();
                if (mServiceTimeBeanList != null) {
                    for (ServiceBseTimeBean serviceBseTimeBean : mServiceTimeBeanList) {
                        if (indus_id == serviceBseTimeBean.getIndus_id())
                            mServiceTimeTextView.setText("试用期间按服务价格" + serviceBseTimeBean.getBasic_price() + "元/天计算");
                    }
                }
                break;
        }


    }
//    private void getServiceContentId(){
//        ServiceContentBean serviceContentBean =  getCheckedServiceContent();
//        if (serviceContentBean != null){
//            mCommitReservationServiceBean.setIndus_id(serviceContentBean.getIndus_id());
//        }
//
//    }

    /**
     * 从服务端获取服务时间列表
     */
    public void getTimeFromServiceAndUpdateView(final int typ) {
        Map paramMap = new HashMap();
        paramMap.put("dis_upid", mProvinceId);
        paramMap.put("dis_id", mCityId);
        paramMap.put("indus_pid", mPosition);// 服务大类Id
        paramMap.put("indus_id", indus_id);// 服务小类Id
        paramMap.put("hour_typ", typ);//1:开始  2:结束     非必要选项，默认返回开始时间列表
        BaseRequest.instance().doRequest(Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_HOURE, paramMap, new BaseResponse() {

            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codemsg = responseBean.getMsg();
                if (code == 0) {
                    try {
                        JSONArray dataJson = new JSONArray(responseBean.getData());
                        if (dataJson != null && dataJson.length() > 0) {
                            List<HourBean> hourBeans = new ArrayList<HourBean>();
                            for (int i = 0; i < dataJson.length(); i++) {
                                JSONObject hourJson = (JSONObject) dataJson.get(i);
                                HourBean hourBean = new HourBean(hourJson.getInt("hour"), hourJson.getString("hour_name"));
                                hourBeans.add(hourBean);

                            }

                            if (typ == 1) {
                                startHourBeanList = hourBeans;
                            }
                            if (typ == 2) {
                                endHourBeanList = hourBeans;
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void failure(VolleyError error) {

            }
        });
    }

    /**
     * 获取试用天数列表
     */
    public void getTryDaysFromService() {
        Map paramMap = new HashMap();
        paramMap.put("dis_upid", mProvinceId);
        paramMap.put("dis_id", mCityId);
        paramMap.put("indus_pid", mPosition);// 服务大类Id
        paramMap.put("indus_id", indus_id);// 服务小类Id
        BaseRequest.instance().doRequest(Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_TRYDAYS, paramMap, new BaseResponse() {

            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codemsg = responseBean.getMsg();
                if (code == 0) {
                    try {
                        JSONArray dataJson = new JSONArray(responseBean.getData());
                        if (dataJson != null && dataJson.length() > 0) {
                            serviceTryDayBeanList = new ArrayList<ServiceTryDayBean>();
                            for (int i = 0; i < dataJson.length(); i++) {
                                JSONObject tryDayJson = (JSONObject) dataJson.get(i);
                                ServiceTryDayBean tryDayBean = new ServiceTryDayBean(tryDayJson.getInt("try_days"), tryDayJson.getString("try_days_name"));
                                serviceTryDayBeanList.add(tryDayBean);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void failure(VolleyError error) {

            }
        });
    }

    /**
     * 获取雇佣天数列表
     */
    public void getMonthFromService() {
        Map paramMap = new HashMap();
        paramMap.put("dis_upid", mProvinceId);
        paramMap.put("dis_id", mCityId);
        paramMap.put("indus_pid", mPosition);// 服务大类Id
        paramMap.put("indus_id", indus_id);// 服务小类Id
        BaseRequest.instance().doRequest(Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_TRYDAYS, paramMap, new BaseResponse() {

            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codemsg = responseBean.getMsg();
                if (code == 0) {
                    try {
                        JSONArray dataJson = new JSONArray(responseBean.getData());
                        if (dataJson != null && dataJson.length() > 0) {
                            mServiceEmploymentMonthBeanList = new ArrayList<ServiceEmploymentMonthBean>();
                            for (int i = 0; i < dataJson.length(); i++) {
                                JSONObject monthJson = (JSONObject) dataJson.get(i);
                                ServiceEmploymentMonthBean monthBean = new ServiceEmploymentMonthBean(monthJson.getInt("employment_month"), monthJson.getString("employment_month_name"));
                                mServiceEmploymentMonthBeanList.add(monthBean);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void failure(VolleyError error) {

            }
        });
    }

}
