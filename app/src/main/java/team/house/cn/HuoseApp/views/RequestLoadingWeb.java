package team.house.cn.HuoseApp.views;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import team.house.cn.HuoseApp.R;


public class RequestLoadingWeb extends IRequestLoading{
	
	private int mStatus = STATUS_NORMAL;
	public static final int STATUS_NORMAL = 0;
	public static final int STATUS_IN_LOADING = 1;
	public static final int STATUS_ERROR = 2;
	public static final int STATUS_SUCCESS = 3;
	
	View mLoadingView;
	View mLoadingRelativeLayout;
	View mRequestInLoadingView;
	TextView mRequestLoadingProgressText;
	
	View mRequestError;
	Button mCancel;
	Button mAgain;
	Button btn_ErrAgin;
	TextView mRequestLoadingErrorText;
	ImageView mSuccessFailImage;
	
	private String REQUESTLOADING_LOADING;
	private String REQUESTLOADING_FAIL;
	private String REQUESTLOADING_RETRY;
	private String REQUESTLOADING_SUCCESS;
	private String REQUESTLOADING_CONTINUE;
	
	
	private Context mContext;
	
	private String mTag;
	
	public String getTag() {
		return mTag;
	}
	public void setTag(String tag) {
		this.mTag = tag;
	}

	public RequestLoadingWeb(Window window){
		this(window, null);
	}
	
	public RequestLoadingWeb(Window window, View.OnClickListener againListener){
		this(window, againListener, null);
	}

	public RequestLoadingWeb(Window window, View.OnClickListener againListener, View.OnClickListener cancelListener){
	    mLoadingView = window.findViewById(R.id.loading_view);
		initUI(window.getContext(), mLoadingView, againListener, cancelListener);
	}

	public RequestLoadingWeb(View view){
		this(view, null);
	}

	public RequestLoadingWeb(View view, View.OnClickListener againListener){
		this(view, againListener, null);
	}

	public RequestLoadingWeb(View view, View.OnClickListener againListener, View.OnClickListener cancelListener){
	    mLoadingView = view.findViewById(R.id.loading_view);
		initUI(view.getContext(),mLoadingView, againListener, cancelListener);
	}

	private void initUI(Context context, View loadingView, OnClickListener againListener,
			OnClickListener cancelListener) {
		mContext = context;
	    REQUESTLOADING_LOADING = context.getResources().getString(R.string.request_loading_info);
	    REQUESTLOADING_FAIL = context.getResources().getString(R.string.request_loading_fail);
	    REQUESTLOADING_RETRY = context.getResources().getString(R.string.requestloading_retry);
	    REQUESTLOADING_SUCCESS = context.getResources().getString(R.string.requestloading_success);
	    REQUESTLOADING_CONTINUE = context.getResources().getString(R.string.requestloading_continue);
	    
		mRequestInLoadingView = loadingView.findViewById(R.id.RequestInLoading);
		mRequestLoadingProgressText = (TextView) loadingView.findViewById(R.id.RequestLoadingProgressText);
		
		mRequestError = loadingView.findViewById(R.id.RequestError);
		mRequestLoadingErrorText = (TextView) loadingView.findViewById(R.id.RequestLoadingErrorText);
		mLoadingRelativeLayout = loadingView.findViewById(R.id.public_request_loading_view);
		
		btn_ErrAgin = (Button)loadingView.findViewById(R.id.bt_connect_refresh);
		if(againListener != null){
			mLoadingRelativeLayout.setOnClickListener(againListener);
			btn_ErrAgin.setOnClickListener(againListener);
		}
	}
	@Override
	public void setAgainListener(OnClickListener againListener){
		if(againListener != null){
			mLoadingRelativeLayout.setOnClickListener(againListener);
			btn_ErrAgin.setOnClickListener(againListener);
		}
	}
    /**
     * 设置loading内容的背景颜色
     * @param color
     */
	public void setLoadBg(int color){
		mLoadingRelativeLayout.setBackgroundColor(color);
	}
	public int getStatus() {
		return mStatus;
	}

	public void setStatus(int status) {
		this.mStatus = status;
	}
    

	
	/**
	 * 把状态改变到正常(normal)
	 */
	public void statuesToNormal(){
		if(mStatus != STATUS_NORMAL) {
			mLoadingView.setVisibility(View.GONE);
			mStatus = STATUS_NORMAL;
		}
	}
	
	/**
	 * 把状态改变到加载中(inloading)
	 */
	public void statuesToInLoading(){
		statuesToInLoading(REQUESTLOADING_LOADING);
	}
	
	/**
	 * 把状态改变到加载中(inloading)
	 */
	public void statuesToInLoading(String loadingText){
	    statuesToInLoading(loadingText,true);
	}
	
	/**
     * 把状态改变到加载中(inloading)
     */
    public void statuesToInLoading(String loadingText,boolean changeBg){
        if(mStatus != STATUS_IN_LOADING) {
            if(changeBg){
                mLoadingView.setBackgroundResource(mContext.getResources().getColor(R.color.c00000000));
            }else{
                mLoadingView.setBackgroundResource(mContext.getResources().getColor(R.color.cc0000000));
            }
            mLoadingView.setVisibility(View.VISIBLE);
            mRequestInLoadingView.setVisibility(View.VISIBLE);
            mRequestError.setVisibility(View.GONE);
            
            mRequestLoadingProgressText.setText(loadingText);
            
            mStatus = STATUS_IN_LOADING;
        }
    }
	
	/**
	 * 把状态改变到错误中(error)
	 */
	public void statuesToError(){
		statuesToError(REQUESTLOADING_FAIL);
	}
	
	/*public void statuesToError(String errorInfo){
		statuesToError(errorInfo);
	}*/
	
	/*public void statuesToError(String errorInfo, String againName){
		statuesToError(errorInfo);
	}*/
	
	public void statuesToError(String errorInfo){
//		if(mStatus != STATUS_ERROR) {
		mLoadingView.setVisibility(View.VISIBLE);
		mRequestInLoadingView.setVisibility(View.GONE);
		mRequestError.setVisibility(View.VISIBLE);
		
		mRequestLoadingErrorText.setText(errorInfo);
		/*mSuccessFailImage.setImageResource(R.drawable.publish_fail);
		mAgain.setText(againName);
		mCancel.setText(cancelName);*/
		
		mStatus = STATUS_ERROR;
//		}
	}
	/**
	 * 展示错误，并隐藏view
	 * @param errorInfo
	 */
	public void statuesToErrorGoneErrorView(String errorInfo){
		this.statuesToError(errorInfo);
		mRequestLoadingErrorText.setCompoundDrawables(null, null, null, null);
	}
	
	/**
	 * 把状态改变到成功中(success)
	 */
	public void statuesToSuccess(){
		statuesToSuccess(REQUESTLOADING_SUCCESS);
	}
	
	public void statuesToSuccess(String successInfo){
		statuesToSuccess(successInfo, REQUESTLOADING_CONTINUE);
	}
	
	public void statuesToSuccess(String successInfo, String againName){
		statuesToSuccess(successInfo, againName, "取　消");
	}
	
	public void statuesToSuccess(String successInfo, String againName, String cancelName){
		if(mStatus != STATUS_SUCCESS) {
		    mLoadingView.setBackgroundResource(mContext.getResources().getColor(R.color.c00000000));
			mLoadingView.setVisibility(View.GONE);
			mRequestInLoadingView.setVisibility(View.GONE);
			mRequestError.setVisibility(View.GONE);
			
			mRequestLoadingErrorText.setText(successInfo);
			/*mSuccessFailImage.setImageResource(R.drawable.publish_success);
			mAgain.setText(againName);
			mCancel.setText(cancelName);*/
			
			mStatus = STATUS_SUCCESS;
		}
	}
	
	public boolean isLoadingShow(){
		return mLoadingView.isShown();
	}
}
