package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kenan on 15/11/6.
 */
public class ProvinceBean implements Serializable{
    private int pro_id;
    private String pro_name;

    public ProvinceBean(int pro_id, String pro_name) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
    }

    public ProvinceBean() {
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }
}
