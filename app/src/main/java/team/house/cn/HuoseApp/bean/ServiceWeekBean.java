package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * 服务频率列表
 */
public class ServiceWeekBean implements Serializable {
    private int week_id;
    private String week_name;
    private int indus_id;

    public String getWeek_name() {
        return week_name;
    }

    public void setWeek_name(String week_name) {
        this.week_name = week_name;
    }

    public ServiceWeekBean(int week_id, String week_name, int indus_id) {

        this.week_id = week_id;
        this.week_name = week_name;
        this.indus_id = indus_id;
    }

    public int getWeek_id() {
        return week_id;
    }

    public void setWeek_id(int week_id) {
        this.week_id = week_id;
    }

    public int getIndus_id() {
        return indus_id;
    }

    public void setIndus_id(int indus_id) {
        this.indus_id = indus_id;
    }
}
