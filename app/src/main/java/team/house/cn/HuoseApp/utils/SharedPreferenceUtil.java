package team.house.cn.HuoseApp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
/**
 * @author kenan
 */
public class SharedPreferenceUtil {
    private static SharedPreferenceUtil instance;
    public static final String  ISFIRST = "isFirst";
    private SharedPreferenceUtil() {
        
    }
    public static SharedPreferenceUtil getInstance() {
        if (null == instance) {
            instance = new SharedPreferenceUtil();
        }
        return instance;
    }
    public SharedPreferences getSharedPreference(Context context){
        SharedPreferences settings=PreferenceManager.getDefaultSharedPreferences(context);
        return settings;
    }
    
}
