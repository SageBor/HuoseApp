package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kn on 15/11/12.
 * 保洁用品
 */
public class ServiceToolsBean implements Serializable{
    private int supplies_id;
    private String supplies_name;
    private int indus_id;

    public int getSupplies_id() {
        return supplies_id;
    }

    public void setSupplies_id(int supplies_id) {
        this.supplies_id = supplies_id;
    }

    public String getSupplies_name() {
        return supplies_name;
    }

    public void setSupplies_name(String supplies_name) {
        this.supplies_name = supplies_name;
    }

    public int getIndus_id() {
        return indus_id;
    }

    public void setIndus_id(int indus_id) {
        this.indus_id = indus_id;
    }

    public ServiceToolsBean(int supplies_id, String supplies_name, int indus_id) {

        this.supplies_id = supplies_id;
        this.supplies_name = supplies_name;
        this.indus_id = indus_id;
    }
}
