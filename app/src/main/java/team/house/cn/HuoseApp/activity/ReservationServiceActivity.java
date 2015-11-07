package team.house.cn.HuoseApp.activity;

import team.house.cn.HuoseApp.R;

/**
 * Created by kenan on 15/11/7.
 * 预约服务
 */
public class ReservationServiceActivity extends BaseActivity {
    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_reservationservice);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("预约服务");
    }
}
