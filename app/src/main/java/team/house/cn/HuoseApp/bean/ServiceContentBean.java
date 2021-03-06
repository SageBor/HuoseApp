package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kenan on 15/11/8.
 * 服务大类
 */
public class ServiceContentBean implements Serializable {
    private int indus_id;
    private String indus_name;

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    private boolean isChecked;

    public ServiceContentBean(int indus_id, String indus_name, boolean isChecked) {
        this.indus_id = indus_id;
        this.indus_name = indus_name;
        this.isChecked = isChecked;
    }

    public ServiceContentBean() {
    }

    public String getIndus_name() {
        return indus_name;
    }

    public void setIndus_name(String indus_name) {
        this.indus_name = indus_name;
    }

    public int getIndus_id() {
        return indus_id;
    }

    public void setIndus_id(int indus_id) {
        this.indus_id = indus_id;
    }
}

