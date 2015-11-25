package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import team.house.cn.HuoseApp.adapter.AddressAdapter;
import team.house.cn.HuoseApp.adapter.CityAdapter;
import team.house.cn.HuoseApp.adapter.ProvinceAdapter;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.bean.AddressBean;
import team.house.cn.HuoseApp.bean.CityBean;
import team.house.cn.HuoseApp.bean.ProvinceBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.JSONUtils;
import team.house.cn.HuoseApp.views.RequestLoadingWeb;

/**
 * Created by kenan on 15/11/8.
 */
public class ChooseAddressActivity extends BaseActivity {
    public final String Tag =  "ChooseAddressActivity";
    private ListView mAddressListview;
    private RelativeLayout mAddAddressLinearLayout;
    private TextView mAddAddressTextView;
    private EditText mAddAddressEditText;
    private Button mAddAddressButton;
    private  List<AddressBean> mAddressList;
    private AddressAdapter mAddressAdapter;
    private Spinner mProvinceSpinner;
    private Spinner mCityceSpinner;
    private ProvinceAdapter mProvinceAdapter;
    private CityAdapter mCityAdapter;
    private ArrayList<ProvinceBean> provinceBeanArrayList;
    private ArrayList<CityBean> cityBeanArrayList;

    private ArrayList<CityBean> allcityBeanArrayList;
    private ProvinceBean mProvinceBean;
    private CityBean mCityBean;

    private RequestLoadingWeb mRequestLoadingWeb;



    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_addresslist);
        mAddAddressLinearLayout = (RelativeLayout) findViewById(R.id.rl_addServiceAddress);
        mAddAddressTextView = (TextView) findViewById(R.id.tv_addServiceAddress);
        mAddAddressEditText = (EditText) findViewById(R.id.et_addServiceAddress);
        mAddAddressButton = (Button) findViewById(R.id.bt_addAddress);
        mAddressListview = (ListView) findViewById(R.id.lv_address);
        mAddressList = new ArrayList<AddressBean>();
        mAddressAdapter = new AddressAdapter(mAddressList, this);
        mAddressListview.setAdapter(mAddressAdapter);
        mProvinceSpinner = (Spinner) findViewById(R.id.province);
        mCityceSpinner = (Spinner) findViewById(R.id.city);
        provinceBeanArrayList = new ArrayList<ProvinceBean>();
        allcityBeanArrayList = new ArrayList<CityBean>();
        cityBeanArrayList = new ArrayList<CityBean>();
        mProvinceAdapter = new ProvinceAdapter(provinceBeanArrayList, this);
        mCityAdapter = new CityAdapter(cityBeanArrayList, this);
        mProvinceSpinner.setAdapter(mProvinceAdapter);
        mCityceSpinner.setAdapter(mCityAdapter);
        mRequestLoadingWeb = new RequestLoadingWeb(getWindow());
        mRequestLoadingWeb.setAgainListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRequestLoadingWeb.getStatus() == RequestLoadingWeb.STATUS_ERROR) {
                    getListAddressFromService();
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        getCityList();
        getListAddressFromService();
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("选择地址");
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mAddAddressTextView.setOnClickListener(this);
        mAddAddressButton.setOnClickListener(this);
        mAddressListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AddressBean addressBean = (AddressBean) mAddressList.get(i);
                Intent intent = new Intent();
                intent.putExtra("address", addressBean);
                intent.putExtra("address_info", addressBean.getmAddressAll());
                setResult(RESULT_OK, intent);
                finish();


            }
        });
//        mAddressListview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                AddressBean addressBean = (AddressBean) mAddressList.get(position);
//                Intent intent = new Intent();
//                intent.putExtra("address_id", addressBean.getmAddlesId());
//                intent.putExtra("address_info", addressBean.getmAddressAll());
//                setResult(RESULT_OK, intent);
//                finish();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        mProvinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mProvinceBean = provinceBeanArrayList.get(i);
                int provinceid = mProvinceBean.getPro_id();
                cityBeanArrayList.clear();
                for (int j = 0; j < allcityBeanArrayList.size(); j++) {
                    if (allcityBeanArrayList.get(j).getProvinceId() == provinceid) {
                        cityBeanArrayList.add(allcityBeanArrayList.get(j));
                    }
                }
                mCityAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mCityceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mCityBean = cityBeanArrayList.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
        int viewId = v.getId();
        if (viewId == R.id.tv_addServiceAddress) {
            mAddAddressLinearLayout.setVisibility(View.VISIBLE);
        }
        if (viewId == R.id.bt_addAddress) {
            String addressinfo = mAddAddressEditText.getText().toString();
            if (TextUtils.isEmpty(addressinfo)) {

            } else {
                if (mProvinceBean != null && mCityBean != null) {
                    commitAddressInfo(addressinfo);
                } else {
                    Toast.makeText(this, "请选择省市信息", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private void getListAddressFromService(){
        mRequestLoadingWeb.statuesToInLoading();
        Map paramMap = new HashMap<>();
        paramMap.put("uid" , mUserBean.getUid());
        BaseRequest.instance(this).doRequest(Tag,Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_ADDRESS, paramMap, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codemsg = responseBean.getMsg();
                mRequestLoadingWeb.statuesToNormal();
                if(code == 0){
                    String datajson = responseBean.getData();
                    try {
                        JSONArray data = new JSONArray(datajson);
                        if (data != null && data.length() > 0)  {
                            mAddressList.clear();
                            for (int i = 0; i < data.length(); i++) {

                                JSONObject addressjson = data.getJSONObject(i);
                                int addressId = addressjson.getInt("id");
                                int provinceId = addressjson.getInt("province");
                                int cityId = addressjson.getInt("city");
                                String address  = addressjson.getString("address");
                                String addressAll = addressjson.getString("address_all");
                                boolean is_default = addressjson.getBoolean("is_default");
                                AddressBean addressBean = new AddressBean(addressId, provinceId, cityId, address, addressAll, is_default);
                                mAddressList.add(addressBean);

                            }
                            mAddressAdapter.addItems(mAddressList);
                            mAddressAdapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        mRequestLoadingWeb.statuesToError();
                        e.printStackTrace();
                    }
                } else {
                    mRequestLoadingWeb.statuesToError();
                }

            }

            @Override
            public void failure(VolleyError error) {

                mRequestLoadingWeb.statuesToError();
            }
        });
    }

    private void commitAddressInfo(String addressinfo) {
        Map paramMap = new HashMap<>();
        paramMap.put("data[uid]" , mUserBean.getUid());
        paramMap.put("data[province]" , mProvinceBean.getPro_id());
        paramMap.put("data[city]" , mCityBean.getCityId());
        paramMap.put("data[address]" , addressinfo);
        paramMap.put("data[is_default]" , 0);
        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_ADDADDRESS, paramMap, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codemsg = responseBean.getMsg();
                if(code == 0){
//                    String datajson = responseBean.getData();
//                    try {
//                        JSONArray data = new JSONArray(datajson);
//                        if (data != null && data.length() > 0)  {
//                            for (int i = 0; i < data.length(); i++) {
//                                JSONObject addressjson = data.getJSONObject(i);
//                                int addressId = addressjson.getInt("id");
//                                int provinceId = addressjson.getInt("province");
//                                int cityId = addressjson.getInt("city");
//                                String address  = addressjson.getString("address");
//                                String addressAll = addressjson.getString("address_all");
//                                boolean is_default = addressjson.getBoolean("is_default");
//                                AddressBean addressBean = new AddressBean(addressId, provinceId, cityId, address, addressAll, is_default);
//                                mAddressAdapter.addItem(addressBean);
//
//                            }
//                            mAddressAdapter.notifyDataSetChanged();
//                        }

//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                    getListAddressFromService();
                } else {

                }

            }

            @Override
            public void failure(VolleyError error) {

            }
        });
    }

    private void getCityList () {

        BaseRequest.instance(this).doRequest(Tag, Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_CITY_LIST, null, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codemsg = responseBean.getMsg();
                if(code == 0){
                    String datajson = responseBean.getData();
                    try {
                        JSONObject data = new JSONObject(datajson);
                        if (data != null)  {
                            JSONArray provinceJSONArray = JSONUtils.getJSONArray(data, "provinces" , null);
                            JSONArray cityJSONArray = JSONUtils.getJSONArray(data, "cities", null);
                            if (provinceJSONArray != null && provinceJSONArray.length() > 0) {
                                for (int i = 0; i < provinceJSONArray.length(); i++) {
                                    ProvinceBean provinceBean = new ProvinceBean();
                                    JSONObject provinceJSONObject = provinceJSONArray.getJSONObject(i);
                                    provinceBean.setPro_id(JSONUtils.getInt(provinceJSONObject, "pro_id", 0));
                                    provinceBean.setPro_name(JSONUtils.getString(provinceJSONObject, "pro_name", ""));
                                    provinceBeanArrayList.add(provinceBean);
                                }
                                mProvinceAdapter.notifyDataSetChanged();
                            }

                            if (cityJSONArray != null && cityJSONArray.length()> 0) {
                                for (int i = 0; i < cityJSONArray.length(); i++){
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
}
