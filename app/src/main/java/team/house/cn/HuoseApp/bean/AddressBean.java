package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kenan on 15/11/8.
 */
public class AddressBean implements Serializable {
    private int mAddlesId;
    private int mProvinceId;
    private int mCityid;
    private String mAddress;
    private String mAddressAll;
    private boolean iSDefault;

    public AddressBean(int addressId, int provinceId, int cityId, String address, String addressAll, boolean is_default) {
        mAddlesId = addressId;
        mProvinceId = provinceId;
        mCityid = cityId;
        mAddress = address;
        mAddressAll = addressAll;
        iSDefault = is_default;
    }

    public AddressBean() {
    }

    public int getmAddlesId() {
        return mAddlesId;
    }

    public void setmAddlesId(int mAddlesId) {
        this.mAddlesId = mAddlesId;
    }

    public int getmProvinceId() {
        return mProvinceId;
    }

    public void setmProvinceId(int mProvinceId) {
        this.mProvinceId = mProvinceId;
    }

    public int getmCityid() {
        return mCityid;
    }

    public void setmCityid(int mCityid) {
        this.mCityid = mCityid;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmAddressAll() {
        return mAddressAll;
    }

    public void setmAddressAll(String mAddressAll) {
        this.mAddressAll = mAddressAll;
    }

    public boolean iSDefault() {
        return iSDefault;
    }

    public void setiSDefault(boolean iSDefault) {
        this.iSDefault = iSDefault;
    }
}
