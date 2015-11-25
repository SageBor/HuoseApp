package team.house.cn.HuoseApp.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.JSONUtils;
import team.house.cn.HuoseApp.views.XRatingBar;

/**
// * Created by kn on 15/11/14.
 */
public class EvaluateActivity extends BaseActivity {
    private final String Tag = "EvaluateActivity";
    private Button mEvaluateButton;
    private XRatingBar ratingbar;
    private EditText et_sugusstEditeText;
    private EditText et_auntEvaluate;
    private int orderid;
    private String  mark_id;
    private int flag = 1;//1 - 新增评价 2 - 修改评价
    private Intent intent;

    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_evaluate);
        mEvaluateButton = (Button) findViewById(R.id.bt_evaluate);
        ratingbar = (XRatingBar) findViewById(R.id.ratingbar);
        et_sugusstEditeText = (EditText) findViewById(R.id.et_sugusst);
        et_auntEvaluate = (EditText) findViewById(R.id.et_auntEvaluate);
    }

    @Override
    protected void initData() {
        super.initData();
        intent = getIntent();
        orderid = intent.getIntExtra("orderid", 0);
        mark_id = intent.getStringExtra("mark_id");
        flag = intent.getIntExtra("flag", 1);
        if (flag == 2) {
            getEvaluateInfo();
        }

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mEvaluateButton.setOnClickListener(this);
        ratingbar.setOnRatingBarChangeListener(new XRatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(XRatingBar ratingBar, float rating, boolean fromUser) {

            }

            @Override
            public void onRatingFinished(XRatingBar ratingBar, float rating) {

            }
        });

    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        int vId = v.getId();
        if (vId == R.id.bt_evaluate) {


            if (!ratingbar.rated())
            {
                Toast.makeText(this, "请麻烦评分", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(et_sugusstEditeText.getText())){
                et_sugusstEditeText.setFocusable(true);

            } else if (TextUtils.isEmpty(et_auntEvaluate.getText())){
                et_auntEvaluate.setFocusable(true);

            } else {
                commitEvaluate();
            }


        }
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

    private void getEvaluateInfo () {
        Map map = new HashMap<>();
        map.put("mark_id", mark_id);
        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_COMMIT_EVALUATE, map, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codeMsg = responseBean.getMsg();
                if (code == 0) {
                    try {
                        JSONObject data = new JSONObject(responseBean.getData());
                        mark_id = JSONUtils.getString(data, "mark_id", "");
                        float sore = Float.parseFloat(JSONUtils.getString(data, "aid_star", "0.0"));
                        orderid = JSONUtils.getInt(data, "task_id", 0);
                        String evaluateString = JSONUtils.getString(data, "mark_content", "");
                        String suggest = JSONUtils.getString(data, "suggest", "");
                        et_auntEvaluate.setText(evaluateString);
                        et_sugusstEditeText.setText(suggest);
                        ratingbar.setRating(sore);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(EvaluateActivity.this, codeMsg, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void failure(VolleyError error) {
                Toast.makeText(EvaluateActivity.this, "网络请求失败,请稍后重试", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void commitEvaluate() {
        Map map = new HashMap<>();
        map.put("data[mark_id]", mark_id);
        map.put("data[task_id]", orderid);
        map.put("data[aid_star]", ratingbar.getRating());
        map.put("data[mark_content]", et_auntEvaluate.getText());
        map.put("data[suggest]", et_sugusstEditeText.getText());
        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_COMMIT_EVALUATE, map, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codeMsg = responseBean.getMsg();
                if (code == 0) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(EvaluateActivity.this);
                    dialog.setTitle("评价成功").setMessage("评价成功").setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            backCurrentActivity();
                        }
                    }).create().show();
                } else {
                    Toast.makeText(EvaluateActivity.this, codeMsg, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void failure(VolleyError error) {
                Toast.makeText(EvaluateActivity.this, "网络请求失败,请稍后重试", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private float getScore (){
        float score = ratingbar.getRating();

        return 0.0f;
    }

    private void backCurrentActivity() {
        intent.putExtra("orderid", orderid);
        this.setResult(RESULT_OK, intent);
        this.finish();
    }
}
