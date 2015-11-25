package team.house.cn.HuoseApp.activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.adapter.ProvinceListAdapter;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.bean.CityBean;
import team.house.cn.HuoseApp.bean.ProvinceBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.CityUtil;
import team.house.cn.HuoseApp.utils.JSONUtils;
import team.house.cn.HuoseApp.views.ExpandableLayoutListView.ExpandableLayoutListView;

/**
 * Created by kn on 15/11/25.
 */
public class CityListActivity extends BaseActivity {
    private final String Tag = "CityListActivity";
    private List<ProvinceBean> provineList = new ArrayList<ProvinceBean>();
    private List<CityBean> allCityList = new ArrayList<CityBean>();
    private TextView currentTextView;
    private ProvinceListAdapter provinceListAdapter ;
    private ExpandableLayoutListView expandableLayoutListView;
    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_citylist);
        currentTextView = (TextView) findViewById(R.id.first);
        provinceListAdapter = new ProvinceListAdapter(provineList,allCityList, this);
        expandableLayoutListView = (ExpandableLayoutListView) findViewById(R.id.listview);
        expandableLayoutListView.setAdapter(provinceListAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        currentTextView.setText("当前城市: " + CityUtil.getCityName(AppConfig.Preference_ChooseCityNameFromService));
        getCityListFromService();
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mRightView.setVisibility(View.GONE);
        mCityView.setVisibility(View.GONE);
        mLeftView.setVisibility(View.VISIBLE);
        mTitleView.setText("选择城市");
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    public void finish() {
        super.finish();
    }


    private void getCityListFromService () {
        Map param = new HashMap<>();
        BaseRequest.instance().doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_CITYLIST, param, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codeMsg = responseBean.getMsg();
                if (code == 0) {
                    try {
                        JSONObject dataJSONObject = new JSONObject(responseBean.getData());
                        if (dataJSONObject != null) {
                            JSONArray provinces = JSONUtils.getJSONArray(dataJSONObject, "provinces", null);
                            JSONArray citys = JSONUtils.getJSONArray(dataJSONObject, "cities", null);
                            if (provinces != null && provinces.length() > 0) {
                                for (int i = 0; i < provinces.length(); i++) {
                                    JSONObject province = (JSONObject) provinces.get(i);
                                    ProvinceBean provinceBean = new ProvinceBean();
                                    provinceBean.setPro_id(JSONUtils.getInt(province, "pro_id", 0));
                                    provinceBean.setPro_name(JSONUtils.getString(province, "pro_name", ""));
                                    provineList.add(provinceBean);
                                }

                            }
                            if (citys != null && citys.length() > 0) {
                                for (int i = 0; i < citys.length(); i++) {
                                    JSONObject city = (JSONObject) citys.get(i);
                                    CityBean cityBean = new CityBean();
                                    cityBean.setCityId(JSONUtils.getInt(city, "city_id", 0));
                                    cityBean.setCityName(JSONUtils.getString(city, "city_name", ""));
                                    cityBean.setProvinceId(JSONUtils.getInt(city, "pro_id", 0));
                                    allCityList.add(cityBean);
                                }
                            }
                            if (provineList.size() > 0) {
                                provinceListAdapter.notifyDataSetChanged();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else {
                    Toast.makeText(CityListActivity.this, codeMsg, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void failure(VolleyError error) {

                Toast.makeText(CityListActivity.this, "网络请求失败,请稍后重试", Toast.LENGTH_SHORT).show();

            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
