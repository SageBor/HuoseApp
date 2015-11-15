package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import team.house.cn.HuoseApp.R;

/**
 * Created by kn on 15/11/14.
 */
public class OrderPayActivity extends BaseActivity {
    private Button payButton;
    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_order_pay);
        payButton = (Button) findViewById(R.id.bt_pay);

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        payButton.setOnClickListener(this);
    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        Intent intent = getIntent();
        intent.setClass(this, CommitOrderActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("订单支付");
        mCityView.setVisibility(View.GONE);
        mLeftView.setVisibility(View.VISIBLE);
        mRightView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
