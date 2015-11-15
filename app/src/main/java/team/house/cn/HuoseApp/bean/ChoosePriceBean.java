package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kn on 15/11/12.
 * 自选价格bean
 */
public class ChoosePriceBean implements Serializable {
    private int price;
    private String price_name;
    private int indus_id;

    public ChoosePriceBean() {

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPrice_name() {
        return price_name;
    }

    public void setPrice_name(String price_name) {
        this.price_name = price_name;
    }

    public int getIndus_id() {
        return indus_id;
    }

    public void setIndus_id(int indus_id) {
        this.indus_id = indus_id;
    }

    public ChoosePriceBean(int price, String price_name, int indus_id) {

        this.price = price;
        this.price_name = price_name;
        this.indus_id = indus_id;
    }
}
