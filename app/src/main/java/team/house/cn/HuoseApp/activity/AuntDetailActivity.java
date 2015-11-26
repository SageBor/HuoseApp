package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import android.widget.Toast;
import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.bean.AuntDetailBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.JSONUtils;

/**
 * Created by kn on 15/11/14.
 */
public class AuntDetailActivity extends BaseActivity{

    private int  mAuntId;
    private ImageView mAnuntImageView;
    private TextView mAuntNameTextView;
    private TextView mAnutMobeilTextView;
    private TextView mAuntScoreTextView;
    private TextView mAuntServiceTimeTextView;
    private TextView mAuntServiceTextView;
    private TextView mAuntAgeTextView;
    private TextView mAuntCarNumTextView;
    private TextView mAuntvAlidationTextView;
    private int task_id;
    private Button mCommitBuuton;
    public final String Tag =  "AuntDetailActivity";
    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_auntdetail);
        mAnuntImageView = (ImageView) findViewById(R.id.iv_aunt);
        mAuntNameTextView = (TextView) findViewById(R.id.txt_auntname);
        mAnutMobeilTextView = (TextView) findViewById(R.id.tv_mobile);
        mAuntScoreTextView = (TextView) findViewById(R.id.txt_auntscore);
        mAuntServiceTimeTextView = (TextView) findViewById(R.id.txt_service_time);
        mAuntServiceTextView = (TextView) findViewById(R.id.tv_service);
        mAuntAgeTextView = (TextView) findViewById(R.id.tv_age);
        mAuntCarNumTextView = (TextView) findViewById(R.id.tv_carNum);
        mAuntvAlidationTextView = (TextView) findViewById(R.id.tv_validation);
        mCommitBuuton = (Button) findViewById(R.id.bt_commit);


    }
    private void showDetail(AuntDetailBean auntBean){
        mAuntNameTextView.setText(auntBean.getTruename());
        mAnutMobeilTextView.setText(auntBean.getMobile());
        mAuntScoreTextView.setText(auntBean.getSeller_good_num() == 0 ? "暂无评价" : "好评数:" + auntBean.getSeller_good_num());
        mAuntServiceTimeTextView.setText("服务:" + auntBean.getTake_num() + "次");
        mAuntServiceTextView.setText("技能:" + auntBean.getSkill_names());
        mAuntAgeTextView.setText("年龄:" + auntBean.getAge() + "    " + auntBean.getHometown() + "人");
        mAuntCarNumTextView.setText("身份证:" + auntBean.getIdcard());
        mAuntvAlidationTextView.setText(auntBean.getAuth_info() == 1 ? " 已通过身份验证": "未通过身份验证");
        loadDriverImg(auntBean.getUser_pic());



    }
    @Override
    protected void initData() {
        super.initData();
        mAuntId = getIntent().getIntExtra("auntId" , 0);
        task_id = getIntent().getIntExtra("task_id" , 0);
        if (mAuntId == 0) {

        }
        else
        {
            getDetailFromService();
        }

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mCommitBuuton.setOnClickListener(this);
    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        if(task_id>0&&mAuntId>0){

            subTaskAuntFromService();
        }else{
            Intent intent = new Intent ();
            mAuntId = getIntent().getIntExtra("auntId" , 0);
            intent.putExtra("auntId", mAuntId);
            this.setResult(RESULT_OK, intent);
            this.finish();
        }

    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("阿姨详情");
        mCityView.setVisibility(View.GONE);
        mLeftView.setVisibility(View.VISIBLE);
        mRightView.setVisibility(View.GONE);
    }


    private void getDetailFromService() {
        Map param = new HashMap<>();
        param.put("uid", mAuntId);
        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_AUNTDETAIL, param, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String msg = responseBean.getMsg();
                if (code == 0) {
                    try {
                        JSONArray data = new JSONArray(responseBean.getData());
                        if (data != null && data.length() > 0) {
                            for (int i = 0; i < data.length(); i++) {
                                AuntDetailBean auntBean = new AuntDetailBean();
                                JSONObject jsonObject = data.getJSONObject(i);
                                auntBean.setUid(JSONUtils.getInt(jsonObject, "uid", 0));
                                auntBean.setTruename(JSONUtils.getString(jsonObject, "truename", ""));
                                auntBean.setMobile(JSONUtils.getString(jsonObject, "mobile", ""));
                                auntBean.setSeller_good_num(JSONUtils.getInt(jsonObject, "seller_good_num", 0));// 评价次数
                                auntBean.setTake_num(JSONUtils.getInt(jsonObject, "take_num", 0)); //服务次数
                                auntBean.setSkill_names(JSONUtils.getString(jsonObject, "skill_names", ""));
                                auntBean.setAge(JSONUtils.getString(jsonObject, "age", ""));
                                auntBean.setHometown(JSONUtils.getString(jsonObject, "hometown", ""));
                                auntBean.setIdcard(JSONUtils.getString(jsonObject, "idcard", ""));
                                auntBean.setUser_pic(JSONUtils.getString(jsonObject, "user_pic", ""));
                                auntBean.setAuth_info(JSONUtils.getInt(jsonObject, "auth_info", 2));
                                showDetail(auntBean);

                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {

                    Toast.makeText(AuntDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void failure(VolleyError error) {

            }
        });
    }
    private void subTaskAuntFromService() {
        Map param = new HashMap<>();
        param.put("uid", mAuntId);
        param.put("task_id", task_id);
        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_TASKAUNT, param, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String msg = responseBean.getMsg();
                if (code == 0) {
                   Intent intent = new Intent(new Intent(AuntDetailActivity.this, CurrentOrderActivity.class));
                    startActivity(intent);
                }else {

                    Toast.makeText(AuntDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void failure(VolleyError error) {

            }
        });
    }

    private void loadDriverImg(String src) {
        if (TextUtils.isEmpty(src) || src.indexOf("none.gif") > 0) {
            mAnuntImageView.setImageResource(R.drawable.user);
            return;
        }
        Picasso.with(this)
                .load(src)
                .placeholder(R.drawable.user)
                .error(R.drawable.user)
                .tag(this)
                .into(mAnuntImageView);
    }
}
