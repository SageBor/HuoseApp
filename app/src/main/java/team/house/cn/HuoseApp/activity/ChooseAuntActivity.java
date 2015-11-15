package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
import team.house.cn.HuoseApp.adapter.ChooseAuntAdapter;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.bean.AuntBean;
import team.house.cn.HuoseApp.bean.CityBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.CityUtil;
import team.house.cn.HuoseApp.utils.JSONUtils;

/**
 * Created by kn on 15/11/13.
 * 系统选择/自助选择阿姨
 */
public class ChooseAuntActivity extends BaseActivity {
    private ListView lv_aunts;
    private List<AuntBean> auntBeanList;
    private ChooseAuntAdapter chooseAuntAdapter;
    private int mType;
    private int mPositoin;
    private int mPageSize = 10;
    private int mPageNum = 1;
    private String mSearchContent = "";
    private CityBean mCityBean;
    private int  mAuntId;
    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_chooseaunt);
        lv_aunts = (ListView) findViewById(R.id.lv_aunts);
        auntBeanList = new ArrayList<AuntBean>();
        chooseAuntAdapter = new ChooseAuntAdapter(this, auntBeanList);
        lv_aunts.setAdapter(chooseAuntAdapter);


    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        mType = intent.getIntExtra("type", 1); //默认系统选择
        mPositoin = intent.getIntExtra("positoin", 1);// 默认小时工类别
        mCityBean = CityUtil.getCity(AppConfig.Preference_RealCityNameFromService);
        if (mCityBean != null) {
            getAuntListFromServer();
        } else
        {
            Toast.makeText(this, "当前定位城市失败,请重新选择城市", Toast.LENGTH_SHORT);
        }

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        lv_aunts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAuntId = auntBeanList.get(position).getUid();
                Intent intent = new Intent(ChooseAuntActivity.this, AuntDetailActivity.class);
                intent.putExtra("auntId", mAuntId);
                ChooseAuntActivity.this.startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK ) {
            //数据是使用Intent返回
            Intent intent = new Intent();
            //把返回数据存入Intent
            intent.putExtra("auntId", mAuntId);
            //设置返回数据
            this.setResult(RESULT_OK, intent);
            //关闭Activity
            this.finish();
        }
    }


    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("选择阿姨");
        mCityView.setVisibility(View.GONE);
        mLeftView.setVisibility(View.VISIBLE);
        mRightView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void getAuntListFromServer() {
        Map map = new HashMap();
        map.put("indus_pid", mPositoin);
        map.put("group_id", "3");
//        map.put("province", mCityBean.getProvinceId());
//        map.put("city",mCityBean.getCityId());
//        map.put("search_content",mSearchContent);
//        map.put("page_size", mPageSize);
//        map.put("page_num", mPageNum);
        BaseRequest.instance().doRequest(Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_AUNTLIST, map, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String msg = responseBean.getMsg();
                if (code == 0) {
                    try {
                        JSONArray data = new JSONArray(responseBean.getData());
                        if (data != null && data.length() > 0) {
                            for (int i = 0; i < data.length(); i++) {
                                AuntBean auntBean = new AuntBean();
                                JSONObject jsonObject = data.getJSONObject(i);
                                auntBean.setUid(JSONUtils.getInt(jsonObject, "uid", 0));
                                auntBean.setTruename(JSONUtils.getString(jsonObject, "truename", ""));
                                auntBean.setMobile(JSONUtils.getString(jsonObject, "mobile", ""));
                                auntBean.setSeller_good_num(JSONUtils.getInt(jsonObject, "seller_good_num", 0));
                                auntBean.setTake_num(JSONUtils.getInt(jsonObject, "seller_good_num", 0));
                                auntBean.setSkill_names(JSONUtils.getString(jsonObject, "skill_names", ""));
                                auntBean.setAge(JSONUtils.getString(jsonObject, "age", ""));
                                auntBean.setHometown(JSONUtils.getString(jsonObject, "hometown", ""));
                                auntBean.setUser_pic(JSONUtils.getString(jsonObject, "user_pic", ""));
                                auntBeanList.add(auntBean);
                            }
                            chooseAuntAdapter.addItems(auntBeanList);
                            chooseAuntAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void failure(VolleyError error) {

            }
        });

    }
}
