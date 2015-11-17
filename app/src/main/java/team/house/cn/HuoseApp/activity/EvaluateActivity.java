package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.hedgehog.ratingbar.RatingBar;

import java.util.HashMap;
import java.util.Map;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.constans.AppConfig;

/**
// * Created by kn on 15/11/14.
 */
public class EvaluateActivity extends BaseActivity {
    private final String Tag = "EvaluateActivity";
    private Button mEvaluateButton;
    private com.hedgehog.ratingbar.RatingBar ratingbar;
    private EditText et_sugusstEditeText;
    private EditText et_auntEvaluate;
    private int orderid;
    private int score;

    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_evaluate);
        mEvaluateButton = (Button) findViewById(R.id.bt_evaluate);
        ratingbar = (RatingBar) findViewById(R.id.ratingbar);
        et_sugusstEditeText = (EditText) findViewById(R.id.et_sugusst);
        et_auntEvaluate = (EditText) findViewById(R.id.et_auntEvaluate);
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        orderid = intent.getIntExtra("orderid", 0);

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mEvaluateButton.setOnClickListener(this);
        ratingbar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(int RatingCount) {
                score = RatingCount;
            }
        });
    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        int vId = v.getId();
        if (vId == R.id.bt_evaluate) {

            if (score > 0){
                commitEvaluate();
            } else {
                Toast.makeText(this, "请麻烦评分", Toast.LENGTH_SHORT).show();
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

    private void commitEvaluate() {
        Map map = new HashMap<>();
        map.put("data[mark_id]", 0);
        map.put("data[task_id]", orderid);
        map.put("data[aid_star]", 0);
        map.put("data[mark_content]", et_sugusstEditeText.getText());
        map.put("data[suggest]", et_auntEvaluate.getText());
        BaseRequest.instance().doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_COMMIT_EVALUATE, map, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codeMsg = responseBean.getMsg();
                if (code == 0) {

                } else {
                    Toast.makeText(EvaluateActivity.this, codeMsg, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void failure(VolleyError error) {

            }
        });
    }
}
