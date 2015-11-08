package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.adapter.ServiceContentAdaper;
import team.house.cn.HuoseApp.bean.ServiceContentBean;

/**
 * Created by kenan on 15/11/7.
 * 预约服务
 */
public class ReservationServiceActivity extends BaseActivity {
    private RelativeLayout mServiceAddressRelativeLayout;
    private TextView mServiceAddressTextView;
    private GridView mServiceContentGridView; // 服务内容
    private ServiceContentAdaper mServiceContentAdaper; // 服务内容适配器
    private List<ServiceContentBean>  mServiceContentBeanList; // 服务内容集合
    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_reservationservice);
        mServiceAddressRelativeLayout = (RelativeLayout) findViewById(R.id.rl_serviceAddress);
        mServiceAddressTextView = (TextView) findViewById(R.id.tv_serviceAddress);
        mServiceContentGridView = (GridView) findViewById(R.id.gv_serviceInfo);
        mServiceContentBeanList = new ArrayList<ServiceContentBean>();
        mServiceContentAdaper = new ServiceContentAdaper(mServiceContentBeanList, this);
        mServiceContentGridView.setAdapter(mServiceContentAdaper);

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mServiceAddressRelativeLayout.setOnClickListener(this);
        mServiceContentGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        int viewId = v.getId();
        if (viewId == R.id.tv_serviceAddress) {
            // 进入选择地址页面
            this.startActivityForResult(new Intent(this, ChooseAddressActivity.class), 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            String addressId = data.getStringExtra("address_id");
            String addressInfo = data.getStringExtra("address_info");
            if (TextUtils.isEmpty(addressId) && TextUtils.isEmpty(addressInfo)){
                mServiceAddressTextView.setText(addressInfo);
            }
        }
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("预约服务");
    }
}
