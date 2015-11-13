package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kn on 15/11/12.
 * 阿姨bean
 */
public class AuntBean implements Serializable {
    private int uid;
    private String truename;
    private String mobile;
    private int seller_good_num;// 评价次数
    private int take_num; // 服务次数
    private String skill_names;// 技能
    private String  age;
    private String hometown;// 出生地
    private String user_pic;

    public AuntBean(int uid, String truename, String mobile, int seller_good_num, int take_num, String skill_names, String age, String hometown, String user_pic) {
        this.uid = uid;
        this.truename = truename;
        this.mobile = mobile;
        this.seller_good_num = seller_good_num;
        this.take_num = take_num;
        this.skill_names = skill_names;
        this.age = age;
        this.hometown = hometown;
        this.user_pic = user_pic;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getSeller_good_num() {
        return seller_good_num;
    }

    public void setSeller_good_num(int seller_good_num) {
        this.seller_good_num = seller_good_num;
    }

    public int getTake_num() {
        return take_num;
    }

    public void setTake_num(int take_num) {
        this.take_num = take_num;
    }

    public String getSkill_names() {
        return skill_names;
    }

    public void setSkill_names(String skill_names) {
        this.skill_names = skill_names;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }
}