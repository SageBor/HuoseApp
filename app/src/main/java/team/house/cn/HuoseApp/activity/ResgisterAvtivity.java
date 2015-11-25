package team.house.cn.HuoseApp.activity;

import android.text.TextUtils;
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
public class ResgisterAvtivity extends BaseActivity {
    private final String Tag = "ResgisterAvtivity";
    private EditText mPhoneEditText;
    private EditText mPSWEditText;
    private Button mGetPswButton;
    private Button mSureBotton;
    private TextView mRegisteredTextView;
    private TextView mHelpTextView;
    private Animation mShakeAnimation;
    private EditText pwdEdite;

    private EditText pwdEditeTwo;



    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_register);
        mPhoneEditText = (EditText) findViewById(R.id.et_phone);
        mPSWEditText = (EditText) findViewById(R.id.et_code);
        mGetPswButton = (Button) findViewById(R.id.bt_getcode);
        mSureBotton = (Button) findViewById(R.id.bt_sure);
        mRegisteredTextView = (TextView) findViewById(R.id.tv_registered);
        mHelpTextView = (TextView) findViewById(R.id.tv_helpdoc);
        mShakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        pwdEdite = (EditText) findViewById(R.id.et_psw);
        pwdEditeTwo = (EditText) findViewById(R.id.et_psw2);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mGetPswButton.setOnClickListener(this);
        mSureBotton.setOnClickListener(this);
        mRegisteredTextView.setOnClickListener(this);
        mHelpTextView.setOnClickListener(this);
    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        int tag = v.getId();
        if (tag == R.id.bt_getcode) {
            String phoneNum = getPhoneNum();
            if (TextUtils.isEmpty(phoneNum)) {
                Toast.makeText(this, "手机号必须为11位", Toast.LENGTH_SHORT).show();
            } else {
                getCodeFromSerice();
            }

        }

        if (tag == R.id.bt_sure) {
            String phoneNum = getPhoneNum();
            if (TextUtils.isEmpty(phoneNum)) {
                Toast.makeText(this, "手机号必须为11位", Toast.LENGTH_SHORT).show();
            } else {
                String code = mPSWEditText.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                } else {
                    String pwdOne = pwdEdite.getText().toString();
                    String pwdTwo = pwdEditeTwo.getText().toString();
                    if(TextUtils.isEmpty(pwdOne)) {
                        Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    } else  {
                        if (TextUtils.isEmpty(pwdTwo)) {
                            Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                        } else {
                            if (pwdOne.equals(pwdTwo)) {
                                startRegisterActivity();
                            } else {
                                Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

            }

//            startRegisterActivity();
        }

        if (tag == R.id.tv_registered) {

        }

        if (tag == R.id.tv_helpdoc) {

        }
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("用户注册");
        mLeftView.setVisibility(View.VISIBLE);
        mCityView.setVisibility(View.GONE);
        mRightView.setVisibility(View.GONE);
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
    private void startRegisterActivity () {
        Map param = new HashMap();
        param.put("mobile", mPhoneEditText.getText().toString().trim());
        param.put("code",mPSWEditText.getText().toString());
        param.put("group_id","4");
        param.put("password",pwdEdite.getText().toString().toString());
        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_REGISTER, param, new BaseResponse() {
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
                    } catch (JSONException e) {
                        e.printStackTrace();

                        Toast.makeText(ResgisterAvtivity.this, "系统异常,请稍后重试", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(ResgisterAvtivity.this, codeMsg, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void failure(VolleyError error) {
                Toast.makeText(ResgisterAvtivity.this, "网络请求失败,请稍后重试", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getCodeFromSerice() {
        Map param = new HashMap();
        param.put("mobile", "");
        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_CODE, param, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codeMsg = responseBean.getMsg();
                if (code == 0) {
                   Toast.makeText(ResgisterAvtivity.this, "请求已发送,请耐心等待", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ResgisterAvtivity.this, codeMsg, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void failure(VolleyError error) {

                Toast.makeText(ResgisterAvtivity.this, "请求失败,请稍后重试", Toast.LENGTH_SHORT).show();
            }
        });
        }
}
