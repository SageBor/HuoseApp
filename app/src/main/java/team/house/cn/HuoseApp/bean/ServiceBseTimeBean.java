package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kn on 15/11/12.
 * 服务起步时间及起步价
 */
public class ServiceBseTimeBean implements Serializable {
    private int basic_price;
    private int basic_hours;
    private int indus_id;

    public int getBasic_price() {
        return basic_price;
    }

    public void setBasic_price(int basic_price) {
        this.basic_price = basic_price;
    }

    public int getBasic_hours() {
        return basic_hours;
    }

    public void setBasic_hours(int basic_hours) {
        this.basic_hours = basic_hours;
    }

    public int getIndus_id() {
        return indus_id;
    }

    public void setIndus_id(int indus_id) {
        this.indus_id = indus_id;
    }

    public ServiceBseTimeBean(int basic_price, int basic_hours, int indus_id) {

        this.basic_price = basic_price;
        this.basic_hours = basic_hours;
        this.indus_id = indus_id;
    }
}
