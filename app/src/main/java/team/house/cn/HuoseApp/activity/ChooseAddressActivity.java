package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.bean.AddressBean;
import team.house.cn.HuoseApp.constans.AppConfig;

/**
 * Created by kenan on 15/11/8.
 */
public class ChooseAddressActivity extends BaseActivity {
    private ListView mAddressListview;
    private LinearLayout mAddAddressLinearLayout;
    private TextView mAddAddressTextView;
    private EditText mAddAddressEditText;
    private Button mAddAddressButton;
    private  List<AddressBean> mAddressList;
    private AddressAdapter mAddressAdapter;



    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_addresslist);
        mAddAddressLinearLayout = (LinearLayout) findViewById(R.id.ll_addServiceAddress);
        mAddAddressTextView = (TextView) findViewById(R.id.tv_addServiceAddress);
        mAddAddressEditText = (EditText) findViewById(R.id.et_addServiceAddress);
        mAddAddressButton = (Button) findViewById(R.id.bt_addAddress);
        mAddressListview = (ListView) findViewById(R.id.lv_address);
        mAddressList = new ArrayList<AddressBean>();
        mAddressAdapter = new AddressAdapter(mAddressList, this);
        mAddressListview.setAdapter(mAddressAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
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
                AddressBean addressBean = (AddressBean) adapterView.getItemAtPosition(i);
                Intent intent = new Intent();
                intent.putExtra("address_id", addressBean.getmAddlesId());
                intent.putExtra("address_info", addressBean.getmAddressAll());
                setResult(RESULT_OK, intent);
                finish();


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
                commitAddressInfo(addressinfo);
            }
        }
    }

    private void getListAddressFromService(){
        Map paramMap = new HashMap<>();
        paramMap.put("uid" , 0);
        BaseRequest.instance().doRequest(Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_ADDRESS, paramMap, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codemsg = responseBean.getMsg();
                if(code == 0){
                    String datajson = responseBean.getData();
                    try {
                        JSONArray data = new JSONArray(datajson);
                        if (data != null && data.length() > 0)  {

                            for (int i = 0; i < data.length(); i++) {

                                JSONObject addressjson = data.getJSONObject(i);
                                int addressId = addressjson.getInt("id");
                                int provinceId = addressjson.getInt("province");
                                int cityId = addressjson.getInt("city");
                                String address  = addressjson.getString("address");
                                String addressAll = addressjson.getString("address_all");
                                boolean is_default = addressjson.getBoolean("is_default");
                                AddressBean addressBean = new AddressBean(addressId, provinceId, cityId, address, addressAll, is_default);
                                mAddressAdapter.addItem(addressBean);

                            }
                            mAddressAdapter.notifyDataSetChanged();
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

    private void commitAddressInfo(String addressinfo) {
        Map paramMap = new HashMap<>();
        paramMap.put("uid" , 0);
        paramMap.put("province" , 0);
        paramMap.put("city" , 0);
        paramMap.put("address" , addressinfo);
        paramMap.put("is_default" , 0);
        BaseRequest.instance().doRequest(Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_ADDRESS, paramMap, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                int code = responseBean.getCode();
                String codemsg = responseBean.getMsg();
                if(code == 0){
                    String datajson = responseBean.getData();
                    try {
                        JSONArray data = new JSONArray(datajson);
                        if (data != null && data.length() > 0)  {

                            for (int i = 0; i < data.length(); i++) {

                                JSONObject addressjson = data.getJSONObject(i);
                                int addressId = addressjson.getInt("id");
                                int provinceId = addressjson.getInt("province");
                                int cityId = addressjson.getInt("city");
                                String address  = addressjson.getString("address");
                                String addressAll = addressjson.getString("address_all");
                                boolean is_default = addressjson.getBoolean("is_default");
                                AddressBean addressBean = new AddressBean(addressId, provinceId, cityId, address, addressAll, is_default);
                                mAddressAdapter.addItem(addressBean);

                            }
                            mAddressAdapter.notifyDataSetChanged();
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
