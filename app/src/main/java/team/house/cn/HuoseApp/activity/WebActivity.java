package team.house.cn.HuoseApp.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.bean.WebBundleBean;
import team.house.cn.HuoseApp.utils.CommonTools;
import team.house.cn.HuoseApp.views.RequestLoadingWeb;

/**
 * 展示浏览器的界面 用于显示广告、推广等
 * 
 * @author huxianbo
 * 
 * @date:2014-3-31
 * 
 */
public class WebActivity extends BaseActivity {

	private WebView urlWeb;
	private RequestLoadingWeb mRequestLoading;// loading
	private String web_url;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_web);

		urlWeb = (WebView) findViewById(R.id.web_openurl);
		WebSettings websettting = urlWeb.getSettings();
		websettting.setJavaScriptEnabled(true);
		websettting.setDefaultTextEncodingName("UTF-8");
		// 接口 用于url调用Nagetive;
//		urlWeb.addJavascriptInterface(this, "nagetive");
		mRequestLoading = new RequestLoadingWeb(getWindow());

	

	}

	@Override
	protected void initTitle() {
		showWebUrl();
		mRightView.setVisibility(View.GONE);
		mLeftView.setVisibility(View.VISIBLE);
		mCityView.setVisibility(View.GONE);

	}

	/**
	 * 通过startActivity传入的Intent中得到标题和链接地址。
	 */
	private void showWebUrl() {
		Intent webIntent = getIntent();
		try {
			WebBundleBean webBean = (WebBundleBean) webIntent.getSerializableExtra(WebBundleBean.WEB_SERIALIZABLE_KEY);
			if (webBean != null) {

//				urlWeb.loadUrl(webBean.getWeb_url());
				web_url = webBean.getWeb_url();
				loadUrl(webBean.isEncrypt());
				if (webBean.getShowMainTitle()) {
					RelativeLayout.LayoutParams mPararm = (RelativeLayout.LayoutParams) mTitleView.getLayoutParams();
					mPararm.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
					mPararm.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
					mTitleView.setLayoutParams(mPararm);
				}else{
					mTitleView.setText(webBean.getActivity_title().length() > 10 ? webBean.getActivity_title().substring(0, 10) + "..." : webBean.getActivity_title());
					mTitleView.setVisibility(View.VISIBLE);
				}
			}
		} catch (Exception e) {
 		}
	}


	/**
	 * url上的按钮点击进行的跳转；请使用nagetive.opennewUrl("新页面","http://www.baidu.com");
	 * 
	 * @edit by liuyi
	 * @param title
	 *            新页签的标题
	 * @param url
	 *            新页签的界面url
	 */
//	private void opennewurl(String title, String url) {
//		Intent intent = new Intent(this, WebActivity.class);
//		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		WebBundleBean webbean = new WebBundleBean(title, url);
//		intent.putExtra(WebBundleBean.WEB_SERIALIZABLE_KEY, webbean);
//		startActivity(intent);
//		CommonTools.acitvityTransition(this, R.anim.view_fade_in, R.anim.view_fade_out);
//	}

	/**
	 * 调用系统浏览器打开网址 请使用nagetive.opennewurlbybrowser("http://www.baidu.com");
	 * 
	 *        需要跳转的界面
	 */
//	private void opennewurlbybrowser(String url) {
//		Intent intent = new Intent();
//		intent.setAction("android.intent.action.VIEW");
//		Uri content_url = Uri.parse(url);
//		intent.setData(content_url);
//		startActivity(intent);
//	}

	private void loadUrl(boolean safely){
		if(!safely){
			urlWeb.loadUrl(web_url);
			return;
		}
		/*Map<Object, Object> paramMap = new HashMap<Object, Object>();
		UserBean user = userHelper.selectUser();
		paramMap.put("user_id", user.getUid());*/
/*		CommonGetTask getWebTask =
				new CommonGetTask(this, null, web_url,
						new CommonTask.ResultCallBack() {

							@Override
							public void ComTaskResult(CommonBean bean) {
								try {
									if (bean.isNull() || bean.getCode() != 0) {
										return;
									}
									urlWeb.loadDataWithBaseURL("", bean.getsHttpResult(), "text/html", "UTF-8",
											null);
								} catch (Exception e) {
									e.printStackTrace();
								}

							}
						});
		getWebTask.execute();*/
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			finish();
			CommonTools.acitvityTransition(WebActivity.this, R.anim.view_fade_in, R.anim.view_fade_out);
		}
		return super.onKeyDown(keyCode, event);
	}



	

}
