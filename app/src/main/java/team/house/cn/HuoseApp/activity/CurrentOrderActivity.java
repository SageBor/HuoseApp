package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.house.cn.HuoseApp.Dao.Users;
import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.adapter.OrderListAdapter;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.bean.AuntDetailBean;
import team.house.cn.HuoseApp.bean.OrderBean;
import team.house.cn.HuoseApp.bean.OrderDetailBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.JSONUtils;
import team.house.cn.HuoseApp.utils.UserUtil;

/**
 * Created by kn on 15/11/14.
 */
public class CurrentOrderActivity extends BaseActivity {
    private final String Tag = "CurrentOrderActivity";
    private OrderDetailBean orderDetailBean;
    private List<OrderBean> orderBeanList;
    private int mPageNum = 1;
    private int mPageSize = 10;
    private ScrollView mOrderDetailScrollview;
    private TextView mServiceContentTextView;
    private TextView mServiceWeekTextView;
    private TextView mServiceStartTimeTextView;
    private TextView mServiceEndTimeTextView;
    private TextView mServiceWorkTimeTextView;
    private TextView mServiceToolsTextView;
    private TextView mOrderMoneyTextView;
    private TextView mAuntNameTextView;
    private TextView mAuntMobileTextView;
    private TextView mSoreTextView;
    private TextView mSoreContentTextView;
    private TextView mSuggestTextView;
    private TextView mServiceModelTextView;
    private TextView mServiceTryDateTextView;
    private TextView mServiceEmploymentTextView;
    private TextView mServiceAddressTextView;
    private TextView mCouponsTextView;
    private TextView mMarginTextView;
    private TextView mContactTextView;
    private TextView mContactMobileTextView;
    private TextView mOrderStateTextView;
    private Button mStartServiceButton;
    private Button mEndServiceButton;
    private Button mPayButton;
    private ListView mOrderListView;
    private OrderListAdapter orderListAdapter;
    private int Status = 0; //0 - 当前订单 1- 订单详情

    private Users mUser;
    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_current_order);
        orderDetailBean = new OrderDetailBean();
        orderBeanList = new ArrayList<OrderBean>();
        mOrderDetailScrollview = (ScrollView) findViewById(R.id.sv_order_detail);
        mServiceContentTextView = (TextView) findViewById(R.id.tv_serviceContent);
        mServiceWeekTextView = (TextView) findViewById(R.id.tv_serviceWeek);
        mServiceStartTimeTextView = (TextView) findViewById(R.id.tv_startServiceTime);
        mServiceEndTimeTextView = (TextView) findViewById(R.id.tv_endServiceTime);
        mServiceWorkTimeTextView = (TextView) findViewById(R.id.tv_ServiceWorkTime);
        mServiceToolsTextView = (TextView) findViewById(R.id.tv_serviceTools);
        mOrderMoneyTextView = (TextView) findViewById(R.id.tv_orderMoney);
        mAuntNameTextView = (TextView) findViewById(R.id.tv_auntName);
        mAuntMobileTextView = (TextView) findViewById(R.id.tv_auntMoble);
        mSoreTextView = (TextView) findViewById(R.id.tv_score);
        mSoreContentTextView = (TextView) findViewById(R.id.tv_scoreContent);
        mSuggestTextView = (TextView) findViewById(R.id.tv_suggest);
        mServiceModelTextView = (TextView) findViewById(R.id.tv_serviceModel);
        mServiceTryDateTextView = (TextView) findViewById(R.id.tv_serviceTryDate);
        mServiceEmploymentTextView = (TextView) findViewById(R.id.tv_serviceEmploymentDate);
        mServiceAddressTextView = (TextView) findViewById(R.id.tv_serviceAddress);
        mCouponsTextView = (TextView) findViewById(R.id.tv_coupons);
        mMarginTextView = (TextView) findViewById(R.id.tv_margin);
        mContactTextView = (TextView) findViewById(R.id.tv_contact);
        mContactMobileTextView = (TextView) findViewById(R.id.tv_contact_mobile);
        mOrderStateTextView = (TextView) findViewById(R.id.tv_ordertate);
        mStartServiceButton = (Button) findViewById(R.id.bt_start);
        mEndServiceButton = (Button) findViewById(R.id.bt_end);
        mPayButton = (Button) findViewById(R.id.bt_pay);
        mOrderListView = (ListView) findViewById(R.id.lv_order);
        orderListAdapter = new OrderListAdapter(orderBeanList, this);
        mOrderListView.setAdapter(orderListAdapter);

    }
    private void showDetail(boolean show) {
        mOrderDetailScrollview.setVisibility(show ? View.VISIBLE : View.GONE);
        mOrderListView.setVisibility(show ? View.GONE : View.VISIBLE);

    }
    private void showViewDetail() {
        showDetail(true);
        mServiceContentTextView.setText("服务信息:" + orderDetailBean.getIndus_id());
        mServiceWeekTextView.setText("服务频率:" + orderDetailBean.getWeek_name());
        mServiceStartTimeTextView.setText("服务时间:" + orderDetailBean.getStart_time());
        mServiceEndTimeTextView.setText("服务结束日期:" + orderDetailBean.getEnd_time());
        mServiceWorkTimeTextView.setText("服务时间段" + orderDetailBean.getWork_time());
        mServiceToolsTextView.setText("随手带:" + orderDetailBean.getSupplies_name());
        mOrderMoneyTextView.setText("订单金额:" + orderDetailBean.getTask_cash());
        mAuntNameTextView.setText("阿姨姓名:" + orderDetailBean.getEmployment_truename());
        mAuntMobileTextView.setText("阿姨电话:" + orderDetailBean.getEmployment_mobile());
        mSoreTextView.setText("评分:" + orderDetailBean.getAid_star());
        mSoreContentTextView.setText("评分内容:" + orderDetailBean.getMark_content());
        mSuggestTextView.setText("意见建议:" + orderDetailBean.getSuggest());
        mServiceModelTextView.setText("服务模式:" + orderDetailBean.getEmployment_typ());
        mServiceTryDateTextView.setText("试用期限:" +orderDetailBean.getTry_days());
        mServiceEmploymentTextView.setText("雇佣期限:" + orderDetailBean.getEmployment_month());
        mServiceAddressTextView.setText("服务地点:" + orderDetailBean.getAddress());
        mCouponsTextView.setText("优惠券:25元优惠券");
        mMarginTextView.setText("保证金:" + orderDetailBean.getPaied_cash());
        mContactTextView.setText("联系人:" + orderDetailBean.getTruename());
        mContactMobileTextView.setText("联系电话:" + orderDetailBean.getContact());
        mOrderStateTextView.setText("订单状态:" + orderDetailBean.getTask_status_content());
        if (orderDetailBean.getTask_status().equals("8")){
            // 已完成服务等到雇主确认并支付
            mPayButton.setText("余额支付");
            mPayButton.setVisibility(View.VISIBLE);
        } else if (orderDetailBean.getTask_status().equals("9")) {
            // 订单完成,用户可评价
            mPayButton.setText("我要评价");
            mPayButton.setVisibility(View.VISIBLE);
        } else if (orderDetailBean.getTask_status().equals("10")) {
            // 用户已评价 可修改评价
            mPayButton.setText("修改评价");
            mPayButton.setVisibility(View.VISIBLE);
        }else if (orderDetailBean.getTask_status().equals("5")) {
            // 用户已评价 可修改评价
            mPayButton.setText("选择阿姨");
            mPayButton.setVisibility(View.VISIBLE);
        }else{
            mPayButton.setVisibility(View.GONE);
        }

    }

    private void showList() {
        showDetail(false);
    }
    @Override
    protected void initData() {
        super.initData();
        mUser = UserUtil.getUserinfoFromSharepreference();
        if (mUser == null) {
            Toast.makeText(this, "用户退出,请重新登录", Toast.LENGTH_SHORT).show();
        } else {
            getInfoFromService();

        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mStartServiceButton.setOnClickListener(this);
        mEndServiceButton.setOnClickListener(this);
        mPayButton.setOnClickListener(this);
        mOrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getDetailFromService(orderBeanList.get(i).getTask_id(), orderBeanList.get(i).indus_pid());
            }
        });


    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        int id = v.getId();
        if (id == R.id.bt_pay) {
            if (orderDetailBean.getTask_status().equals("8")) {
                // 已完成服务等到雇主确认并支付
                updataOrderStates();
            } else if (orderDetailBean.getTask_status().equals("2")) {
                // 支付完成,用户可评价
                startEvaluateActivity(1);
            } else if (orderDetailBean.getTask_status().equals("10")) {
                // 用户已评价 可修改评价
                startEvaluateActivity(2);
            } else if (orderDetailBean.getTask_status().equals("5")) {
                Intent intent = new Intent(this, ChooseAuntActivity.class);
                intent.putExtra("source", "order");
                intent.putExtra("task_id", orderDetailBean.getTask_id());
                this.startActivity(intent);

            }
        }
        if (id ==R.id.tv_right) {
            Intent intent = new Intent(this, HistoryOrderListActivity.class);
            this.startActivity(intent);
        }
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("当前订单");
        mCityView.setVisibility(View.GONE);
        mLeftView.setVisibility(View.VISIBLE);
        mRightView.setVisibility(View.VISIBLE);
        mRightView.setText("历史订单");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void getInfoFromService() {
        Map param = new HashMap();
        param.put("uid", mUser.getUid());
        param.put("group_id", 4);
        param.put("page_size", mPageSize);
        param.put("page_num", mPageNum);
        BaseRequest.instance().doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_CURRENT_ORDER, param, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String msg = responseBean.getMsg();
                if (code == 0) {
                    try {
                        JSONArray data = new JSONArray(responseBean.getData());
                        if (data != null && data.length() > 0) {
                            if (data.length() == 1) {
                                JSONObject jsonObject = data.getJSONObject(0);
                                setOrderDetailInfoAndShow(jsonObject);

                            } else {
                                showList();
                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject jsonObject = data.getJSONObject(i);
                                    OrderBean orderBean = new OrderBean();
                                    orderBean.setTask_id(JSONUtils.getInt(jsonObject, "task_id", 0));
                                    orderBean.setOn_time(JSONUtils.getString(jsonObject, "on_time", ""));
                                    orderBean.setIndus_pid(JSONUtils.getString(jsonObject, "indus_pid", ""));
                                    orderBean.setStart_time(JSONUtils.getString(jsonObject, "start_time", ""));
                                    orderBean.setAddress(JSONUtils.getString(jsonObject, "address", ""));
                                    orderBean.setTask_status(JSONUtils.getString(jsonObject, "task_status", ""));
                                    orderBean.setTask_status_content(JSONUtils.getString(jsonObject, "task_status_content", ""));
                                    orderBeanList.add(orderBean);
                                }
                                orderListAdapter.setItems(orderBeanList);
                                orderListAdapter.notifyDataSetChanged();
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


    public void getDetailFromService (int task_id, int indus_pid) {
        Map param = new HashMap();
        param.put("task_id", task_id);
        param.put("indus_pid", indus_pid);
        BaseRequest.instance().doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_ORDERDETAIL, param, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String msg = responseBean.getMsg();
                if (code == 0) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(responseBean.getData());
                        setOrderDetailInfoAndShow(jsonObject);
                        Status = 1;
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

    private void setOrderDetailInfoAndShow(JSONObject jsonObject) {
        orderDetailBean.setUid(JSONUtils.getInt(jsonObject, "uid", 0));
        orderDetailBean.setTask_id(JSONUtils.getInt(jsonObject, "task_id", 0));
        orderDetailBean.setIndus_pid(JSONUtils.getString(jsonObject, "indus_pid", ""));
        orderDetailBean.setIndus_id(JSONUtils.getString(jsonObject, "indus_id", ""));
        orderDetailBean.setWeek_name(JSONUtils.getString(jsonObject, "week_name", ""));
        orderDetailBean.setStart_time(JSONUtils.getString(jsonObject, "start_time", ""));
        orderDetailBean.setEnd_time(JSONUtils.getString(jsonObject, "end_time", ""));
        orderDetailBean.setWork_time(JSONUtils.getString(jsonObject, "work_time", ""));
        orderDetailBean.setAddress(JSONUtils.getString(jsonObject, "address", ""));
        orderDetailBean.setSupplies_name(JSONUtils.getString(jsonObject, "supplies_name", ""));
        orderDetailBean.setTask_cash(JSONUtils.getString(jsonObject, "task_cash", ""));
        orderDetailBean.setPaied_cash(JSONUtils.getString(jsonObject, "paied_cash", ""));
        orderDetailBean.setTruename(JSONUtils.getString(jsonObject, "truename", ""));
        orderDetailBean.setContact(JSONUtils.getString(jsonObject, "contact", ""));
        orderDetailBean.setTask_status(JSONUtils.getString(jsonObject, "task_status", ""));
        orderDetailBean.setTask_status_content(JSONUtils.getString(jsonObject, "task_status_content", ""));
        orderDetailBean.setEmployment_uid(JSONUtils.getString(jsonObject, "employment_uid", ""));
        orderDetailBean.setEmployment_truename(JSONUtils.getString(jsonObject, "employment_truename", ""));
        orderDetailBean.setEmployment_mobile(JSONUtils.getString(jsonObject, "employment_mobile", ""));
        orderDetailBean.setAid_star(JSONUtils.getString(jsonObject, "aid_star", ""));
        orderDetailBean.setMark_content(JSONUtils.getString(jsonObject, "mark_content", ""));
        orderDetailBean.setSuggest(JSONUtils.getString(jsonObject, "suggest", ""));
        orderDetailBean.setEmployment_typ(JSONUtils.getString(jsonObject, "employment_typ", ""));
        orderDetailBean.setTry_days(JSONUtils.getString(jsonObject, "try_days", ""));
        orderDetailBean.setEmployment_month(JSONUtils.getString(jsonObject, "employment_month", ""));
        orderDetailBean.setMark_id(JSONUtils.getString(jsonObject, "mark_id", ""));
        showViewDetail();
    }
    private void updataOrderStates() {
        Map param = new HashMap();
        param.put("task_id", orderDetailBean.getTask_id());
        param.put("indus_pid", orderDetailBean.indus_pid());
        param.put("type", 3);

        BaseRequest.instance().doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_ORDERUPDATE, param, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String msg = responseBean.getMsg();
                if (code == 0) {
                    getDetailFromService(orderDetailBean.getTask_id(), orderDetailBean.indus_pid());
                } else {
                    Toast.makeText(CurrentOrderActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(VolleyError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (Status == 1) {
            showList();
            Status = 0;

        } else {

            super.onBackPressed();
        }
    }

    /**
     * 评价页面 1 - 新增评价 2 - 修改评价
     * @param flag
     */
    private void startEvaluateActivity(int flag) {
        Intent intent  = new Intent(this, EvaluateActivity.class);
        intent.putExtra("orderid", orderDetailBean.getTask_id());
        intent.putExtra("mark_id", orderDetailBean.getMark_id());
        intent.putExtra("flag", flag);
        this.startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // 评价返回
            if (requestCode == 1){
                getDetailFromService(orderDetailBean.getTask_id(), orderDetailBean.indus_pid());

            }
        }
    }

    @Override
    protected void onDestroy() {
        BaseRequest.instance().cancelRequst(Tag);
        super.onDestroy();
    }
}

