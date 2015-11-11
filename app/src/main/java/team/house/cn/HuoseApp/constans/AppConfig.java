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
        public static final String URL_GET_CODE = "index.php?do=app&view=get_verification_code";
        // 用户注册
        public static final String URL_REGUSTER = "index.php?do=app&view=regist";
        // 地址列表
        public static final String URL_GET_ADDRESS = "index.php?do=app&view=get_address";
        // 预约服务页面配置参数借口
        public static final String URL_GET_CONFIG = "index.php?do=app&view=get_task_config";

    }
    // 本地百度定位城市在Share中存储key
    public static final String Preference_LocaCityName = "locationCtityName";
    // 本地定位城市信息在Share中存储key
    public static final String Preference_RealCityNameFromService = "realCtityfromservice" ;
    // 选择城市信息在Share中存储key
    public static final String Preference_ChooseCityNameFromService = "chooseCtityfromservice" ;

}
