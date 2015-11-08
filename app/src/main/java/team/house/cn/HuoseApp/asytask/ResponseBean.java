package team.house.cn.HuoseApp.asytask;

import org.json.JSONObject;

/**
 * Created by kenan on 15/11/6.
 */
public class ResponseBean {
    private int code;
    private String msg;
    private String data;

    public ResponseBean(int _code, String _msg, String _data) {
        code = _code;
        msg = _msg;
        data = _data;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
