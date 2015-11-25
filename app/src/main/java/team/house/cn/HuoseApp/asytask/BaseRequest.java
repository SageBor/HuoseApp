package team.house.cn.HuoseApp.asytask;

import android.app.Activity;
import android.os.SystemClock;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import team.house.cn.HuoseApp.application.HouseApplication;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.CommonTools;
import team.house.cn.HuoseApp.utils.PreferenceUtil;

//import im.amomo.volley.OkRequest;

/**
 * Created by kenan on 15/11/5.
 */
public class BaseRequest {
    private long mRequestBeginTime = 0;
    private static BaseRequest mBaseRequest = null;
    private JsonObjectRequest jsonRequet;
    private static Activity mActivity;

    public static BaseRequest instance(Activity _activity) {

        mActivity= _activity;
        if (mBaseRequest == null) {
            mBaseRequest = new BaseRequest();
        }
        return mBaseRequest;
    }
    public static BaseRequest instance() {

        if (mBaseRequest == null) {
            mBaseRequest = new BaseRequest();
        }
        return mBaseRequest;
    }

    public void doRequest(String tag, int method, String url, Map param, final BaseResponse baseResponse) {

        String params = getParames(param);
        if (!TextUtils.isEmpty(params)) {
            url = url + params;
        }

        if (mActivity != null) {
            CommonTools.createRequestLoadingDialog(mActivity);
        }
        jsonRequet = new JsonObjectRequest(method, url, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject jsonObject) {
                CommonTools.dissmissLoadingDialog();
                try {

                    int code = jsonObject.getInt("rst_code");
                    String codeMsg = jsonObject.getString("rst_msg");
                    String data = jsonObject.getString("data");
                    ResponseBean responseBean = new ResponseBean(code, codeMsg, data);
                    baseResponse.successful(responseBean);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        CommonTools.dissmissLoadingDialog();
                        baseResponse.failure(error);
                    }
                });
        jsonRequet.setTag(tag);
        jsonRequet.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 2, 1));
        HouseApplication.mQueue.add(jsonRequet);


    }

    public void cancelRequst(Object object) {
        HouseApplication.mQueue.cancelAll(object);
    }

    //解析返回的数据
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            byte[] data = response.data;
            String json = new String(data, HttpHeaderParser.parseCharset(response.headers));
            if (VolleyLog.DEBUG) {
                VolleyLog.d("response:%s", json);
            }
            return Response.success(new JSONObject(json), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }
    }

    private String getParames(Map<Object, Object> paramMap) {
        StringBuffer paramsb = null;
        if (paramMap != null && paramMap.size() > 0) {
            paramsb = new StringBuffer();
            for (Map.Entry<Object, Object> entry : paramMap.entrySet()) {
                try {
                    paramsb.append("&" + entry.getKey().toString() + "=" + URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            return paramsb.toString();


        }
        return "";
    }
//    @Override
//    public void addMarker(String tag) {
//        super.addMarker(tag);
//        if (mRequestBeginTime == 0) {
//            mRequestBeginTime = SystemClock.elapsedRealtime();
//        }
//    }
//
//
//    protected void deliverResponse(JSONObject jsonObject) {
//        super.deliverResponse(jsonObject);
//        //请求用掉的总时间
//        long requestTime = SystemClock.elapsedRealtime() - mRequestBeginTime;
//    }
//
//    @Override
//    //可以自己在这里完成错误的自定义处理
//    public void deliverError(VolleyError error) {
//        super.deliverError(error);
//        //错误发生时候response的数据 byte[]
//        //error.networkResponse.data;
//    }

}
