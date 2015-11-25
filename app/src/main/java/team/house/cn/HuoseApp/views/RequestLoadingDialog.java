package team.house.cn.HuoseApp.views;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import team.house.cn.HuoseApp.R;


/**
 * 注意：按钮只有Left, Right两个按钮，如果只显示一个按钮时，显示的是Left按钮，Right按钮会隐藏
 * 
 * @author ly
 * 
 */
public class RequestLoadingDialog extends Dialog implements WubaLoadingDialog {

  public static interface OnButClickListener {
    void onLeft(State state, Object tag);

    void onRight(State state, Object tag);
  }

  public static enum State {
    Normal, Loading, Result,
  }

  private Object mTag;

  public Object getTag() {
    return mTag;
  }

  public void setTag(Object tag) {
    mTag = tag;
  }

  private State mState = State.Normal;

  public State getState() {
    return mState;
  }


  TextView mLoadingText;
  ProgressBar mAnimoateBar;

  private final String REQUESTLOADING_LOADING;

  public RequestLoadingDialog(Context context) {
    super(context, R.style.RequestDialog);
    // 设置点击屏幕Dialog不消失
    setCanceledOnTouchOutside(false);
    setCancelable(false);
    REQUESTLOADING_LOADING = context.getResources().getString(R.string.request_loading_info);
    init(context);
  }

  /**
   * 设置title上的文字
   */
  public void setTitleText(int resoureText) {
    ((TextView) findViewById(R.id.title)).setText(resoureText);
  }

  private void init(Context context) {
    try {
      LayoutInflater inflater = LayoutInflater.from(context);
      View layout = inflater.inflate(R.layout.layout_loading, null);
      // LinearLayout layout_content = (LinearLayout) layout.findViewById(R.id.layout_content);

      // layout_content.getBackground().setAlpha(200);
      mLoadingText = (TextView) layout.findViewById(R.id.txt_msg);
      mLoadingText.setText(REQUESTLOADING_LOADING);
      mAnimoateBar = (ProgressBar) layout.findViewById(R.id.RequestLoadingProgress);
      mAnimoateBar.setVisibility(View.VISIBLE);
      setContentView(layout);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static interface OnBackListener {
    /**
     * 如果返回为true，表示处理了这个返回事件
     * 
     * @return
     */
    boolean onBack();
  }

  private OnBackListener mBackListener;

  public void setBackListener(OnBackListener backListener) {
    this.mBackListener = backListener;
  }

  @Override
  public void onBackPressed() {
    if (mBackListener != null && mBackListener.onBack()) {
      return;
    }
    super.onBackPressed();
  }

  public void stateToNormal() {
    if (this.isShowing()) {
      this.dismiss();
    }
    mState = State.Normal;
    mTag = null;


  }

  public void stateToLoading() {
    stateToLoading(REQUESTLOADING_LOADING);
  }

  public void setLoadingText(String loadingText) {
    // 判断LoadingText是否为空，则不进行设置，保持之前的文字信息
    if (loadingText != null) {
      mLoadingText.setText(loadingText);
    }
  }

  public void stateToLoading(String loadingText) {
    if (mState == State.Loading) {
      return;
    }
    mState = State.Loading;
    mTag = null;

    // 本身显示
    if (!this.isShowing()) {
      this.show();
    }

    // mContentWrap.setBackgroundResource(R.drawable.z_dialog_footer);

    // 判断LoadingText是否为空，则不进行设置，保持之前的文字信息
    if (loadingText != null) {
      mLoadingText.setText(loadingText);
    }


  }

  public void setVisibleProgressBar(boolean visible) {
    mAnimoateBar.setVisibility(visible ? View.VISIBLE : View.GONE);
  }

  public void setVisibleText(boolean visible) {
    mLoadingText.setVisibility(visible ? View.VISIBLE : View.GONE);
  }

}
