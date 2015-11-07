package team.house.cn.HuoseApp.utils;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import team.house.cn.HuoseApp.application.HouseApplication;
import team.house.cn.HuoseApp.asytask.BaseRequest;
import team.house.cn.HuoseApp.asytask.BaseResponse;
import team.house.cn.HuoseApp.asytask.ResponseBean;
import team.house.cn.HuoseApp.constans.AppConfig;

/**
 * Created by kenan on 15/11/7.
 */
public class UserUtil {
    /**
     * get code from service
     */
    public static void getCodeFromService() {
        Map paraments = new HashMap<String, String>();
        paraments.put("mobile", "18612310824");
        BaseRequest.instance().doRequest(Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_CODE, paraments, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                if (responseBean != null) {
                    int code = responseBean.getCode();
                    String msg = responseBean.getMsg();
                    JSONObject jsonData = responseBean.getData();
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
                    JSONObject jsonData = responseBean.getData();
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
}
