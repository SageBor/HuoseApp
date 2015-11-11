package team.house.cn.HuoseApp.bean;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by kenan on 15/11/6.
 */
public class CityBean implements Serializable{
    private int provinceId;
    private int cityId;
    private String cityName;

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public CityBean(int provinceId, int cityId, String cityName) {

        this.provinceId = provinceId;
        this.cityId = cityId;
        this.cityName = cityName;
    }
}
