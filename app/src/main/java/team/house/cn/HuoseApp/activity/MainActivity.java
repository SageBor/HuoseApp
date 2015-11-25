package team.house.cn.HuoseApp.activity;


import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.adapter.ServiceTypeAdapter;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.CityUtil;
import team.house.cn.HuoseApp.utils.UserUtil;


/**
 * Created by kenan on 2015/10/15.
 */
public class MainActivity extends BaseActivity {
    private GridView mGridViewServiceType;
    private static final Object TAG = new Object();
    private final int mChooseCityRequestCode = 1;

    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_mian);
        mGridViewServiceType = (GridView) findViewById(R.id.gv_servicetype);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UserUtil.getUseridFromSharepreference() == 0){
            mRightView.setText("登陆");
        } else {
            mRightView.setText("退出");
        }
        showCitynfo();
    }

    @Override
    protected void initData() {
        super.initData();
        showCitynfo();
        //图片的文字标题
        String[] titles = new String[]
                {"钟点工", "长期钟点工", "保姆", "月嫂"};
        //图片ID数组
        int[] images = new int[]{
                R.drawable.clock, R.drawable.cala, R.drawable.peo,
                R.drawable.bot
        };
        ServiceTypeAdapter adapter = new ServiceTypeAdapter(titles, images, this);
        mGridViewServiceType.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mGridViewServiceType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                Toast.makeText(MainActivity.this, (position + 1), Toast.LENGTH_SHORT).show();
                Intent intent;
                int loginSate = UserUtil.getUseridFromSharepreference();
                if (loginSate == 0) {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                }else{
                    intent = new Intent(MainActivity.this, ReservationServiceActivity.class);
                    intent.putExtra("position", position + 1);
                }
                startActivity(intent);
            }
        });
        mCityView.setOnClickListener(this);
    }


    private void showCitynfo(){
        String chooseCityName = CityUtil.getCityName(AppConfig.Preference_ChooseCityNameFromService);
        if (!TextUtils.isEmpty(chooseCityName)){
            mCityView.setText(chooseCityName);
        } else {
            // 处理没有定位到城市且用户没有自选城市的情况 需要用户开启定位  重新定位
        }

    }
    private void showRightText() {
        mRightView.setText("登陆");
    }
    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        if (v.getId() == R.id.tv_right) {
            if (mRightView.getText().toString().equals("登陆")) {
                this.startActivity(new Intent(this, LoginActivity.class));
            } else {
                UserUtil.exitLogin();
                onResume();

            }
        }
        if (v.getId() == R.id.tv_city){
            this.startActivityForResult(new Intent(this, CityListActivity.class), mChooseCityRequestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == mChooseCityRequestCode) {

            }
        }
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        showRightText();
    }
}
