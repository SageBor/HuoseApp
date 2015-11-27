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
import team.house.cn.HuoseApp.bean.CityBean;
import team.house.cn.HuoseApp.constans.AppConfig;

/**
 * Created by kenan on 15/11/6.
 */
public class CityUtil {

    /**
     * save city info
     *
     * @param proId
     * @param cityId
     * @param cityName
     * @param key
     */
    public static void saveCity(int proId, int cityId, String cityName, String key) {
        String realCty = proId + "~" + cityId + "~" + cityName;
        PreferenceUtil.putString(HouseApplication.getHuoYunApplicationContext(), key, realCty);
    }

    /**
     * get city info
     *
     * @param key
     * @return
     */
    public static CityBean getCity(String key) {
        CityBean cityBean = null;
        String cityInfo = PreferenceUtil.getString(HouseApplication.getHuoYunApplicationContext(), key);
        if (!TextUtils.isEmpty(cityInfo)) {
            String[] cityArray = cityInfo.split("~");
            if (cityArray != null && cityArray.length == 3) {
                cityBean = new CityBean(Integer.parseInt(cityArray[0]), Integer.parseInt(cityArray[1]), cityArray[2]);
            }
        }
        return cityBean;
    }

    public static String getCityName(String key) {
        CityBean cityInfo = getCity(key);
        if (cityInfo != null) {
            return (String) cityInfo.getCityName();

        }
        return "";
    }

    /**
     * getLocation from service and save
     */
    public static void getLocationFromService(String Tag) {
        String locationname = PreferenceUtil.getString(HouseApplication.getHuoYunApplicationContext(), AppConfig.Preference_LocaCityName);
        Map paraments = new HashMap<String, String>();
        paraments.put("city_name", locationname);
        BaseRequest.instance().doRequest(Tag ,Request.Method.POST, AppConfig.WebHost + AppConfig.Urls.URL_GET_POSSTION, paraments, new BaseResponse() {
            @Override
            public void successful(ResponseBean responseBean) {
                if (responseBean != null) {
                    int code = responseBean.getCode();
                    String msg = responseBean.getMsg();

                    if (code == 0) {
                        try {
                            JSONObject jsonData = new JSONObject(responseBean.getData());
                            int upid = jsonData.getInt("upid");
                            int id = jsonData.getInt("id");
                            String name = jsonData.getString("name");

                            saveCity(upid, id, name, AppConfig.Preference_RealCityNameFromService);

                            String chooseCityName = getCityName(AppConfig.Preference_ChooseCityNameFromService);
                            if (TextUtils.isEmpty(chooseCityName)) {
                                saveCity(upid, id, name, AppConfig.Preference_ChooseCityNameFromService);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
