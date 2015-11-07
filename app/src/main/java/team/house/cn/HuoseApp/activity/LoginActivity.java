package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import team.house.cn.HuoseApp.R;

/**
 * Created by kenan on 15/11/7.
 */
public class LoginActivity extends BaseActivity {
    private EditText mPhoneEditText;
    private EditText mPSWEditText;
    private Button mLoginBotton;
    private TextView mUnRegisteredTextView;
    private TextView mFogetPSWTextView;
    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_login);
        mPhoneEditText = (EditText) findViewById(R.id.et_phone);
        mPSWEditText = (EditText) findViewById(R.id.et_psw);
        mLoginBotton = (Button) findViewById(R.id.bt_login);
        mUnRegisteredTextView = (TextView) findViewById(R.id.tv_unregistered);
        mFogetPSWTextView = (TextView) findViewById(R.id.tv_unpwd);
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
        if (tag == R.id.bt_login){

        }

        if (tag == R.id.tv_unregistered){
            startRegisterActivity();
        }

        if (tag == R.id.tv_unpwd){

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
}
