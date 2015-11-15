package team.house.cn.HuoseApp.activity;

import android.view.View;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.asytask.BaseRequest;

/**
 * Created by kn on 15/11/14.
 */
public class MyBalanceActivity extends BaseActivity {
    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_my_balance);
    }

    @Override
    protected void initData() {
        super.initData();
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
        mTitleView.setText("我的余额");
        mCityView.setVisibility(View.GONE);
        mLeftView.setVisibility(View.VISIBLE);
        mRightView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
