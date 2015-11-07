package team.house.cn.HuoseApp.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xiayong
 */
public class ActivityManager {
	private List<Activity> list = new LinkedList<Activity>();
	private static ActivityManager instance = new ActivityManager();

	public static ActivityManager getInstance() {
		return instance;
	}

	private ActivityManager() {
	}

	public void removeActivity(Activity activity) {
		list.remove(activity);
	}

	public void addActivity(Activity activity) {
		list.add(activity);
	}

	public void exist() {
		for(Activity activity:list){
			activity.finish();
		}
		System.exit(0);
	}

	public boolean isListEmpty() {
		return list==null||list.isEmpty();
	}
	public int activitySize(){
		return list==null?0:list.size();
	}
//	public String topStackName(){
//		if(activityStack==null)
//			return null;
//		return activityStack.get(0).getLocalClassName();
//	}
}