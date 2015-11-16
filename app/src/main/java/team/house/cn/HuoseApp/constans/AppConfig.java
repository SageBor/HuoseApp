package team.house.cn.HuoseApp.constans;

/**
 * Created by lenovo on 2015/11/4.
 */
public  class  AppConfig {

    // 域名
    public static final String WebHost = "http://housekeepingo2o.cloudapp.net/index.php?";
    public class  Urls {
        // 定位城市信息
        public static final String URL_GET_POSSTION = "do=app&view=get_position";
        // 验证码
        public static final String URL_GET_CODE = "do=app&view=get_verification_code";
        // 用户注册
        public static final String URL_REGISTER = "do=app&view=regist";
        // 用户登陆
        public static final String URL_LOGIN = "do=app&view=login";
        // 城市列表
        public static final String URL_CITY_LIST = "do=app&view=get_dq_list";
        // 地址列表
        public static final String URL_GET_ADDRESS = "do=app&view=get_address";
        // 添加地址
        public static final String URL_GET_ADDADDRESS = "do=app&view=add_address";
        // 预约服务页面配置参数
        public static final String URL_GET_CONFIG = "do=app&view=get_task_config";
        // 取得时间选择列表
        public static final String URL_GET_HOURE = "do=app&view=get_select_hours";
        // 取得试用天数列表
        public static final String URL_GET_TRYDAYS = "do=app&view=get_try_days";
        // 获取雇佣时间列表 月
        public static final String URL_GET_MONTH = "do=app&view=get_employment_month";
        // 阿姨列表
        public static final String URL_GET_AUNTLIST = "do=app&view=get_ayi_list";
        // 阿姨详情
        public static final String URL_GET_AUNTDETAIL = "do=app&view=get_ayi_detail";
        // 当前订单
        public static final String URL_GET_CURRENT_ORDER = "do=app&view=get_task_current_list";
        // 预约服务
        public static final String URL_COMMIT_ORDER = "do=app&view=add_task";


    }
    // 本地百度定位城市在Share中存储key
    public static final String Preference_LocaCityName = "locationCtityName";
    // 本地定位城市信息在Share中存储key
    public static final String Preference_RealCityNameFromService = "realCtityfromservice" ;
    // 选择城市信息在Share中存储key
    public static final String Preference_ChooseCityNameFromService = "chooseCtityfromservice" ;
    //

    public static final String AppRootDir = "huoseapp";//手机根目录名称

}
