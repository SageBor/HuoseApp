package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.VolleyError;

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
import team.house.cn.HuoseApp.application.HouseApplication;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.bean.ChoosePriceBean;
import team.house.cn.HuoseApp.bean.CityBean;
import team.house.cn.HuoseApp.bean.ServiceContentBean;
import team.house.cn.HuoseApp.bean.ServiceModelBean;
import team.house.cn.HuoseApp.bean.ServiceTimeBean;
import team.house.cn.HuoseApp.bean.ServiceToolsBean;
import team.house.cn.HuoseApp.bean.ServiceWeekBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.CityUtil;
import team.house.cn.HuoseApp.utils.PreferenceUtil;

/**
 * Created by kenan on 15/11/7.
 * 预约服务
 */
public class ReservationServiceActivity extends BaseActivity {
    private int mPosition;
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
    private LinearLayout mServiceTryTimeLinearLayout;// 试用时间
    private RelativeLayout mServiceStartTimeRelativeLayout; // 开始时间
    private LinearLayout mServiceTimeLinearLayout;// 雇佣时间
    private LinearLayout mServiceOverTimeLinearLayout;// 结束时间
    // 自选价格空间还没定义 稍后补上
    private List<ChoosePriceBean> mChoosePriceBeanList;// 自选价格集合

    private LinearLayout mServiceToolsLinearLayout;// 随手带保洁用品 父容器
    private GridView mServiceToolsGradView; // 随手带保洁用品
    private ServiceToolsAdapter mServiceToolsAdapter; // 保洁用品适配器
    private List<ServiceToolsBean> mServiceToolsBeanList;// 保洁用品集合


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
        mServiceTryTimeLinearLayout = (LinearLayout) findViewById(R.id.ll_serviceTryTime);
        mServiceStartTimeRelativeLayout = (RelativeLayout) findViewById(R.id.rl_serviceStarTime);
        mServiceTimeLinearLayout = (LinearLayout) findViewById(R.id.ll_servicereTime);
        mServiceOverTimeLinearLayout = (LinearLayout) findViewById(R.id.ll_serviceOverTime);
        mChoosePriceBeanList = new ArrayList<ChoosePriceBean>();

        mServiceToolsLinearLayout = (LinearLayout) findViewById(R.id.ll_servicetools);
        mServiceToolsGradView = (GridView) findViewById(R.id.gv_servicetools);
        mServiceToolsBeanList = new ArrayList<ServiceToolsBean>();
        mServiceToolsAdapter = new ServiceToolsAdapter(mServiceToolsBeanList, this);


    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        mPosition = intent.getIntExtra("position", 1);
//        ServiceContentBean serviceContentBean = new ServiceContentBean(5, "日常保洁");
//        ServiceContentBean serviceContentBean2 = new ServiceContentBean(6, "开荒");
//        mServiceContentBeanList.add(serviceContentBean);
//        mServiceContentBeanList.add(serviceContentBean2);
//        mServiceContentAdaper.addItems(mServiceContentBeanList);
//        mServiceContentAdaper.notifyDataSetChanged();
//
//        mSerivceWeekLinearlayout.setVisibility(View.VISIBLE);
//        ServiceWeekBean serviceWeekBean = new ServiceWeekBean(1, "星期一", 5);
//        ServiceWeekBean serviceWeekBean2 = new ServiceWeekBean(2, "星期二", 5);
//        mServiceWeekBeanList.add(serviceWeekBean);
//        mServiceWeekBeanList.add(serviceWeekBean2);
//        mServiceWeekAdapter.addItems(mServiceWeekBeanList);
//        mServiceWeekAdapter.notifyDataSetChanged();
//
//
//        mServiceModelLinearLayout.setVisibility(View.VISIBLE);
//        ServiceModelBean serviceModelBean = new ServiceModelBean(1, "住家");
//        ServiceModelBean serviceModelBean1 = new ServiceModelBean(2,"不住家");
//        mServiceModelBeanList.add(serviceModelBean);
//        mServiceModelBeanList.add(serviceModelBean1);
//        mServiceModelAdapter.addItems(mServiceModelBeanList);
//        mServiceModelAdapter.notifyDataSetChanged();
        getConfigInfoFromService();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mServiceAddressRelativeLayout.setOnClickListener(this);
        mServiceContentGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        mServiceTryTimeLinearLayout.setOnClickListener(this);
        mServiceStartTimeRelativeLayout.setOnClickListener(this);
        mServiceTimeLinearLayout.setOnClickListener(this);
        mServiceOverTimeLinearLayout.setOnClickListener(this);
    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        int viewId = v.getId();
        if (viewId == R.id.tv_serviceAddress) {
            // 进入选择地址页面
            this.startActivityForResult(new Intent(this, ChooseAddressActivity.class), 1);
        }
        // 试用时间
        if (viewId == R.id.ll_serviceTryTime){

        }
        //开始时间
        if (viewId == R.id.rl_serviceStarTime){

        }
        // 雇佣时间
        if (viewId == R.id.ll_servicereTime){

        }
        //结束时间
        if (viewId == R.id.ll_serviceOverTime){

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

    private void getConfigInfoFromService() {

        CityBean cityBean = CityUtil.getCity(AppConfig.Preference_ChooseCityNameFromService);
        if (cityBean != null) {


            Map paraments = new HashMap<String, String>();
            paraments.put("dis_upid", cityBean.getProvinceId());
            paraments.put("dis_id", cityBean.getCityId());
            paraments.put("indus_pid", mPosition);
            BaseRequest.instance().doRequest(Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_CONFIG, paraments, new BaseResponse() {
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
                                        ServiceContentBean serviceContentBean = new ServiceContentBean(serviceContentJson.getInt("indus_id"), serviceContentJson.getString("indus_name"));
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
                                        ServiceTimeBean serviceTimeBean = new ServiceTimeBean(serviceTimeJson.getInt("basic_price"), serviceTimeJson.getInt("basic_hours"), serviceTimeJson.getInt("indus_id"));
                                        // 小时工及长期工此文案   保姆其他
                                        mServiceTimeTextView.setText("基础价格" + serviceTimeBean.getBasic_price() + "元/小时," + serviceTimeBean.getBasic_hours() + "小时起雇");
                                    }
                                // 自选时间列表
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
                                    if (mServiceToolsBeanList.size() > 0) {
                                        mServiceToolsAdapter.addItems(mServiceToolsBeanList);
                                        mServiceToolsAdapter.notifyDataSetChanged();
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
                                    if (mServiceWeekBeanList.size() > 0) {
                                        mServiceWeekAdapter.addItems(mServiceWeekBeanList);
                                        mServiceWeekAdapter.notifyDataSetChanged();
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
    }
}
