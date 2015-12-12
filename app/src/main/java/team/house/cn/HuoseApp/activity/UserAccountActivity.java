package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import team.house.cn.HuoseApp.Dao.Users;
import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.application.HouseApplication;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.JSONUtils;
import team.house.cn.HuoseApp.utils.PreferenceUtil;
import team.house.cn.HuoseApp.utils.UserUtil;

/**
 * Created by kn on 15/11/14.
 * 加个注释试试好不好用
 */
public class UserAccountActivity extends BaseActivity {
    private final String Tag = "UserAccountActivity";

    private Users users;
    private TextView tv_balance;
    private TextView tv_coupon;
    private TextView tv_username;
    private RelativeLayout rlMyAccount;
    private ImageView iv_aunt;
    private RelativeLayout rlHelp;
    private RelativeLayout rlAbout;
    private RelativeLayout rlToOther;
    private RelativeLayout rlAdvice;
    private RelativeLayout rl_perfect;
    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_user_account);
        tv_balance=(TextView) findViewById(R.id.tv_balance);
        tv_coupon=(TextView) findViewById(R.id.tv_coupon);
        tv_username=(TextView) findViewById(R.id.tv_username);
        rlMyAccount=(RelativeLayout)findViewById(R.id.rl_myAccount);
        iv_aunt = (ImageView) findViewById(R.id.iv_aunt);
        rlHelp=(RelativeLayout)findViewById(R.id.rl_help);
        rlAbout=(RelativeLayout)findViewById(R.id.rl_about);
        rlToOther=(RelativeLayout)findViewById(R.id.rl_to_other);
        rlAdvice=(RelativeLayout)findViewById(R.id.rl_advice);
        rl_perfect = (RelativeLayout) findViewById(R.id.rl_perfect);
    }

    @Override
    protected void initData() {
        super.initData();
        mUcenter.setSelected(true);
        getUsersInfo();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        rlMyAccount.setOnClickListener(this);
        rlHelp.setOnClickListener(this);
        rlAbout.setOnClickListener(this);
        rlToOther.setOnClickListener(this);
        rlAdvice.setOnClickListener(this);
        rl_perfect.setOnClickListener(this);
    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        if (users == null){
            Toast.makeText(this, "用户获取中请稍后....", Toast.LENGTH_SHORT).show();
            return;
        }
        if (v.getId() == R.id.rl_about) {
            Intent intent=new Intent(this,AboutActivity.class);
            this.startActivity(intent);
        }
        if (v.getId() == R.id.rl_help) {
            Intent intent=new Intent(this,HelpActivity.class);
            this.startActivity(intent);
        }
        if (v.getId() == R.id.rl_to_other) {
            Toast.makeText(this, "该功能正在建设中，敬请期待！", Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.rl_advice) {
            Toast.makeText(this, "该功能正在建设中，敬请期待！", Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.rl_myAccount) {
            Intent intent=new Intent(this, MyAccountActivity.class);
            intent.putExtra("myBalance", users.getBalance());
            intent.putExtra("noPay",users.getNopay());
            intent.putExtra("username",users.getUsername());
            intent.putExtra("pic", users.getUser_pic());
            this.startActivity(intent);
        }
        if (v.getId() == R.id.rl_perfect) {
            if (users != null) {
                startPerfectInformationActivity();
            }
        }
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("用户中心");
        mCityView.setVisibility(View.GONE);
        mLeftView.setVisibility(View.VISIBLE);
        mRightView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
    private void getUsersInfo() {
        final Users usersInfo= UserUtil.getUserinfoFromSharepreference();
        Map params = new HashMap();
        params.put("uid", usersInfo.getUid());
        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_URSER_INFO, params, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codeMsg = responseBean.getMsg();
                if (code == 0) {
                    JSONObject data = null;
                    try {
                        data = new JSONObject(responseBean.getData());
                        users = new Users();
                        users.setUid(JSONUtils.getInt(data, "uid", 0));
                        users.setUsername(JSONUtils.getString(data, "username", ""));
                        users.setTruename(JSONUtils.getString(data, "truename", ""));
                        users.setSex(JSONUtils.getString(data, "sex", "男"));
                        users.setMarry(JSONUtils.getString(data, "marry", "是"));
                        users.setMobile(JSONUtils.getString(data, "mobile", ""));
                        users.setBirthday(JSONUtils.getString(data, "birthday", ""));
                        users.setProvince(JSONUtils.getInt(data, "province", 0));
                        users.setProvince_nm(JSONUtils.getString(data, "province_nm", ""));
                        users.setCity(JSONUtils.getInt(data, "city", 0));
                        users.setCity_nm(JSONUtils.getString(data, "city_nm", ""));
                        users.setArea(JSONUtils.getInt(data, "area", 0));
                        users.setArea_nm(JSONUtils.getString(data, "area_nm", ""));
                        users.setEmail(JSONUtils.getString(data, "email", ""));
                        users.setBalance(JSONUtils.getString(data, "balance", ""));
                        users.setUser_pic(JSONUtils.getString(data, "user_pic", ""));
                        users.setIs_perfec(JSONUtils.getBoolean(data, "is_perfect", false));
                        users.setNopay(JSONUtils.getString(data, "nopay", ""));
                        users.setIdcard(JSONUtils.getString(data, "idcard", ""));
                        users.setEducation(JSONUtils.getInt(data, "educational", 1));
                        users.setWork_exp(JSONUtils.getString(data, "work_exp", ""));

                        PreferenceUtil.putString(HouseApplication.getHuoYunApplicationContext(), "userinfo", responseBean.getData());
                        PreferenceUtil.putInt(HouseApplication.getHuoYunApplicationContext(), "userId", JSONUtils.getInt(data, "uid", 0));
                        PreferenceUtil.putString(HouseApplication.getHuoYunApplicationContext(), "addressinfo", JSONUtils.getString(data, "addresses", ""));

                        setView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else {
                    Toast.makeText(UserAccountActivity.this, codeMsg, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void failure(VolleyError error) {
                Toast.makeText(UserAccountActivity.this, "网络请求失败,请稍后再试", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void startPerfectInformationActivity() {
        Intent intent = new Intent (this, PerfectInformationActivity.class);
        intent.putExtra("user", users);
        this.startActivity(intent);

    }
    private void setView () {
        Picasso.with(this)
                .load(users.getUser_pic())
                .placeholder(R.drawable.user)
                .error(R.drawable.user)
                .tag(this)
                .into(iv_aunt);

        tv_balance.setText("余额：" + users.getBalance() + "元");
        tv_coupon.setText("未结算：" + users.getNopay() + "元");
        tv_username.setText(users.getUsername());
    }

}
