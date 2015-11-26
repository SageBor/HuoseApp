package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kn on 15/11/12.
 * 服务模式bean
 */
public class ServiceModelBean implements Serializable {
    private  int employment_typ;
    private String employment_typ_name;
    private int indus_id;
    private boolean isChecked = false;

    public ServiceModelBean() {

    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public ServiceModelBean(int employment_typ, String employment_typ_name, int indus_id ) {
        this.employment_typ = employment_typ;
        this.employment_typ_name = employment_typ_name;
        this.indus_id = indus_id;
    }

    public int getIndus_id() {
        return indus_id;
    }

    public void setIndus_id(int indus_id) {
        this.indus_id = indus_id;
    }

    public int getEmployment_typ() {
        return employment_typ;
    }

    public void setEmployment_typ(int employment_typ) {
        this.employment_typ = employment_typ;
    }

    public String getEmployment_typ_name() {
        return employment_typ_name;
    }

    public void setEmployment_typ_name(String employment_typ_name) {
        this.employment_typ_name = employment_typ_name;
    }
}
