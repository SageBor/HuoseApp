package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kn on 15/11/12.
 */
public class AuntDetailBean extends AuntBean implements Serializable{

    private String idcard;// 身份证号码
    private int auth_info; //验证标示 结果1：认证；2：未认证
    private String day;
    private String hour;
    private boolean is_booking; //是否排版

    public AuntDetailBean(int uid, String truename, String mobile, int seller_good_num, int take_num, String skill_names, String age, String hometown, String user_pic, String idcard, int auth_info, String day, String hour, boolean is_booking) {
        super(uid, truename, mobile, seller_good_num, take_num, skill_names, age, hometown, user_pic);
        this.idcard = idcard;
        this.auth_info = auth_info;
        this.day = day;
        this.hour = hour;
        this.is_booking = is_booking;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public int getAuth_info() {
        return auth_info;
    }

    public void setAuth_info(int auth_info) {
        this.auth_info = auth_info;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public boolean is_booking() {
        return is_booking;
    }

    public void setIs_booking(boolean is_booking) {
        this.is_booking = is_booking;
    }
}
