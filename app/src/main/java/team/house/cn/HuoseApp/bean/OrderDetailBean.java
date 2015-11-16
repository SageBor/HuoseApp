package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kn on 15/11/14.
 */
public class OrderDetailBean implements Serializable {
    private int task_id;
    private int uid;
    private String indus_pid;
    private String indus_id;
    private String week_name;
    private String start_time;
    private String end_time;
    private String work_time;
    private String address;
    private String supplies_name;
    private String task_cash;
    private String paied_cash;
    private String truename;
    private String contact;
    private String task_status;
    private String task_status_content;
    private String employment_uid;
    private String employment_truename;
    private String employment_mobile;
    private String aid_star;
    private String mark_content;
    private String suggest;
    private String employment_typ;
    private String try_days;
    private String employment_month;

    public OrderDetailBean(int task_id, String indus_pid, String indus_id, String start_time, String address, String supplies_name, String task_cash, String paied_cash, String truename, String contact, String task_status, String task_status_content) {
        this.task_id = task_id;
        this.indus_pid = indus_pid;
        this.indus_id = indus_id;
        this.start_time = start_time;
        this.address = address;
        this.supplies_name = supplies_name;
        this.task_cash = task_cash;
        this.paied_cash = paied_cash;
        this.truename = truename;
        this.contact = contact;
        this.task_status = task_status;
        this.task_status_content = task_status_content;
    }

    public OrderDetailBean() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getWeek_name() {
        return week_name;
    }

    public void setWeek_name(String week_name) {
        this.week_name = week_name;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getWork_time() {
        return work_time;
    }

    public void setWork_time(String work_time) {
        this.work_time = work_time;
    }

    public String getEmployment_uid() {
        return employment_uid;
    }

    public void setEmployment_uid(String employment_uid) {
        this.employment_uid = employment_uid;
    }

    public String getEmployment_truename() {
        return employment_truename;
    }

    public void setEmployment_truename(String employment_truename) {
        this.employment_truename = employment_truename;
    }

    public String getEmployment_mobile() {
        return employment_mobile;
    }

    public void setEmployment_mobile(String employment_mobile) {
        this.employment_mobile = employment_mobile;
    }

    public String getAid_star() {
        return aid_star;
    }

    public void setAid_star(String aid_star) {
        this.aid_star = aid_star;
    }

    public String getMark_content() {
        return mark_content;
    }

    public void setMark_content(String mark_content) {
        this.mark_content = mark_content;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getEmployment_typ() {
        return employment_typ;
    }

    public void setEmployment_typ(String employment_typ) {
        this.employment_typ = employment_typ;
    }

    public String getTry_days() {
        return try_days;
    }

    public void setTry_days(String try_days) {
        this.try_days = try_days;
    }

    public String getEmployment_month() {
        return employment_month;
    }

    public void setEmployment_month(String employment_month) {
        this.employment_month = employment_month;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getIndus_pid() {
        return indus_pid;
    }

    public void setIndus_pid(String indus_pid) {
        this.indus_pid = indus_pid;
    }

    public String getIndus_id() {
        return indus_id;
    }

    public void setIndus_id(String indus_id) {
        this.indus_id = indus_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSupplies_name() {
        return supplies_name;
    }

    public void setSupplies_name(String supplies_name) {
        this.supplies_name = supplies_name;
    }

    public String getTask_cash() {
        return task_cash;
    }

    public void setTask_cash(String task_cash) {
        this.task_cash = task_cash;
    }

    public String getPaied_cash() {
        return paied_cash;
    }

    public void setPaied_cash(String paied_cash) {
        this.paied_cash = paied_cash;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public String getTask_status_content() {
        return task_status_content;
    }

    public void setTask_status_content(String task_status_content) {
        this.task_status_content = task_status_content;
    }
}
