package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.house.cn.HuoseApp.Dao.Users;
import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.bean.CommitReservationServiceBean;
import team.house.cn.HuoseApp.bean.ServiceToolsBean;
import team.house.cn.HuoseApp.bean.ServiceWeekBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.UserUtil;

/**
 * Created by kn on 15/11/14.
 */
public class CommitOrderActivity extends BaseActivity {

    public final String Tag =  "CommitOrderActivity";
    private CommitReservationServiceBean mCommitReservationServiceBean;
    private Users mUser;
    private Button mSureButton;
    private Button mUpdateButton;
    private TextView mServiceContentTextView;
    private TextView mServiceTimeTextView;
    private TextView mServiceAddressTextView;
    private TextView mCouponsTextView;
    private TextView mMaginTextView;
    private TextView mContactTextView;
    private TextView mMobileTextView;
    private TextView mOrderStatusTextView;
    private CheckBox mDealCheckBox;


    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_commitorder);
        mSureButton = (Button) findViewById(R.id.bt_sure_commit);
        mUpdateButton = (Button) findViewById(R.id.bt_update);
        mServiceContentTextView = (TextView) findViewById(R.id.tv_serviceContent);
        mServiceTimeTextView = (TextView) findViewById(R.id.tv_serviceTime);
        mServiceAddressTextView = (TextView) findViewById(R.id.tv_serviceAddress);
        mCouponsTextView = (TextView) findViewById(R.id.tv_coupons);
        mMaginTextView = (TextView) findViewById(R.id.tv_margin);
        mContactTextView = (TextView) findViewById(R.id.tv_contact);
        mMobileTextView = (TextView) findViewById(R.id.tv_contact_mobile);
        mOrderStatusTextView = (TextView) findViewById(R.id.tv_ordertate);
        mDealCheckBox = (CheckBox) findViewById(R.id.ck_deal);

    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        mCommitReservationServiceBean = (CommitReservationServiceBean) intent.getSerializableExtra("CommitReservationServiceBean");

        mUser = UserUtil.getUserinfoFromSharepreference();
        if (mUser == null) {
            Toast.makeText(this, "用户退出,请重新登录", Toast.LENGTH_SHORT).show();
        } else {
            updateView();
        }

    }

    private void updateView() {
        mServiceContentTextView.setText("服务信息:" + mCommitReservationServiceBean.getServiceContentBean().getIndus_name());
        mServiceTimeTextView.setText(getTIme());
        mServiceAddressTextView.setText("服务地址:" + mCommitReservationServiceBean.getAddressBean().getmAddressAll());
        mCouponsTextView.setText("优惠券:25元优惠券");
        mMaginTextView.setText("保证金:" + mCommitReservationServiceBean.getPaied_cash());
        mContactTextView.setText("联系人:" + mUser.getTruename());
        mMobileTextView.setText("联系电话:" + mUser.getMobile());




    }

    private String getTIme() {
        switch (mCommitReservationServiceBean.getIndus_pid()) {
            case 1:

            case 2:
                return "服务时间:" + mCommitReservationServiceBean.getStart_time() + "  " + mCommitReservationServiceBean.getStartHouBean().getHour_name() + " - "
                        + mCommitReservationServiceBean.getEnd_time() + "  " + mCommitReservationServiceBean.getEndHouBean().getHour_name();
            case 3:

            case 4:
                return "服务时间:" + mCommitReservationServiceBean.getStart_time() + "  " + mCommitReservationServiceBean.getStartHouBean().getHour_name() + " - 共计"
                        + mCommitReservationServiceBean.getServiceEmploymentMonth().getEmployment_month_name();

        }
        return "服务时间:";
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mSureButton.setOnClickListener(this);
        mUpdateButton.setOnClickListener(this);
    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        int viewId = v.getId();
        if (viewId == R.id.bt_sure_commit) {
            if (mDealCheckBox.isChecked()) {
                commitService();
            } else {
                Toast.makeText(this, "请阅读并同意三个阿姨协议服务", Toast.LENGTH_SHORT).show();
            }

        }
        if (viewId == R.id.bt_update) {
            this.finish();
        }
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("提交订单");
        mCityView.setVisibility(View.GONE);
        mRightView.setVisibility(View.GONE);
        mLeftView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private String getWeeksId() {
        List<ServiceWeekBean> serviceWeekBeanList = mCommitReservationServiceBean.getServiceWeekBeanList();
        StringBuffer stringBuffer = new StringBuffer();
        if (serviceWeekBeanList != null && serviceWeekBeanList.size() > 0) {
            for (ServiceWeekBean serviceWeekBean : serviceWeekBeanList) {
                stringBuffer.append(serviceWeekBean.getWeek_id()).append(",");
            }
            return stringBuffer.toString();
        }
        return "";
    }

    private String getToolIds() {
        List<ServiceToolsBean> serviceToolsBeanList = mCommitReservationServiceBean.getServiceToolsBeanList();
        StringBuffer stringBuffer = new StringBuffer();
        if (serviceToolsBeanList != null && serviceToolsBeanList.size() > 0) {
            for (ServiceToolsBean serviceToolsBean : serviceToolsBeanList) {
                stringBuffer.append(serviceToolsBean.getSupplies_id()).append(",");
            }
            return stringBuffer.toString();
        }
        return "";
    }

    private void commitService() {
        Users user=  UserUtil.getUserinfoFromSharepreference();
        Map params = new HashMap<>();
        params.put("data[uid]", mUser.getUid());
        params.put("data[username]", mUser.getUsername());
        params.put("data[indus_pid]", mCommitReservationServiceBean.getIndus_pid());
        params.put("data[indus_id]", mCommitReservationServiceBean.getServiceContentBean().getIndus_id());
        params.put("data[employment_typ]", mCommitReservationServiceBean.getServiceModelBean() == null ? "" : mCommitReservationServiceBean.getServiceModelBean().getEmployment_typ());
        params.put("data[week_id]", getWeeksId());
        params.put("data[try_days]", mCommitReservationServiceBean.getServiceTryDayBean() == null ? "" : mCommitReservationServiceBean.getServiceTryDayBean().getTry_days());
        params.put("data[start_time]", mCommitReservationServiceBean.getStart_time());
        params.put("data[end_time]", mCommitReservationServiceBean.getEnd_time());
        params.put("data[employment_month]", mCommitReservationServiceBean.getServiceEmploymentMonth() == null ? "" : mCommitReservationServiceBean.getServiceEmploymentMonth().getEmployment_month());
        params.put("data[price]", mCommitReservationServiceBean.getChoosePriceBean().getPrice());// 心里价位
        params.put("data[employment_uid]", mCommitReservationServiceBean.getEmployment_uid()); //阿姨id
        params.put("data[task_desc]", ""); //备注
        params.put("data[pay_typ]", 1); // 支付方式
        params.put("data[model_id]", mCommitReservationServiceBean.getModel_id()); // 发单模式
        params.put("data[task_cash]", mCommitReservationServiceBean.getTask_cash()); //订单金额
        params.put("data[paied_cash]", mCommitReservationServiceBean.getPaied_cash()); //保证金
        params.put("data[mobile]", mUser.getMobile()); //手机号
        params.put("data[truename]", user.getUsername());//真实姓名
        params.put("data[supplies_id]", getToolIds()); //保洁用品id
        params.put("data[address_id]", mCommitReservationServiceBean.getAddressBean().getmAddlesId()); //服务地址id
        params.put("data[start_hour]", mCommitReservationServiceBean.getStartHouBean().getHour()); // /开始时刻
        if(mCommitReservationServiceBean.getEndHouBean()!=null){
            params.put("data[end_hour]", mCommitReservationServiceBean.getEndHouBean().getHour()); //结束时刻
        }
        params.put("data[work_days]", mCommitReservationServiceBean.getWorkDays()); //工作天数 只有长期钟点工用
        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_COMMIT_ORDER, params, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codeMsg = responseBean.getMsg();
                if (code == 0) {
                    Intent intent = new Intent();
                    intent.setClass(CommitOrderActivity.this, CurrentOrderActivity.class);
                    CommitOrderActivity.this.startActivity(intent);
                    CommitOrderActivity.this.destroyActivity();
                } else {

                    Toast.makeText(CommitOrderActivity.this, codeMsg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(VolleyError error) {


                Toast.makeText(CommitOrderActivity.this, "网络请求失败,请稍后重试", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
