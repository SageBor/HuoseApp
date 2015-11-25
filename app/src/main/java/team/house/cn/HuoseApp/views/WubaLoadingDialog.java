package team.house.cn.HuoseApp.views;

/**
 * @author huxianbo 注意：按钮只有Left, Right两个按钮，如果只显示一个按钮时，显示的是Left按钮，Right按钮会隐藏
 * @date:2014-4-1
 * 
 */

public interface WubaLoadingDialog {

	public void stateToLoading(String loadingText);

	public void stateToNormal();
}
