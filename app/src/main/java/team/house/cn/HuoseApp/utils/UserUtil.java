package team.house.cn.HuoseApp.utils;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.house.cn.HuoseApp.Dao.Users;
import team.house.cn.HuoseApp.application.HouseApplication;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.bean.AddressBean;
import team.house.cn.HuoseApp.constans.AppConfig;

/**
 * Created by kenan on 15/11/7.
 */
public class UserUtil {
//    /**
//     * get code from service
//     */
//    public static void getCodeFromService() {
//        Map paraments = new HashMap<String, String>();
//        paraments.put("mobile", "18612310824");
//        BaseRequest.instance().doRequest(Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_CODE, paraments, new BaseResponse() {
//            @Override
//            public void successful(ResponseBean responseBean) {
//                if (responseBean != null) {
//                    int code = responseBean.getCode();
//                    String msg = responseBean.getMsg();
//
//                    if (code == 0) {
//                        try {
//                            JSONObject jsonData = new JSONObject(responseBean.getData());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    } else {
////                        Toast.makeText(MainActivity.this, codeMsg, Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            }
//
//            @Override
//            public void failure(VolleyError error) {
//
//            }
//
//        });
//
//    }

    /**
     * register userinfo on service
     */
    public static void registerUserFromService() {
        String cityName = PreferenceUtil.getString(HouseApplication.getHuoYunApplicationContext(), AppConfig.Preference_LocaCityName);
        Map paraments = new HashMap<String, String>();
        paraments.put("city_name", "长春");
        BaseRequest.instance().doRequest(Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_POSSTION, paraments, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                if (responseBean != null) {
                    int code = responseBean.getCode();
                    String msg = responseBean.getMsg();
                    try {
                        JSONObject jsonData = new JSONObject(responseBean.getData());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (code == 0) {

                    } else {
//                        Toast.makeText(MainActivity.this, codeMsg, Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void failure(VolleyError error) {

            }

        });

    }

    public static Users getUserinfoFromSharepreference () {
        String userinfo = PreferenceUtil.getString(HouseApplication.getHuoYunApplicationContext(), "userinfo");
        if (!TextUtils.isEmpty(userinfo)) {
            Users  users = new Users();
            try {
                JSONObject data = new JSONObject(userinfo);
                users.setUid(JSONUtils.getInt(data, "uid", 0));
                users.setUsername(JSONUtils.getString(data, "username", ""));
                users.setTruename(JSONUtils.getString(data, "truename", ""));
                users.setSex(JSONUtils.getString(data, "sex", ""));
                users.setMarry(JSONUtils.getString(data, "marry", ""));
                users.setMobile(JSONUtils.getString(data, "mobile", ""));
                users.setBirthday(JSONUtils.getString(data, "birthday", ""));
                users.setProvince(JSONUtils.getInt(data, "province", 0));
                users.setProvince_nm(JSONUtils.getString(data, "province_nm", ""));
                users.setCity(JSONUtils.getInt(data, "city", 0));
                users.setCity_nm(JSONUtils.getString(data, "city_nm", ""));
                users.setArea(JSONUtils.getInt(data, "area", 0));
                users.setArea_nm(JSONUtils.getString(data, "area_nm", ""));
                users.setEmail(JSONUtils.getString(data, "email", ""));
                users.setBalance(JSONUtils.getString(data, "balance", ""));
                users.setUser_pic(JSONUtils.getString(data, "user_pic", ""));
                users.setIs_perfec(JSONUtils.getBoolean(data, "is_perfect", false));
                return users;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

        }
        return null ;
    }

    public static List<AddressBean> getAddressFromSharepreference() {
        String userinfo = PreferenceUtil.getString(HouseApplication.getHuoYunApplicationContext(), "addressinfo");
        if (!TextUtils.isEmpty(userinfo)){
            try {
                JSONArray addressArray = new JSONArray(userinfo);
                if (addressArray != null && addressArray.length() > 0)
                {
                    List<AddressBean> addressBeanList = new ArrayList<AddressBean>();
                    for (int i = 0; i < addressArray.length(); i++){
                        JSONObject addressJson = addressArray.getJSONObject(i);
                        AddressBean addressBean = new AddressBean();
                        addressBean.setmAddlesId(JSONUtils.getInt(addressJson, "id", 0));
                        addressBean.setmProvinceId(JSONUtils.getInt(addressJson, "province", 0));
                        addressBean.setmCityid(JSONUtils.getInt(addressJson, "city", 0));
                        addressBean.setmAddress(JSONUtils.getString(addressJson, "address", ""));
                        addressBean.setmAddressAll(JSONUtils.getString(addressJson, "address_all", ""));
                        addressBean.setiSDefault(JSONUtils.getBoolean(addressJson, "is_default", false));
                        addressBeanList.add(addressBean);

                    }
                    return addressBeanList;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static int getUseridFromSharepreference() {

        int userinfo = PreferenceUtil.getInt(HouseApplication.getHuoYunApplicationContext(), "userId");
        return userinfo;
    }

    public static void exitLogin() {
        PreferenceUtil.putString(HouseApplication.getHuoYunApplicationContext(), "userinfo", "");
        PreferenceUtil.putInt(HouseApplication.getHuoYunApplicationContext(), "userId", 0);
        PreferenceUtil.putString(HouseApplication.getHuoYunApplicationContext(), "addressinfo", "");
    }

}
