package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kn on 15/11/14.
 */
public class OrderBean implements Serializable {
    private int task_id;
    private String on_time;
    private String indus_pid;
    private String start_time;
    private String address;
    private String task_status;
    private String task_status_content;

    public OrderBean(int task_id, String on_time, String indus_pid, String start_time, String address, String task_status, String task_status_content) {
        this.task_id = task_id;
        this.on_time = on_time;
        this.indus_pid = indus_pid;
        this.start_time = start_time;
        this.address = address;
        this.task_status = task_status;
        this.task_status_content = task_status_content;
    }

    public OrderBean() {
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getOn_time() {
        return on_time;
    }

    public void setOn_time(String on_time) {
        this.on_time = on_time;
    }

    public String getIndus_pid() {
        return indus_pid;
    }

    public void setIndus_pid(String indus_pid) {
        this.indus_pid = indus_pid;
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
