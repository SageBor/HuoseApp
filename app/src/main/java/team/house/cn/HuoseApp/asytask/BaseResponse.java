package team.house.cn.HuoseApp.asytask;

import com.android.volley.VolleyError;

/**
 * Created by kenan on 15/11/6.
 */
public interface BaseResponse {
    public void successful(ResponseBean responseBean);
    public void failure(VolleyError error);
}
