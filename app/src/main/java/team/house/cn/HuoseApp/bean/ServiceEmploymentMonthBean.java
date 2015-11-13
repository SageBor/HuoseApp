package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kn on 15/11/12.
 * 雇佣时间bean
 */
public class ServiceEmploymentMonthBean /*implements Serializable*/ {
    private int employment_month;
    private String employment_month_name;

    public ServiceEmploymentMonthBean(int employment_month, String employment_month_name) {
        this.employment_month = employment_month;
        this.employment_month_name = employment_month_name;
    }

    public int getEmployment_month() {
        return employment_month;
    }

    public void setEmployment_month(int employment_month) {
        this.employment_month = employment_month;
    }

    public String getEmployment_month_name() {
        return employment_month_name;
    }

    public void setEmployment_month_name(String employment_month_name) {
        this.employment_month_name = employment_month_name;
    }
}
