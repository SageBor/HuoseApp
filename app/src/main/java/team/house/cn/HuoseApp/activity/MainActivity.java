package team.house.cn.HuoseApp.activity;


import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.adapter.ServiceTypeAdapter;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.CityUtil;


/**
 * Created by kenan on 2015/10/15.
 */
public class MainActivity extends BaseActivity {
    private GridView mGridViewServiceType;
    private static final Object TAG = new Object();

    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_mian);
        mGridViewServiceType = (GridView) findViewById(R.id.gv_servicetype);
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
                Toast.makeText(MainActivity.this, (position + 1), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showCitynfo(){
        String chooseCityName = CityUtil.getCityName(AppConfig.Preference_ChooseCityNameFromService);
        if (!TextUtils.isEmpty(chooseCityName)){
            mCityView.setText(chooseCityName);
        } else {
            // 处理没有定位到城市且用户没有自选城市的情况 需要用户开启定位  重新定位
        }

    }
    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
    }

    @Override
    protected void initTitle() {
        super.initTitle();

    }



}
