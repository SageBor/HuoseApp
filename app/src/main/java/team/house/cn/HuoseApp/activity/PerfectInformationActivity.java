package team.house.cn.HuoseApp.activity;

import android.view.View;

import team.house.cn.HuoseApp.R;

/**
 * Created by kn on 15/11/14.
 */
public class PerfectInformationActivity extends BaseActivity {
    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_perfect_information);
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
        mTitleView.setText("完善资料");
        mCityView.setVisibility(View.GONE);
        mLeftView.setVisibility(View.VISIBLE);
        mRightView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
