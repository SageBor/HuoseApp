package team.house.cn.HuoseApp.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import team.house.cn.HuoseApp.Dao.Users;
import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.adapter.CityAdapter;
import team.house.cn.HuoseApp.adapter.ProvinceAdapter;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.bean.CityBean;
import team.house.cn.HuoseApp.bean.ProvinceBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.JSONUtils;

import static android.widget.AdapterView.*;

/**
 * Created by kn on 15/11/14.
 */
public class PerfectInformationActivity extends BaseActivity {
    private final String Tag = "PerfectInformationActivity";
    private Users mUsers;
    private EditText et_username;
    private EditText et_truename;
    private Spinner spinner_sex;
    private Spinner spinner_marry;
    private TextView tv_btbirth;
    private Spinner mProvinceSpinner;
    private Spinner mCityceSpinner;
    private EditText et_email;
    private EditText et_carNum;
    private EditText et_workYears;
    private Spinner spinner_education;
    private Button bt_commit;

    private TextView tv_balance;
    private TextView tv_coupon;
    private TextView tv_username;
    private ImageView iv_aunt;

    private ArrayList<ProvinceBean> provinceBeanArrayList;
    private ArrayList<CityBean> cityBeanArrayList;
    private ProvinceAdapter mProvinceAdapter;
    private CityAdapter mCityAdapter;
    private ProvinceBean mProvinceBean;
    private CityBean mCityBean;

    private ArrayList<CityBean> allcityBeanArrayList;
    private int mYear;
    private int mMonth;
    private int mDay;
    private DatePickerDialog datePickerDialog;

    private String education = "1";
    private String sex = "男";
    private String marry = "已婚";
    private int provinceid;
    private int cityId;
    private String[] educationArray;
    private String[] sexArray;
    private String[] marryArray;
    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_perfect_information);
        et_username = (EditText) findViewById(R.id.et_username);
        et_truename = (EditText) findViewById(R.id.et_truename);
        spinner_sex = (Spinner) findViewById(R.id.spinner_sex);
        spinner_marry = (Spinner) findViewById(R.id.spinner_marry);
        tv_btbirth = (TextView) findViewById(R.id.tv_btbirth);
        mProvinceSpinner = (Spinner) findViewById(R.id.province);
        mCityceSpinner = (Spinner) findViewById(R.id.city);
        et_email = (EditText) findViewById(R.id.et_email);
        et_carNum = (EditText) findViewById(R.id.et_carNum);
        et_workYears = (EditText) findViewById(R.id.et_workYears);
        spinner_education = (Spinner) findViewById(R.id.spinner_education);
        provinceBeanArrayList = new ArrayList<ProvinceBean>();
        allcityBeanArrayList = new ArrayList<CityBean>();
        cityBeanArrayList = new ArrayList<CityBean>();
        mProvinceAdapter = new ProvinceAdapter(provinceBeanArrayList, this);
        mCityAdapter = new CityAdapter(cityBeanArrayList, this);
        mProvinceSpinner.setAdapter(mProvinceAdapter);
        mCityceSpinner.setAdapter(mCityAdapter);

        educationArray = new String[]{"小学", "初中", "高中", "中专/大专", "本科及以上"};
        sexArray = new String[]{"男", "女"};
        marryArray= new String[]{"是", "否"};
        spinner_education.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, educationArray));
        spinner_sex.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, sexArray));
        spinner_marry.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, marryArray));

        bt_commit = (Button) findViewById(R.id.bt_commit);
        tv_balance=(TextView) findViewById(R.id.tv_balance);
        tv_coupon=(TextView) findViewById(R.id.tv_coupon);
        tv_username=(TextView) findViewById(R.id.tv_usermoble);
        iv_aunt = (ImageView) findViewById(R.id.iv_aunt);
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        getCityList();
        Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
        mYear = dateAndTime.get(Calendar.YEAR);
        mMonth = dateAndTime.get(Calendar.MONTH);
        mDay = dateAndTime.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                mDay);
        mUsers = (Users) intent.getSerializableExtra("user");
        if (mUsers != null) {
            showInfo();
        }
    }


    private void showInfo() {
        Picasso.with(this)
                .load(mUsers.getUser_pic())
                .placeholder(R.drawable.user)
                .error(R.drawable.user)
                .tag(this)
                .into(iv_aunt);

        tv_balance.setText("余额：" + mUsers.getBalance() + "元");
        tv_coupon.setText("未结算：" + mUsers.getNopay() + "元");
        tv_username.setText(mUsers.getUsername());
        et_username.setText(mUsers.getUsername());
        et_truename.setText(mUsers.getTruename());
        for (int i = 0; i < educationArray.length; i++) {
            if (mUsers.getEducation() == i + 1) {
                spinner_education.setSelection(i, true);
            }
        }

        for (int i = 0; i < sexArray.length; i++) {
            if (mUsers.getSex().equals(sexArray[i])) {
                spinner_sex.setSelection(i, true);
            }
        }

        for (int i = 0; i < marryArray.length; i++) {
            if (mUsers.getMarry().equals(marryArray[i])) {
                spinner_marry.setSelection(i, true);
            }
        }

        tv_btbirth.setText(mUsers.getBirthday());
        et_email.setText(mUsers.getEmail());
        et_carNum.setText(mUsers.getIdcard());
        et_workYears.setText(mUsers.getWork_exp());

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        tv_btbirth.setOnClickListener(this);
        bt_commit.setOnClickListener(this);
        mProvinceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mProvinceBean = provinceBeanArrayList.get(i);
                provinceid = mProvinceBean.getPro_id();
                cityBeanArrayList.clear();
                int defaultcity = 0;
                for (int j = 0; j < allcityBeanArrayList.size(); j++) {
                    if (allcityBeanArrayList.get(j).getProvinceId() == provinceid) {
                        cityBeanArrayList.add(allcityBeanArrayList.get(j));
                        if (mUsers.getCity() == allcityBeanArrayList.get(j).getCityId()) {
                            defaultcity = j;
                        }
                    }
                }

                mCityAdapter.notifyDataSetChanged();
                mCityceSpinner.setSelection(defaultcity, true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mCityceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                mCityBean = cityBeanArrayList.get(i);
                cityId = mCityBean.getCityId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_education.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        education = "1";
                        break;
                    case 1:
                        education = "2";
                        break;
                    case 2:
                        education = "3";
                        break;
                    case 3:
                        education = "4";
                        break;
                    case 4:
                        education = "5";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_sex.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        sex = "男";
                        break;
                    case 1:
                        sex = "女";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_marry.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        marry = "是";
                        break;
                    case 1:
                        marry = "否";
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        if (v.getId() == R.id.tv_btbirth) {
            datePickerDialog.show();
        }
        if (v.getId() == R.id.bt_commit) {
            commitUserInfo();
        }

    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("完善资料");
        mCityView.setVisibility(View.GONE);
        mLeftView.setVisibility(View.VISIBLE);
        mRightView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }


    private void getCityList() {

        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_CITY_LIST, null, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codemsg = responseBean.getMsg();
                if (code == 0) {
                    String datajson = responseBean.getData();
                    try {
                        JSONObject data = new JSONObject(datajson);
                        if (data != null) {
                            JSONArray provinceJSONArray = JSONUtils.getJSONArray(data, "provinces", null);
                            JSONArray cityJSONArray = JSONUtils.getJSONArray(data, "cities", null);
                            int defaultpro = 0;
                            if (provinceJSONArray != null && provinceJSONArray.length() > 0) {
                                for (int i = 0; i < provinceJSONArray.length(); i++) {
                                    ProvinceBean provinceBean = new ProvinceBean();
                                    JSONObject provinceJSONObject = provinceJSONArray.getJSONObject(i);
                                    provinceBean.setPro_id(JSONUtils.getInt(provinceJSONObject, "pro_id", 0));
                                    provinceBean.setPro_name(JSONUtils.getString(provinceJSONObject, "pro_name", ""));
                                    provinceBeanArrayList.add(provinceBean);
                                    if (mUsers.getProvince() == provinceBean.getPro_id()){
                                        defaultpro = i;
                                    }
                                }
                                mProvinceAdapter.notifyDataSetChanged();
                                mProvinceSpinner.setSelection(defaultpro, true);
                            }

                            if (cityJSONArray != null && cityJSONArray.length() > 0) {
                                for (int i = 0; i < cityJSONArray.length(); i++) {
                                    CityBean cityBean = new CityBean();
                                    JSONObject cityJSONObject = cityJSONArray.getJSONObject(i);
                                    cityBean.setProvinceId(JSONUtils.getInt(cityJSONObject, "pro_id", 0));
                                    cityBean.setCityId(JSONUtils.getInt(cityJSONObject, "city_id", 0));
                                    cityBean.setCityName(JSONUtils.getString(cityJSONObject, "city_name", ""));
                                    allcityBeanArrayList.add(cityBean);
                                }
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {

                }

            }

            @Override
            public void failure(VolleyError error) {

            }
        });
    }


    private void commitUserInfo() {

        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_UPDATE_USERINFO, getParaments(), new BaseResponse() {

            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codemsg = responseBean.getMsg();
                if (code == 0) {
                    Toast.makeText(PerfectInformationActivity.this, codemsg, Toast.LENGTH_SHORT).show();
                    /*String datajson = responseBean.getData();
                    try {
                        JSONObject data = new JSONObject(datajson);
                        if (data != null) {
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/
                } else {
                    Toast.makeText(PerfectInformationActivity.this, codemsg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(VolleyError error) {
                Toast.makeText(PerfectInformationActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private Map getParaments() {
        Map map = new HashMap<>();
        String username = et_username.getText().toString();
        String truename = et_truename.getText().toString();
        String birthday = tv_btbirth.getText().toString();
        String email = et_email.getText().toString();
        String carNum = et_carNum.getText().toString();
        String workyears = et_workYears.getText().toString();
        boolean flag = true;
        if (TextUtils.isEmpty(username)){
            flag = false;
        }
        if (TextUtils.isEmpty(truename)){
            flag = false;
        }
        if (TextUtils.isEmpty(birthday)){
            flag = false;
        }
        if (TextUtils.isEmpty(email)){
            flag = false;
        }
        if (TextUtils.isEmpty(carNum)){
            flag = false;
        }
        if (TextUtils.isEmpty(workyears)){
            flag = false;
        }
        map.put("data[uid]", mUsers.getUid());

        map.put("data[username]", username);

        map.put("data[truename]", truename);

        map.put("data[sex]", sex);

        map.put("data[marry]", marry);

        map.put("data[mobile]", mUsers.getMobile());

        map.put("data[birthday]", birthday);

        map.put("data[province]", provinceid);

        map.put("data[city]", cityId);

        map.put("data[email]", email);

        map.put("data[is_perfect]", flag ? 1 : 0);

        map.put("data[idcard]", carNum);

        map.put("data[work_exp]", workyears);

        map.put("data[educational]", education);

        return map;
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            mYear = year;
            String mm;
            String dd;
            mMonth = monthOfYear + 1;
            if (monthOfYear < 8) {
                mm = "0" + mMonth;
            } else {
                mm = String.valueOf(mMonth);
            }
            if (dayOfMonth <= 9) {
                mDay = dayOfMonth;
                dd = "0" + mDay;
            } else {
                mDay = dayOfMonth;
                dd = String.valueOf(mDay);
            }
            mDay = dayOfMonth;
            tv_btbirth.setText(String.valueOf(mYear) + "-" + mm + "-" + dd);
        }
    };
}
