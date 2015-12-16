package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * WebView 跳转类
 * @author huxianbo

 * @date:2014-3-31
 * 
 */
public class WebBundleBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * bundle 传值的Key;
	 */
	public final static String WEB_SERIALIZABLE_KEY="webkey";
	/**
	 * 跳转后的标题
	 */
	private String activity_title="";
	/**
	 * 需要跳转的url
	 */
	private String web_url="";
	
	/**
	 * 电话号码，不用可留空
	 */
	private String mphone = "";
	
	
	private Boolean showMainTitle = false;

	/**
	 * 从哪个页面跳转进入的
	 */
	private String loginfrom="";
	private boolean encrypt = false;//是否加密请求

     public String getLoginfrom() {
          return loginfrom;
     }

     public void setLoginfrom(String loginfrom) {
          this.loginfrom = loginfrom;
     }

	/**
	 * 
	 * @param title
	 * @param url
	 */
	
	public WebBundleBean(String title, String url) {
		this.activity_title = title;
		this.web_url = url;
	}
	public WebBundleBean(String title, String url, String phone) {
		this.activity_title = title;
		this.web_url = url;
		this.setMphone(phone);
	}
	public String getWeb_url() {
		return web_url;
	}
	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}
	public String getActivity_title() {
		return activity_title;
	}
	public void setActivity_title(String activity_title) {
		this.activity_title = activity_title;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public Boolean getShowMainTitle() {
		return showMainTitle;
	}
	public void setShowMainTitle(Boolean showMainTitle) {
		this.showMainTitle = showMainTitle;
	}

	public boolean isEncrypt() {
		return encrypt;
	}

	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}
}
