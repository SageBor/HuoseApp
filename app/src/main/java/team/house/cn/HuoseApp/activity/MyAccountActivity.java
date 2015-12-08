package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import team.house.cn.HuoseApp.R;

/**
 * Created by kn on 15/11/14.
 */
public class MyAccountActivity extends BaseActivity {
    private ImageView iv_aunt;
    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_my_account);
        iv_aunt = (ImageView) findViewById(R.id.iv_aunt);
        Intent intent=getIntent();
        String pic=intent.getStringExtra("pic");
        String balance=intent.getStringExtra("myBalance");
        TextView tv_balance=(TextView) findViewById(R.id.tv_balance);
        TextView tv_balance1=(TextView) findViewById(R.id.tv_balance1);
        tv_balance1.setText(((balance==null)?"0":balance)+"元 充值");
        tv_balance.setText("余额："+balance+"元");
        String noPay=intent.getStringExtra("noPay");
        TextView tv_coupon=(TextView) findViewById(R.id.tv_coupon);
        tv_coupon.setText("未结算："+noPay+"元");
        String username=intent.getStringExtra("username");
        TextView tv_username=(TextView) findViewById(R.id.tv_username);
        tv_username.setText(username);
        setView(pic);
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
        mTitleView.setText("我的账户");
        mCityView.setVisibility(View.GONE);
        mLeftView.setVisibility(View.VISIBLE);
        mRightView.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
    private void setView (String pic) {
        Picasso.with(this)
                .load(pic)
                .placeholder(R.drawable.user)
                .error(R.drawable.user)
                .tag(this)
                .into(iv_aunt);
    }
}
