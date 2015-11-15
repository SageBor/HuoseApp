package team.house.cn.HuoseApp.activity;

import android.view.View;

import team.house.cn.HuoseApp.R;

/**
// * Created by kn on 15/11/14.
 */
public class EvaluateActivity extends BaseActivity {
    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_evaluate);
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
        mTitleView.setText("服务评价");
        mCityView.setVisibility(View.GONE);
        mRightView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
