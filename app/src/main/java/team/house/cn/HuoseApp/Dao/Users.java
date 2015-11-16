package team.house.cn.HuoseApp.Dao;

/**
 * Created by kn on 15/11/13.
 */
public class Users {

    private int uid;
    private String username;
    private String truename = "zhangdan";
    private String sex;
    private String marry;
    private String mobile;
    private String birthday;
    private int province;
    private String province_nm;
    private int city;
    private String city_nm;
    private int area;
    private String area_nm;
    private String email;
    private String balance;
    private String user_pic;
    private boolean is_perfec;
    private String addresses;


    public Users() {
    }

    public long getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public boolean is_perfec() {
        return is_perfec;
    }

    public void setIs_perfec(boolean is_perfec) {
        this.is_perfec = is_perfec;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }



    public String getProvince_nm() {
        return province_nm;
    }

    public void setProvince_nm(String province_nm) {
        this.province_nm = province_nm;
    }



    public String getCity_nm() {
        return city_nm;
    }

    public void setCity_nm(String city_nm) {
        this.city_nm = city_nm;
    }


    public String getArea_nm() {
        return area_nm;
    }

    public void setArea_nm(String area_nm) {
        this.area_nm = area_nm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }




    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }
}