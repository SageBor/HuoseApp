package team.house.cn.HuoseApp.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.utils.CommonTools;
import team.house.cn.HuoseApp.utils.UserUtil;

/**
 * Created by kenan on 15/11/7.
 */
public class ResgisterAvtivity extends BaseActivity {
    private EditText mPhoneEditText;
    private EditText mPSWEditText;
    private Button mGetPswButton;
    private Button mSureBotton;
    private TextView mRegisteredTextView;
    private TextView mHelpTextView;
    private Animation mShakeAnimation;


    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_register);
        mPhoneEditText = (EditText) findViewById(R.id.et_phone);
        mPSWEditText = (EditText) findViewById(R.id.et_psw);
        mGetPswButton = (Button) findViewById(R.id.bt_getpwd);
        mSureBotton = (Button) findViewById(R.id.bt_sure);
        mRegisteredTextView = (TextView) findViewById(R.id.tv_registered);
        mHelpTextView = (TextView) findViewById(R.id.tv_helpdoc);
        mShakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
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
        if (tag == R.id.bt_getpwd) {
            String phoneNum = getPhoneNum();
            if (TextUtils.isEmpty(phoneNum)) {
                Toast.makeText(this, "手机号必须为11位", Toast.LENGTH_SHORT).show();
            } else {
                UserUtil.getCodeFromService();
            }

        }

        if (tag == R.id.bt_sure) {
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
}
