package team.house.cn.HuoseApp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kn on 15/11/12.
 */
public class  CommitReservationServiceBean implements Serializable{
    private int uid; //用户Id
    private String username; //用户姓名
    private int indus_pid; //服务分类 大类 小时工
    private ServiceContentBean serviceContentBean = new ServiceContentBean();
    private int indus_id; //服务内容 小类
    private ServiceModelBean serviceModelBean = new ServiceModelBean();
    private int employment_typ; // 服务模式
    private List<ServiceWeekBean> serviceWeekBeanList = new ArrayList<ServiceWeekBean>();
    private int week_id; //服务频率 值：星期ID  形式(逗号分隔)： 星期ID,星期ID,星期ID
    private ServiceBseTimeBean serviceBseTimeBean = new ServiceBseTimeBean();
    private ServiceTryDayBean serviceTryDayBean = new ServiceTryDayBean();
    private int try_days; //试用天数
    private String start_time; //开始日期
    private String end_time; //结束日期
    private ServiceEmploymentMonthBean serviceEmploymentMonth = new ServiceEmploymentMonthBean();
    private int employment_month; //雇佣时间
    private ChoosePriceBean choosePriceBean = new ChoosePriceBean();
    private float price; //心里价位

    private AuntBean auntBean = new AuntBean();
    private int employment_uid; //阿姨Id
    private String task_desc; //备注
    private int pay_typ; // 支付方式 1:账户余额支付,  2:支付宝,  3:微信支付    *现在只支持余额支付，其他两个先屏蔽
    private int model_id; //发单模式 "1:平台自动匹配阿姨,  2:发布任务招募,  3:自选阿姨
    //*app和网页端发单模式对应不上，app请全按“自选阿姨”传值，如果没有选择阿姨，后台会自动设置为“发布任务招募”"
    private float task_cash; //订单总价
    private float paied_cash; // 保证金 暂时和订单总价保持一致
    private String mobile; //手机号
    private String truename; //真实姓名
    private List<ServiceToolsBean> serviceToolsBeanList = new ArrayList<ServiceToolsBean>();
    private String supplies_id; //值：保洁用品ID  形式(逗号分隔)： 保洁用品ID,保洁用品ID,保洁用品ID
    private AddressBean addressBean = new AddressBean();
    private int address_id; // 服务地址Id
    private HourBean startHouBean = new HourBean();
    private int start_hour; //开始时刻
    private HourBean endHouBean = new HourBean();
    private int end_hour; //结束时刻



    public CommitReservationServiceBean() {
    }

    public ServiceContentBean getServiceContentBean() {
        return serviceContentBean;
    }

    public void setServiceContentBean(ServiceContentBean serviceContentBean) {
        this.serviceContentBean = serviceContentBean;
    }

    public ServiceModelBean getServiceModelBean() {
        return serviceModelBean;
    }

    public void setServiceModelBean(ServiceModelBean serviceModelBean) {
        this.serviceModelBean = serviceModelBean;
    }

    public List<ServiceWeekBean> getServiceWeekBeanList() {
        return serviceWeekBeanList;
    }

    public void setServiceWeekBeanList(List<ServiceWeekBean> serviceWeekBeanList) {
        this.serviceWeekBeanList = serviceWeekBeanList;
    }

    public ServiceBseTimeBean getServiceBseTimeBean() {
        return serviceBseTimeBean;
    }

    public void setServiceBseTimeBean(ServiceBseTimeBean serviceBseTimeBean) {
        this.serviceBseTimeBean = serviceBseTimeBean;
    }

    public ServiceTryDayBean getServiceTryDayBean() {
        return serviceTryDayBean;
    }

    public void setServiceTryDayBean(ServiceTryDayBean serviceTryDayBean) {
        this.serviceTryDayBean = serviceTryDayBean;
    }

    public ServiceEmploymentMonthBean getServiceEmploymentMonth() {
        return serviceEmploymentMonth;
    }

    public void setServiceEmploymentMonth(ServiceEmploymentMonthBean serviceEmploymentMonth) {
        this.serviceEmploymentMonth = serviceEmploymentMonth;
    }

    public ChoosePriceBean getChoosePriceBean() {
        return choosePriceBean;
    }

    public void setChoosePriceBean(ChoosePriceBean choosePriceBean) {
        this.choosePriceBean = choosePriceBean;
    }

    public AuntBean getAuntBean() {
        return auntBean;
    }

    public void setAuntBean(AuntBean auntBean) {
        this.auntBean = auntBean;
    }

    public List<ServiceToolsBean> getServiceToolsBeanList() {
        return serviceToolsBeanList;
    }

    public void setServiceToolsBeanList(List<ServiceToolsBean> serviceToolsBeanList) {
        this.serviceToolsBeanList = serviceToolsBeanList;
    }

    public AddressBean getAddressBean() {
        return addressBean;
    }

    public void setAddressBean(AddressBean addressBean) {
        this.addressBean = addressBean;
    }

    public HourBean getStartHouBean() {
        return startHouBean;
    }

    public void setStartHouBean(HourBean startHouBean) {
        this.startHouBean = startHouBean;
    }

    public HourBean getEndHouBean() {
        return endHouBean;
    }

    public void setEndHouBean(HourBean endHouBean) {
        this.endHouBean = endHouBean;
    }

    public int getUid() {

        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIndus_pid() {
        return indus_pid;
    }

    public void setIndus_pid(int indus_pid) {
        this.indus_pid = indus_pid;
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

    public int getWeek_id() {
        return week_id;
    }

    public void setWeek_id(int week_id) {
        this.week_id = week_id;
    }

    public int getTry_days() {
        return try_days;
    }

    public void setTry_days(int try_days) {
        this.try_days = try_days;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getEmployment_month() {
        return employment_month;
    }

    public void setEmployment_month(int employment_month) {
        this.employment_month = employment_month;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getEmployment_uid() {
        return employment_uid;
    }

    public void setEmployment_uid(int employment_uid) {
        this.employment_uid = employment_uid;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public int getPay_typ() {
        return pay_typ;
    }

    public void setPay_typ(int pay_typ) {
        this.pay_typ = pay_typ;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public float getTask_cash() {
        return task_cash;
    }

    public void setTask_cash(float task_cash) {
        this.task_cash = task_cash;
    }

    public float getPaied_cash() {
        return paied_cash;
    }

    public void setPaied_cash(float paied_cash) {
        this.paied_cash = paied_cash;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getSupplies_id() {
        return supplies_id;
    }

    public void setSupplies_id(String supplies_id) {
        this.supplies_id = supplies_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(int start_hour) {
        this.start_hour = start_hour;
    }

    public int getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(int end_hour) {
        this.end_hour = end_hour;
    }
}
