package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.house.cn.HuoseApp.Dao.Users;
import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.adapter.ChoosePriceAdapter;
import team.house.cn.HuoseApp.adapter.ServiceContentAdaper;
import team.house.cn.HuoseApp.adapter.ServiceModelAdapter;
import team.house.cn.HuoseApp.adapter.ServiceToolsAdapter;
import team.house.cn.HuoseApp.adapter.ServiceWeekAdapter;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.bean.AddressBean;
import team.house.cn.HuoseApp.bean.ChoosePriceBean;
import team.house.cn.HuoseApp.bean.CityBean;
import team.house.cn.HuoseApp.bean.CommitReservationServiceBean;
import team.house.cn.HuoseApp.bean.HourBean;
import team.house.cn.HuoseApp.bean.ServiceBseTimeBean;
import team.house.cn.HuoseApp.bean.ServiceContentBean;
import team.house.cn.HuoseApp.bean.ServiceEmploymentMonthBean;
import team.house.cn.HuoseApp.bean.ServiceModelBean;
import team.house.cn.HuoseApp.bean.ServiceToolsBean;
import team.house.cn.HuoseApp.bean.ServiceTryDayBean;
import team.house.cn.HuoseApp.bean.ServiceWeekBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.proviews.DateTimeDialog;
import team.house.cn.HuoseApp.utils.CityUtil;
import team.house.cn.HuoseApp.utils.JSONUtils;
import team.house.cn.HuoseApp.utils.UserUtil;
import team.house.cn.HuoseApp.views.HorizontalListView;

/**
 * Created by kenan on 15/11/7.
 * 预约服务
 */
public class ReservationServiceActivity extends BaseActivity {
    public final String Tag = "ReservationServiceActivity";
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
    private ServiceModelBean mChooseServiceModel;

    private TextView mServiceTimeTextView;// 服务起步时间及起步价
    private List<ServiceBseTimeBean> mServiceTimeBeanList;
    private LinearLayout mServiceTryTimeLinearLayout;// 试用时间
    private TextView mServiceTryTimeTextView; //试用时间view

    private RelativeLayout mServiceStartTimeRelativeLayout; // 开始时间
    private TextView mServiceStartTimeTextView;
    private List<HourBean> startHourBeanList;// 小时工 长时工
    private LinearLayout mServiceTimeLinearLayout;// 雇佣时间
    private TextView mServiceEmploymentTimeTextView;
    private List<ServiceEmploymentMonthBean> mServiceEmploymentMonthBeanList;
    private ServiceEmploymentMonthBean mServiceEmploymentMonthBean; //用户选择的雇用时间
    private LinearLayout mServiceOverTimeLinearLayout;// 结束时间
    private TextView mServiceOverTimeTextView;
    private List<HourBean> endHourBeanList;// 小时工 长时工

    private HourBean mStartHourBean; //开始小时(小时工/钟点工)
    private HourBean mEndHourBean;//结束小时(小时工/钟点工)
    //    private StepsView stepsView;
    private HorizontalListView mChoosePriceListView;
    private ChoosePriceAdapter mChoosePriceAdapter;
    private List<ChoosePriceBean> mChoosePriceBeanList;// 自选价格集合
    private ChoosePriceBean mChoosePriceBean;

    private LinearLayout mServiceToolsLinearLayout;// 随手带保洁用品 父容器
    private GridView mServiceToolsGradView; // 随手带保洁用品
    private ServiceToolsAdapter mServiceToolsAdapter; // 保洁用品适配器
    private List<ServiceToolsBean> mServiceToolsBeanList;// 保洁用品集合
    private RelativeLayout mServiceCouponsRelativeLayout;// 使用优惠券 父容器
//    private RelativeLayout mSystemChooseAuntRelativelayout;// 系统选择阿姨
    private RadioButton mSystemChooseAuntRaioButton; //系统选择阿姨
    private RadioButton rb_planAunt;//发布任务招募阿姨
    private RelativeLayout mChooseAuntRelativeLayout; //自选阿姨
    private TextView mOrderMoneyTextView; //订单金额

    private List<ServiceTryDayBean> serviceTryDayBeanList;
    private ServiceContentBean serviceContentBean;
    private Button mCommitButton;
    private ServiceBseTimeBean serviceBaseTimeBean;
    private ImageView iv_userphoto;
    private TextView tv_username;
    private TextView tv_serviceAddress;
    private ServiceTryDayBean mServiceTryDayBean; // 用户选择的试用时间
    private AddressBean mChooseAddress;
    private int mAuntId;
    private String mStartDate;
    private String mEndDate;


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
        mServiceTryTimeTextView = (TextView) findViewById(R.id.tv_serviceTryTime);
        mServiceStartTimeRelativeLayout = (RelativeLayout) findViewById(R.id.rl_serviceStarTime);
        mServiceStartTimeTextView = (TextView) findViewById(R.id.tv_serviceStarTime);
        mServiceTimeLinearLayout = (LinearLayout) findViewById(R.id.ll_servicereTime);
        mServiceEmploymentTimeTextView = (TextView) findViewById(R.id.tv_serviceireTime);
        mServiceOverTimeLinearLayout = (LinearLayout) findViewById(R.id.ll_serviceOverTime);
        mServiceOverTimeTextView = (TextView) findViewById(R.id.tv_serviceOverTime);
        mChoosePriceListView = (HorizontalListView) findViewById(R.id.hlv_chooseprice);
        mChoosePriceBeanList = new ArrayList<ChoosePriceBean>();
        mChoosePriceAdapter = new ChoosePriceAdapter(this, mChoosePriceBeanList);
        mChoosePriceListView.setAdapter(mChoosePriceAdapter);

        mServiceToolsLinearLayout = (LinearLayout) findViewById(R.id.ll_servicetools);
        mServiceToolsGradView = (GridView) findViewById(R.id.gv_servicetools);
        mServiceToolsBeanList = new ArrayList<ServiceToolsBean>();
        mServiceToolsAdapter = new ServiceToolsAdapter(mServiceToolsBeanList, this);
        mServiceToolsGradView.setAdapter(mServiceToolsAdapter);
        mServiceCouponsRelativeLayout = (RelativeLayout) findViewById(R.id.rl_serviceoupons);
        mOrderMoneyTextView = (TextView) findViewById(R.id.tv_orderMoney);
//        mSystemChooseAuntRelativelayout = (RelativeLayout) findViewById(R.id.rl_systemChooseAunt);
        mSystemChooseAuntRaioButton = (RadioButton) findViewById(R.id.rb_systemAunt);
        rb_planAunt = (RadioButton) findViewById(R.id.rb_planAunt);
        mChooseAuntRelativeLayout = (RelativeLayout) findViewById(R.id.rl_chooseAunt);
        mCommitButton = (Button) findViewById(R.id.bt_commit);
        iv_userphoto = (ImageView) findViewById(R.id.iv_userphoto);
        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_serviceAddress = (TextView) findViewById(R.id.tv_serviceAddress);


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
//        mCommitReservationServiceBean.setIndus_pid(mPosition);
        showViewBymPosition();
        getConfigInfoFromService();
        getDefauleAddress();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mServiceAddressRelativeLayout.setOnClickListener(this);
        mServiceTryTimeLinearLayout.setOnClickListener(this);
        mServiceStartTimeRelativeLayout.setOnClickListener(this);
        mServiceTimeLinearLayout.setOnClickListener(this);
        mServiceOverTimeLinearLayout.setOnClickListener(this);
        mCommitButton.setOnClickListener(this);
//        mSystemChooseAuntRelativelayout.setOnClickListener(this);
        mChooseAuntRelativeLayout.setOnClickListener(this);
        mSystemChooseAuntRaioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSystemChooseAuntRaioButton.setChecked(true);
                rb_planAunt.setChecked(false);
                mCommitReservationServiceBean.setModel_id(1);
                mAuntId = 0;
                // mChooseAuntRelativeLayout.setVisibility(View.GONE);
            }
        });
        rb_planAunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSystemChooseAuntRaioButton.setChecked(false);
                rb_planAunt.setChecked(true);
                mAuntId = 0;
                mCommitReservationServiceBean.setModel_id(2);
                // mChooseAuntRelativeLayout.setVisibility(View.GONE);
            }
        });
        mChoosePriceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                List<ChoosePriceBean> choosePriceBeans = mChoosePriceAdapter.getAllItems();
                if (i <= choosePriceBeans.size()) {
                    mChoosePriceBean = (ChoosePriceBean) mChoosePriceAdapter.getItem(i);
                    List<ChoosePriceBean> choosePriceBeanList = new ArrayList<ChoosePriceBean>();
                    if (mChoosePriceBean.isSelelct()) {
                        if (i != 0) {
                            mChoosePriceBean = choosePriceBeans.get(i--);
                            for (int j = 0; j < choosePriceBeans.size(); j++) {
                                ChoosePriceBean choosePriceBean1 = choosePriceBeans.get(j);
                                if (j < i) {
                                    choosePriceBean1.setIsSelelct(true);
                                } else {
                                    if (j != 0) {
                                        choosePriceBean1.setIsSelelct(false);
                                    }
                                }
                                choosePriceBeanList.add(choosePriceBean1);
                            }
                        } else {
                            return;
                        }
                    } else {

                        for (int j = 0; j < choosePriceBeans.size(); j++) {
                            ChoosePriceBean choosePriceBean1 = choosePriceBeans.get(j);
                            if (j <= i) {
                                choosePriceBean1.setIsSelelct(true);
                            } else {
                                choosePriceBean1.setIsSelelct(false);
                            }
                            choosePriceBeanList.add(choosePriceBean1);
                        }
                    }
                    mChoosePriceAdapter.addItems(choosePriceBeanList);
                    mChoosePriceAdapter.notifyDataSetChanged();
                }
                try {
                    setMoney();
                } catch (Exception e) {
                    e.getStackTrace();
                }

            }
        });
    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        int viewId = v.getId();
        if (viewId == R.id.rl_serviceAddress) {
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
                        mServiceTryTimeTextView.setText(time + "天");
                        for (ServiceTryDayBean serviceTryDayBean : serviceTryDayBeanList) {
                            if (serviceTryDayBean.getTry_days() == Integer.parseInt(time)) {
                                mServiceTryDayBean = serviceTryDayBean;
//                                mCommitReservationServiceBean.setServiceTryDayBean(serviceTryDayBean);
                            }
                        }

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
                        String startTime = date + " " + time + ":00";
                        mServiceStartTimeTextView.setText(startTime);
                        mStartDate = startTime;
                        mCommitReservationServiceBean.setStart_time(date);
                        for (HourBean hourBean : startHourBeanList) {
                            if (hourBean.getHour() == Integer.parseInt(time)) {
                                mStartHourBean = hourBean;
                                try {
                                    setMoney();
                                } catch (Exception e) {
                                    e.getStackTrace();
                                }

//                                mCommitReservationServiceBean.setStartHouBean(hourBean);
                            }
                        }

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
                        String month = time + "个月";
                        mServiceEmploymentTimeTextView.setText(month);
                        for (ServiceEmploymentMonthBean serviceEmploymentMonthBean : mServiceEmploymentMonthBeanList) {
                            if (serviceEmploymentMonthBean.getEmployment_month() == Integer.parseInt(time)) {
                                mServiceEmploymentMonthBean = serviceEmploymentMonthBean;
                                try {
                                    setMoney();
                                } catch (Exception e) {
                                    e.getStackTrace();
                                }

//                                mCommitReservationServiceBean.setServiceEmploymentMonth(serviceEmploymentMonthBean);
                            }

                        }


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
                        String endTime = date + " " + time + ":00";
                        mServiceOverTimeTextView.setText(endTime);
                        mEndDate = endTime;
                        mCommitReservationServiceBean.setEnd_time(date);
                        for (HourBean hourBean : endHourBeanList) {
                            if (hourBean.getHour() == Integer.parseInt(time)) {
                                mEndHourBean = hourBean;
                                try {
                                    setMoney();
                                } catch (Exception e) {
                                    e.getStackTrace();
                                }

//                                mCommitReservationServiceBean.setEndHouBean(hourBean);
                            }
                        }
                    }
                });
            } else {
                Toast.makeText(this, "数据获取中", Toast.LENGTH_SHORT).show();
            }
        }
        if (viewId == R.id.bt_commit) {
            try {
                if (checkParam()) {
                    setCommitReservationServiceBean();
                    Intent intent = new Intent();
                    intent.putExtra("CommitReservationServiceBean", mCommitReservationServiceBean);
                    intent.setClass(this, CommitOrderActivity.class);
                    this.startActivity(intent);
                }
            }catch(Exception e){
                e.getStackTrace();
            }
        }

        if (viewId == R.id.rl_chooseAunt) {
            mSystemChooseAuntRaioButton.setChecked(false);
            rb_planAunt.setChecked(false);
            Intent intent = new Intent(this, ChooseAuntActivity.class);
            intent.putExtra("positoin", mPosition); //服务大类
            intent.putExtra("type", 2); //选择阿姨
            this.startActivityForResult(intent, 3);
        }
    }

    private void getDefauleAddress() {
        Users userInfo = UserUtil.getUserinfoFromSharepreference();
        Picasso.with(this)
                .load(userInfo.getUser_pic())
                .placeholder(R.drawable.user)
                .error(R.drawable.user)
                .tag(this)
                .into(iv_userphoto);
        tv_username.setText(userInfo.getUsername());
        List<AddressBean> addressList = UserUtil.getAddressFromSharepreference();
        if(addressList!=null&&addressList.size()>0){
            for (AddressBean address : addressList) {
                if (address.iSDefault() && address.getmAddressAll() != null) {
                    tv_serviceAddress.setText(address.getmAddressAll());
                    mChooseAddress = address;
                    break;
//                mCommitReservationServiceBean.setAddressBean(address);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 选择路线
        if (requestCode == 1 && resultCode == RESULT_OK) {
            AddressBean addressBean = (AddressBean) data.getSerializableExtra("address");
            String addressInfo = data.getStringExtra("address_info");
            if (addressBean != null) {
//                mCommitReservationServiceBean.setAddress_id(addressId);
                mChooseAddress = addressBean;
//                mCommitReservationServiceBean.setAddressBean(addressBean);
                mServiceAddressTextView.setText(addressInfo);
            }
        }
        //选择阿姨
        if (requestCode == 3 && resultCode == RESULT_OK) {
            mAuntId = data.getIntExtra("auntId", 0);
            mCommitReservationServiceBean.setModel_id(3);
//            mCommitReservationServiceBean.setEmployment_uid(data.getIntExtra("auntId", 0));
        }
        //系统选择阿姨
//        if (requestCode == 3 && resultCode == RESULT_OK) {
//            mCommitReservationServiceBean.setEmployment_uid(data.getIntExtra("auntId", 0));
//        }
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("预约服务");
        mCityView.setVisibility(View.GONE);
        mLeftView.setVisibility(View.VISIBLE);
        mRightView.setText("服务说明");
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
        BaseRequest.instance(this).doRequest(Tag, Request.Method.GET, AppConfig.WebHost + AppConfig.Urls.URL_GET_CONFIG, paraments, new BaseResponse() {
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
                                    ServiceModelBean serviceModelBean = new ServiceModelBean(modelJson.getInt("employment_typ"), modelJson.getString("employment_typ_name"), JSONUtils.getInt(modelJson, "indus_id" , 0));
                                    mServiceModelBeanList.add(serviceModelBean);
                                }
//                                if (mServiceModelBeanList.size() > 0) {
//                                    mServiceModelAdapter.addItems(mServiceModelBeanList);
//                                    mServiceModelAdapter.notifyDataSetChanged();
//                                }
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

    private void updateChoosePriceLIst() {
        List<ChoosePriceBean> choosePriceBeans = new ArrayList<ChoosePriceBean>();
        if (mChoosePriceBeanList != null) {
            int i = 0;
            for (ChoosePriceBean choosePriceBean : mChoosePriceBeanList) {

                if (indus_id == choosePriceBean.getIndus_id()) {
                    if (i == 0) {
                        choosePriceBean.setIsSelelct(true);
                        mChoosePriceBean = choosePriceBean;
                    }
                    choosePriceBeans.add(choosePriceBean);
                    i++;
                }
            }
        }
        if (choosePriceBeans.size() > 0) {
            mChoosePriceAdapter.addItems(choosePriceBeans);
            mChoosePriceAdapter.notifyDataSetChanged();
        }
    }

    private void updateServiceMOdelList () {
        List<ServiceModelBean> serviceModelBeans = new ArrayList<ServiceModelBean>();
        if (mServiceModelBeanList != null && mServiceModelBeanList.size() > 0) {
            for (ServiceModelBean serviceModelBean : mServiceModelBeanList) {
                if (indus_id == serviceModelBean.getIndus_id()) {
                    serviceModelBeans.add(serviceModelBean);
                }

            }
            if (serviceModelBeans.size() > 0) {
                mServiceModelAdapter.addItems(serviceModelBeans);
                mServiceModelAdapter.notifyDataSetChanged();
            }

        }
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    // 用户选择服务内容回调
    public void serviceContentGridView_ItemChecked(ServiceContentBean _serviceContentBean) {
        serviceContentBean = _serviceContentBean;
//        mCommitReservationServiceBean.setServiceContentBean(serviceContentBean);
        indus_id = serviceContentBean.getIndus_id();
        getTimeFromServiceAndUpdateView(1);
        updateChoosePriceLIst();
        updateServiceMOdelList();
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
                            serviceBaseTimeBean = serviceBseTimeBean;
                    //    mServiceTimeTextView.setText("基础价格" + serviceBseTimeBean.getBasic_price() + "元/小时," + serviceBseTimeBean.getBasic_hours() + "小时起雇");
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
//                if (mServiceTimeBeanList != null) {
//                    for (ServiceBseTimeBean serviceBseTimeBean : mServiceTimeBeanList) {
//                        if (indus_id == serviceBseTimeBean.getIndus_id())
//                            mServiceTimeTextView.setText("试用期间按服务价格" + serviceBseTimeBean.getBasic_price() + "元/天计算");
//                    }
//                }
                break;
        }


    }

    /**
     * 用户选择服务模式回调
     *
     * @param serviceModelBean
     */
    public void serviceModel_ItemChecked(ServiceModelBean serviceModelBean) {
        mChooseServiceModel= serviceModelBean;
//        mCommitReservationServiceBean.setServiceModelBean(serviceModelBean);
    }

    /**
     * 从服务端获取服务时间列表
     */
    public void getTimeFromServiceAndUpdateView(final int typ) {
        Map paramMap = new HashMap();
        paramMap.put("dis_upid", mProvinceId);
        paramMap.put("dis_id", mCityId);
        paramMap.put("indus_pid", mPosition);// 服务大类Id
        paramMap.put("indus_id", indus_id);// 服务小类Id
        paramMap.put("hour_typ", typ);//1:开始  2:结束       非必要选项，默认返回开始时间列表
        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_HOURE, paramMap, new BaseResponse() {

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
        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_TRYDAYS, paramMap, new BaseResponse() {

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
     * 获取雇佣月列表
     */
    public void getMonthFromService() {
        Map paramMap = new HashMap();
        paramMap.put("dis_upid", mProvinceId);
        paramMap.put("dis_id", mCityId);
        paramMap.put("indus_pid", mPosition);// 服务大类Id
        paramMap.put("indus_id", indus_id);// 服务小类Id
        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_MONTH, paramMap, new BaseResponse() {

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

    private void setMoney() throws Exception{
        mOrderMoneyTextView.setText(getMoney() + "元");
    }
    private int getMoney() throws  Exception{
        int money = 0;
        int days=1;
        switch (mPosition) {

            // 长期工
            case 2:

                // 小时工
            case 1:
                 if(mStartDate!=null&&!"".equals(mStartDate)&&mEndDate!=null&&!"".equals(mEndDate)){
                 days=days+Integer.parseInt(mEndDate.replace("-","").substring(0, 8))-Integer.parseInt(mStartDate.replace("-","").substring(0,8));
                 }
                int hours = getHours();
                if (hours != 0 && mChoosePriceBean != null) {
                    money = hours * mChoosePriceBean.getPrice()*days;
                }
                break;
            // 保姆
            case 3:
                // 月嫂
            case 4:
                int month = getChooseMonth();
                if (month != 0 && mChoosePriceBean != null) {
                    money = month * mChoosePriceBean.getPrice();
                }

                break;
        }
        return money;
    }

    private int getChooseMonth() {
        if (mServiceEmploymentMonthBean != null) {
            return mServiceEmploymentMonthBean.getEmployment_month();

        }
        return 0;
    }

    private int getHours() throws  Exception{

        if (mStartHourBean != null && mEndHourBean != null) {

            if (mStartHourBean.getHour() < mEndHourBean.getHour()) {
                return mEndHourBean.getHour() - mStartHourBean.getHour();
            }
        }
//        if(mStartDate!=null&&!"".equals(mStartDate)&&mEndDate!=null&&!"".equals(mEndDate)){
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            java.util.Date now = df.parse(mEndDate+":00");
//            java.util.Date date=df.parse(mStartDate+":00");
//            long l=now.getTime()-date.getTime();
//            long hour=l/(60*60*1000);
//            return (int)hour;
//        }


        return 0;
    }

    private boolean checkParam() throws  Exception{
        boolean isPass = true;

        if (mChooseAddress == null){
            Toast.makeText(this, "请填写服务地址信息", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (serviceContentBean == null){
            Toast.makeText(this, "请选择服务内容", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mStartHourBean == null) {
            Toast.makeText(this, "请选择服务开始时间", Toast.LENGTH_SHORT).show();
            return false;
        }
        switch (mPosition) {
            // 长期工
            case 2:
               if (mServiceWeekAdapter.getCHooseServiceWeeksList().size() == 0)
               {
                   Toast.makeText(this, "请选择服务频率", Toast.LENGTH_SHORT).show();
                   return false;
               }

                // 小时工
            case 1:
                if (mEndHourBean == null) {
                    Toast.makeText(this, "请选择服务结束时间", Toast.LENGTH_SHORT).show();
                    return false;
                } else {

                    if(getHours() == 0){
                        Toast.makeText(this, "服务的开始时间要在结束时间之前,请注意选择服务时间", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    if (getHours() < serviceBaseTimeBean.getBasic_hours()){
                        Toast.makeText(this, serviceBaseTimeBean.getBasic_hours() + "小时起雇,您所选时间为" + getHours() + "小时", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                }

                break;
            // 保姆
            case 3:
            // 月嫂
            case 4:
                if (mChooseServiceModel == null){
                    Toast.makeText(this, "请选择服务模式", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (mServiceEmploymentMonthBean == null){
                    Toast.makeText(this, "请选择服务雇佣时间", Toast.LENGTH_SHORT).show();
                    return false ;
                }
                break;
        }
        if (!rb_planAunt.isChecked()&&!mSystemChooseAuntRaioButton.isChecked() && mAuntId == 0) {
            Toast.makeText(this, "请选择阿姨", Toast.LENGTH_SHORT).show();
            return false;
        }
        return isPass;
    }

    private void setCommitReservationServiceBean() throws Exception{
        mCommitReservationServiceBean.setIndus_pid(mPosition);
        mCommitReservationServiceBean.setAddressBean(mChooseAddress);
        mCommitReservationServiceBean.setServiceContentBean(serviceContentBean);
        mCommitReservationServiceBean.setStartHouBean(mStartHourBean);
        mCommitReservationServiceBean.setEndHouBean(mEndHourBean);
        mCommitReservationServiceBean.setServiceTryDayBean(mServiceTryDayBean);
        mCommitReservationServiceBean.setServiceModelBean(mChooseServiceModel);
        mCommitReservationServiceBean.setServiceEmploymentMonth(mServiceEmploymentMonthBean);
        mCommitReservationServiceBean.setEmployment_uid(mAuntId);
        mCommitReservationServiceBean.setServiceWeekBeanList(mServiceWeekAdapter.getCHooseServiceWeeksList());
        mCommitReservationServiceBean.setServiceToolsBeanList(mServiceToolsAdapter.getCHooseServiceToolsList());
        mCommitReservationServiceBean.setChoosePriceBean(mChoosePriceBean);
        mCommitReservationServiceBean.setTask_cash(getMoney());
        mCommitReservationServiceBean.setPaied_cash(getMoney());

    }
}
