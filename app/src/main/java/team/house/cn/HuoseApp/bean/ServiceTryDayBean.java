package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kn on 15/11/12.
 * 试用天数bean
 */
public class ServiceTryDayBean implements Serializable {
    private int try_days;
    private String try_days_name;

    public ServiceTryDayBean(int try_days, String try_days_name) {
        this.try_days = try_days;
        this.try_days_name = try_days_name;
    }

    public int getTry_days() {
        return try_days;
    }

    public void setTry_days(int try_days) {
        this.try_days = try_days;
    }

    public String getTry_days_name() {
        return try_days_name;
    }

    public void setTry_days_name(String try_days_name) {
        this.try_days_name = try_days_name;
    }
}
