package team.house.cn.HuoseApp.activity;

import android.view.View;
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

    }
    private void showDetail(boolean show) {
        mOrderDetailScrollview.setVisibility(show ? View.VISIBLE : View.GONE);
        mOrderListView.setVisibility(show ? View.GONE : View.VISIBLE);

    }
    private void showViewDetail() {
        showDetail(true);
        mServiceContentTextView.setText(orderDetailBean.getIndus_id());
        mServiceWeekTextView.setText(orderDetailBean.getWeek_name());
        mServiceStartTimeTextView.setText(orderDetailBean.getStart_time());
        mServiceEndTimeTextView.setText(orderDetailBean.getEnd_time());
        mServiceWorkTimeTextView.setText(orderDetailBean.getWork_time());
        mServiceToolsTextView.setText(orderDetailBean.getSupplies_name());
        mOrderMoneyTextView.setText(orderDetailBean.getTask_cash());
        mAuntNameTextView.setText(orderDetailBean.getEmployment_truename());
        mAuntMobileTextView.setText(orderDetailBean.getEmployment_mobile());
        mSoreTextView.setText(orderDetailBean.getAid_star());
        mSoreContentTextView.setText(orderDetailBean.getMark_content());
        mSuggestTextView.setText(orderDetailBean.getSuggest());
        mServiceModelTextView.setText(orderDetailBean.getEmployment_typ());
        mServiceTryDateTextView.setText(orderDetailBean.getTry_days());
        mServiceEmploymentTextView.setText(orderDetailBean.getEmployment_month());
        mServiceAddressTextView.setText(orderDetailBean.getAddress());
        mCouponsTextView.setText("");
        mMarginTextView.setText(orderDetailBean.getPaied_cash());
        mContactTextView.setText(orderDetailBean.getTruename());
        mContactMobileTextView.setText(orderDetailBean.getContact());
        mOrderStateTextView.setText(orderDetailBean.getTask_status());
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
    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
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
        BaseRequest.instance().doRequest(Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_CURRENT_ORDER, param, new BaseResponse() {
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
                                showViewDetail();

                            } else {
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

