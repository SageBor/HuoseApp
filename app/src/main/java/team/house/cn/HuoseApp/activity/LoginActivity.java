package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.Touch;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.application.HouseApplication;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.CommonTools;
import team.house.cn.HuoseApp.utils.JSONUtils;
import team.house.cn.HuoseApp.utils.PreferenceUtil;
import team.house.cn.HuoseApp.utils.UserUtil;

/**
 * Created by kenan on 15/11/7.
 */
public class LoginActivity extends BaseActivity {
    private final String Tag = "LoginActivity";
    private EditText mPhoneEditText;
    private EditText mPSWEditText;
    private Button mLoginBotton;
    private TextView mUnRegisteredTextView;
    private TextView mFogetPSWTextView;
    private Animation mShakeAnimation;

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_login);
        mPhoneEditText = (EditText) findViewById(R.id.et_phone);
        mPSWEditText = (EditText) findViewById(R.id.et_psw);
        mLoginBotton = (Button) findViewById(R.id.bt_login);
        mUnRegisteredTextView = (TextView) findViewById(R.id.tv_unregistered);
        mFogetPSWTextView = (TextView) findViewById(R.id.tv_unpwd);
        mShakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mLoginBotton.setOnClickListener(this);
        mUnRegisteredTextView.setOnClickListener(this);
        mFogetPSWTextView.setOnClickListener(this);
    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        int tag = v.getId();
        if (tag == R.id.bt_login) {
            String phoneNum = getPhoneNum();
            if (TextUtils.isEmpty(phoneNum)) {
                Toast.makeText(this, "手机号必须为11位", Toast.LENGTH_SHORT).show();
            } else {
                String passWord = mPSWEditText.getText().toString();
                if (TextUtils.isEmpty(passWord)){
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    loginCommit();
                }
            }


        }

        if (tag == R.id.tv_unregistered) {
            startRegisterActivity();
        }

        if (tag == R.id.tv_unpwd) {

        }
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("用户登陆");
        mLeftView.setVisibility(View.VISIBLE);
        mCityView.setVisibility(View.GONE);
        mRightView.setVisibility(View.GONE);
    }

    private void startRegisterActivity() {
        this.startActivity(new Intent(this, ResgisterAvtivity.class));
    }

    private void loginCommit() {
        Map params = new HashMap();
        params.put("username", mPhoneEditText.getText());
        params.put("password", mPSWEditText.getText());
        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_LOGIN, params, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codeMsg = responseBean.getMsg();
                if (code == 0) {
                    try {
                        JSONObject data = new JSONObject(responseBean.getData());
                        PreferenceUtil.putString(HouseApplication.getHuoYunApplicationContext(), "userinfo", responseBean.getData());
                        PreferenceUtil.putInt(HouseApplication.getHuoYunApplicationContext(), "userId", JSONUtils.getInt(data, "uid", 0));
                        PreferenceUtil.putString(HouseApplication.getHuoYunApplicationContext(), "addressinfo", JSONUtils.getString(data, "addresses", ""));
                        LoginActivity.this.destroyActivity();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this, "请求失败,请稍后再试", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, codeMsg, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void failure(VolleyError error) {
                Toast.makeText(LoginActivity.this, "网络请求失败,请稍后再试", Toast.LENGTH_LONG).show();

            }
        });

    }
    private String getPhoneNum() {
        String phonenumber = mPhoneEditText.getText().toString().trim();
        if (!CommonTools.checkPhone(phonenumber)) {
            mPhoneEditText.requestFocus();
            mPhoneEditText.startAnimation(mShakeAnimation);
            return "";
        }
        return phonenumber;
    }
}
