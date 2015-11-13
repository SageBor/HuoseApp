package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kn on 15/11/12.
 */
public class HourBean implements Serializable{
    private int hour;
    private String hour_name;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getHour_name() {
        return hour_name;
    }

    public void setHour_name(String hour_name) {
        this.hour_name = hour_name;
    }

    public HourBean(int hour, String hour_name) {

        this.hour = hour;
        this.hour_name = hour_name;
    }
}
