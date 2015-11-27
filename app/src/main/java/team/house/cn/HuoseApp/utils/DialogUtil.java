package team.house.cn.HuoseApp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.activity.BaseActivity;
import team.house.cn.HuoseApp.views.RequestLoadingDialog;


/**
 * @author jiazhaoyang 对话框管理
 */
public class DialogUtil {
	private static DialogUtil instance;
	private Dialog mDialog;
	private RequestLoadingDialog mProgressDialog;
	private Activity mActivity;

//	private IPositiveButtonDialogListener mIPositiveButtonDialogListener;
//	private INegativeButtonDialogListener mINegativeButtonDialogListener;

	private DialogUtil() {

	}

	public static DialogUtil getInstance() {
		if (instance == null) {
			instance = new DialogUtil();
		}
		return instance;
	}

	/*
	 * public void setContext(Activity activity) { this.mActivity = activity; }
	 */

	/**
	 * 一个按钮的Dialog
	 * @param activity
	 * @param title
	 * @param subtitle
	 * @param positiveButtonText
	 * @param positiveListener
	 */
	public void createAlertDiaog(Activity activity, CharSequence title, CharSequence subtitle, CharSequence positiveButtonText, IPositiveButtonDialogListener positiveListener) {
//		Activity activity, CharSequence title, CharSequence subtitle, int resId, CharSequence positivebutton, final IPositiveButtonDialogListener positiveListener, CharSequence negativebutton, final INegativeButtonDialogListener negativeListener, int gravity
		createAlertDiaog(activity, title, subtitle, 0, positiveButtonText, positiveListener, "", null, Gravity.CENTER);
	}

	/**
	 * 两个按钮  两个监听
	 * @param activity
	 * @param title
	 * @param subtitle
	 * @param positiveButtonText
	 * @param positiveListener
	 * @param negativeButtonText
	 * @param negativeListener
	 */
	public void createAlertDialog(Activity activity, CharSequence title, CharSequence subtitle, CharSequence positiveButtonText, IPositiveButtonDialogListener positiveListener, CharSequence negativeButtonText, INegativeButtonDialogListener negativeListener) {
		createAlertDiaog(activity , title, subtitle, 0, positiveButtonText, positiveListener, negativeButtonText, negativeListener, Gravity.CENTER);
	}

	/**
	 * 两个按钮 一个按钮有监听
	 * @param activity
	 * @param title
	 * @param positiveButtonText
	 * @param positiveListener
	 * @param negativeButtonText
	 */
	public void createAlertDiaog(Activity activity, CharSequence title, CharSequence positiveButtonText, IPositiveButtonDialogListener positiveListener,CharSequence negativeButtonText) {
//		Activity activity, CharSequence title, CharSequence subtitle, int resId, CharSequence positivebutton, final IPositiveButtonDialogListener positiveListener, CharSequence negativebutton, final INegativeButtonDialogListener negativeListener, int gravity
		createAlertDiaog(activity, title, "", 0, positiveButtonText, positiveListener, negativeButtonText, null, Gravity.CENTER);
	}

//	public void createAlertDiaog(CharSequence title, CharSequence subtitle, CharSequence positiveButtonText, OnClickListener mlistener1) {
//		createAlertDiaog(title, subtitle, 0, positiveButtonText, mlistener1, null, null);
//	}

	/**
	 * 只有tiltle的两个提示按钮,用于询问或退出
	 *
	 * @param title
	 * @param positiveButtonText
	 *            右边按钮名称
	 * @param positiveListener
	 * @param negativeButtonText
	 *            左边按钮名称
	 * @param negativeListener
	 */

	public void createAlertDiaog(Activity activity,CharSequence title, CharSequence positiveButtonText, IPositiveButtonDialogListener positiveListener, CharSequence negativeButtonText, INegativeButtonDialogListener negativeListener) {
		createAlertDiaog(activity , title, "", 0, positiveButtonText, positiveListener, negativeButtonText, negativeListener, Gravity.CENTER);
	}

	/**
	 *
	 * @param activity
	 * @param title
	 * @param subtitle
	 * @param positiveButtonText
	 * @param positiveListener
	 * @param negativeButtonText
	 * @param negativeListener
	 * @param gravity
	 */
	public void createAlertDialog(Activity activity, CharSequence title, CharSequence subtitle, CharSequence positiveButtonText, IPositiveButtonDialogListener positiveListener, CharSequence negativeButtonText, INegativeButtonDialogListener negativeListener, int gravity) {
		createAlertDiaog(activity , title, subtitle, 0, positiveButtonText, positiveListener, negativeButtonText, negativeListener,gravity);
	}
	/**
	 * 判断对话框是否显示
	 */
	public Boolean isDialogShowing() {
		return mDialog == null ? false : mDialog.isShowing();
	}


	/**
	 * 有1个按钮1个图片的dialog;
	 *
	 * @param title
	 * @param subtitle
	 * @param imgResoureid
	 *            图片id，没有图片传0
	 * @param positiveButtonText
	 * @param positiveButton
	 */
	public void createAlertDiaog(Activity activity, CharSequence title, CharSequence subtitle, int imgResoureid, CharSequence positiveButtonText, IPositiveButtonDialogListener positiveButton) {
		createAlertDiaog(activity,title, subtitle, imgResoureid, positiveButtonText, positiveButton, null, null, Gravity.CENTER);
	}

	public void createAlertDiaog(Activity activity,CharSequence title, CharSequence subtitle, int imgResoureid, CharSequence positiveButtonText, IPositiveButtonDialogListener positiveListener, int gravity) {
		createAlertDiaog(activity,title, subtitle, imgResoureid, positiveButtonText, positiveListener, null, null, gravity);
	}

	public void createAlertDiaog(Activity activity,CharSequence title, CharSequence subtitle, CharSequence positiveButtonText, IPositiveButtonDialogListener positiveListener,CharSequence negativeButtonText, INegativeButtonDialogListener negativeButton) {
		createAlertDiaog(activity,title, subtitle, 0, positiveButtonText, positiveListener, negativeButtonText, negativeButton, Gravity.CENTER);
	}


	/**
	 * 有两个按钮1个图片dialog;
	 * @param activity
	 * @param title  标题
	 * @param subtitle  消息 无消息传递空
	 * @param resId     图片id，没有图片传0
	 * @param positivebutton 右边按钮的名字
	 * @param positiveListener
	 * @param negativebutton 左边按钮名字
	 * @param negativeListener
	 * @param gravity
	 */
	public void createAlertDiaog(Activity activity, CharSequence title, CharSequence subtitle, int resId, CharSequence positivebutton, final IPositiveButtonDialogListener positiveListener, CharSequence negativebutton, final INegativeButtonDialogListener negativeListener, int gravity) {
		//创建之前先dismiss掉之前的对话框
		dismissAlertDialog();
		// get data
		mActivity = activity;
		mDialog = new Dialog(mActivity, R.style.MyDialog);
		mDialog.setCanceledOnTouchOutside(false);
		View layout = LayoutInflater.from(mActivity).inflate(R.layout.layout_fragmentdialog, null);
		TypefaceHelper.typeface(layout);
		ImageView imgIcon = (ImageView) layout.findViewById(R.id.img_icon);
		TextView txtTitle = (TextView) layout.findViewById(R.id.txt_title);
		TextView txtSubTitle = (TextView) layout.findViewById(R.id.txt_subtitle);
		Button btnConfirm = (Button) layout.findViewById(R.id.btn_confirm);
		Button btnCancle = (Button) layout.findViewById(R.id.btn_cancle);
		txtTitle.setText(title);
		txtSubTitle.setText(subtitle);
		txtSubTitle.setGravity(gravity);
		btnConfirm.setText(positivebutton);
		btnCancle.setText(negativebutton);
		btnConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dismissAlertDialog();
				if (positiveListener != null) {
					positiveListener.onPositiveButtonClicked(0);
				}
			}
		});
		btnCancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dismissAlertDialog();
				if (negativeListener != null) {
					negativeListener.onNegativeButtonClicked(0);
				}
			}
		});

		if (TextUtils.isEmpty(title)) {
			txtTitle.setVisibility(View.GONE);
		}
		if (TextUtils.isEmpty(subtitle)) {
			txtSubTitle.setVisibility(View.GONE);
		}
		if (TextUtils.isEmpty(positivebutton)) {
			btnConfirm.setVisibility(View.GONE);
		}
		if (TextUtils.isEmpty(negativebutton)) {
			btnCancle.setVisibility(View.GONE);
		}
		if (resId <= 0) {
			imgIcon.setVisibility(View.GONE);
		} else {
			imgIcon.setImageResource(resId);
			imgIcon.setVisibility(View.VISIBLE);
		}
		Window window = mDialog.getWindow();
		window.setGravity(Gravity.CENTER);
		window.setWindowAnimations(R.style.mystyle);
		mDialog.setContentView(layout);
		mDialog.setCancelable(false);
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.show();
	}
	/*public void createAlertDiaog(Activity activity, CharSequence title, CharSequence msg, int imgResoureid, CharSequence positivebutton, IPositiveButtonDialogListener positiveListener, CharSequence negativebutton, INegativeButtonDialogListener negativeListener, int gravity) {
		// 在创建之前，保证只有一个dialog在显示
		dismissAlertDialog();
		mDialog = new Dialog(mActivity, R.style.MyDialog);
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.setCancelable(true);
		LayoutInflater inflater = LayoutInflater.from(mActivity);
		View layout = inflater.inflate(R.layout.layout_dialog, null);
		TypefaceHelper.typeface(layout);
		LinearLayout layout_content = (LinearLayout) layout.findViewById(R.id.layout_content);
		LinearLayout layout_havemsg = (LinearLayout) layout.findViewById(R.id.layout_havemsg);
		ImageView image = (ImageView) layout.findViewById(R.id.img);
		TextView txt_title = (TextView) layout.findViewById(R.id.txt_title);
		TextView txt_content = (TextView) layout.findViewById(R.id.txt_msg);
		TextView txt_onlytitle = (TextView) layout.findViewById(R.id.txt_onlytitle);
		Button btn_assuere = (Button) layout.findViewById(R.id.btn_assuere);
		Button btn_cancle = (Button) layout.findViewById(R.id.btn_cancle);

		RelativeLayout.LayoutParams contentParams = (RelativeLayout.LayoutParams) layout_content.getLayoutParams();
		LinearLayout.LayoutParams havemsgparams = (LinearLayout.LayoutParams) layout_havemsg.getLayoutParams();
		if (imgResoureid > 0) {
			image.setImageResource(imgResoureid);
			image.setVisibility(View.VISIBLE);
			contentParams.setMargins(0, CommonTools.dip2px(mActivity, 43), 0, 0);
		} else {
			havemsgparams.setMargins(0, 25, 0, 0);
			image.setVisibility(View.GONE);
			contentParams.setMargins(0, 0, 0, 0);

		}
		layout_havemsg.setLayoutParams(havemsgparams);
		layout_content.setLayoutParams(contentParams);
		if (TextUtils.isEmpty(msg)) {
			txt_onlytitle.setVisibility(View.VISIBLE);
			layout_havemsg.setVisibility(View.GONE);
			if (title instanceof SpannableString) {
				txt_onlytitle.setText((SpannableString) title);
			} else {
				txt_onlytitle.setText((String) title);
			}

		} else {// msg不为空
			layout_havemsg.setVisibility(View.VISIBLE);
			txt_onlytitle.setVisibility(View.GONE);
			if (title instanceof SpannableString) {
				txt_title.setText((SpannableString) title);
			} else {// String
				txt_title.setText((String) title);
				if (TextUtils.isEmpty((String) title)) {
					txt_title.setVisibility(View.GONE);
				} else {
					txt_title.setVisibility(View.VISIBLE);
				}
			}

			txt_content.setText(msg);
		}
		if (title instanceof String && title.equals("")) {
			txt_title.setVisibility(View.GONE);
		}
		// mDialog.getLayoutInflater();
		Window window = mDialog.getWindow();
		window.setGravity(Gravity.CENTER);
		if (btn1title != null && mlistener1 != null) {
			btn_assuere.setText(btn1title);
			btn_assuere.setOnClickListener(mlistener1);
		}
		if (btn2title != null && mlistener2 != null) {
			btn_cancle.setText(btn2title);
			btn_cancle.setOnClickListener(mlistener2);
			btn_cancle.setVisibility(View.VISIBLE);
		} else {
			btn_cancle.setVisibility(View.GONE);
		}

		window.setWindowAnimations(R.style.mystyle);
		mDialog.setCancelable(false);
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.setContentView(layout);
		mDialog.show();
	}*/

	/*
	 * public void createAlertDiaog(Object title, String msg, int imgResoureid,
	 * String btn1title, OnClickListener mlistener1, String btn2title,
	 * OnClickListener mlistener2, int msglayout) { dismissAlertDialog();
	 * mDialog = new Dialog(mActivity, R.style.MyDialog);
	 * mDialog.setCanceledOnTouchOutside(false); LayoutInflater inflater =
	 * LayoutInflater.from(mActivity); View layout =
	 * inflater.inflate(R.layout.layout_dialog, null);
	 * TypefaceHelper.typeface(layout); LinearLayout layout_content =
	 * (LinearLayout) layout.findViewById(R.id.layout_content); LinearLayout
	 * layout_havemsg = (LinearLayout) layout.findViewById(R.id.layout_havemsg);
	 * ImageView image = (ImageView) layout.findViewById(R.id.img); TextView
	 * txt_title = (TextView) layout.findViewById(R.id.txt_title); TextView
	 * txt_content = (TextView) layout.findViewById(R.id.txt_msg); TextView
	 * txt_onlytitle = (TextView) layout.findViewById(R.id.txt_onlytitle);
	 * Button btn_assuere = (Button) layout.findViewById(R.id.btn_assuere);
	 * Button btn_cancle = (Button) layout.findViewById(R.id.btn_cancle);
	 *
	 * RelativeLayout.LayoutParams contentParams = (RelativeLayout.LayoutParams)
	 * layout_content.getLayoutParams(); LinearLayout.LayoutParams havemsgparams
	 * = (LinearLayout.LayoutParams) layout_havemsg.getLayoutParams(); if
	 * (imgResoureid > 0) { image.setImageResource(imgResoureid);
	 * image.setVisibility(View.VISIBLE); contentParams.setMargins(0,
	 * CommonTools.dip2px(mActivity, 43), 0, 0); } else {
	 * havemsgparams.setMargins(0, 25, 0, 0); image.setVisibility(View.GONE);
	 * contentParams.setMargins(0, 0, 0, 0);
	 *
	 * } layout_havemsg.setLayoutParams(havemsgparams);
	 * layout_content.setLayoutParams(contentParams); if
	 * (TextUtils.isEmpty(msg)) { txt_onlytitle.setVisibility(View.VISIBLE);
	 * layout_havemsg.setVisibility(View.GONE); if (title instanceof
	 * SpannableString) { txt_onlytitle.setText((SpannableString) title); } else
	 * { txt_onlytitle.setText((String) title); }
	 *
	 * } else {// msg不为空 layout_havemsg.setVisibility(View.VISIBLE);
	 * txt_onlytitle.setVisibility(View.GONE); if (title instanceof
	 * SpannableString) { txt_title.setText((SpannableString) title); } else {//
	 * String txt_title.setText((String) title); if (TextUtils.isEmpty((String)
	 * title)) { txt_title.setVisibility(View.GONE); } else {
	 * txt_title.setVisibility(View.VISIBLE); } }
	 *
	 * txt_content.setText(msg); txt_content.setGravity(msglayout);
	 *
	 * } if (title instanceof String && title.equals("")) {
	 * txt_title.setVisibility(View.GONE); } // mDialog.getLayoutInflater();
	 * Window window = mDialog.getWindow(); window.setGravity(Gravity.CENTER);
	 * if (btn1title != null && mlistener1 != null) {
	 * btn_assuere.setText(btn1title);
	 * btn_assuere.setOnClickListener(mlistener1); } if (btn2title != null &&
	 * mlistener2 != null) { btn_cancle.setText(btn2title);
	 * btn_cancle.setOnClickListener(mlistener2);
	 * btn_cancle.setVisibility(View.VISIBLE); } else {
	 * btn_cancle.setVisibility(View.GONE); }
	 *
	 * window.setWindowAnimations(R.style.mystyle);
	 * mDialog.setContentView(layout); mDialog.setCancelable(false);
	 * mDialog.setCanceledOnTouchOutside(false); mDialog.show(); }
	 */
	/**
	 * 选择司机对话框
	 *
	 * @param title
	 * @param driver
	 * @param btn1title
	 * @param mlistener1
	 * @param btn2title
	 * @param mlistener2
	 */
	/*
	 * public void createDriverChooseDialog(String title,final DriverBean
	 * driver,String btn1title, OnClickListener mlistener1, String btn2title,
	 * OnClickListener mlistener2){ dismissAlertDialog(); mDialog = new
	 * Dialog(mContext,R.style.MyDialog);
	 * mDialog.setCanceledOnTouchOutside(false); LayoutInflater inflater =
	 * LayoutInflater.from(mContext); ViewGroup layout = (ViewGroup)
	 * inflater.inflate(R.layout.layout_driverdialog, null);
	 * TypefaceHelper.typeface(layout); // Typeface typeface =
	 * Typeface.createFromAsset(mContext.getAssets(),APPConfig.ASSETS_FONTS); //
	 * UITools.changeTypeFace(layout, typeface); ImageView image_photo =
	 * (ImageView) layout.findViewById(R.id.img_photo); TextView txt_drivername
	 * = (TextView) layout.findViewById(R.id.txt_drivername); TextView
	 * txt_distance = (TextView)
	 * layout.findViewById(R.id.txt_driverdistance_dialog); TextView
	 * txt_driverscore = (TextView)
	 * layout.findViewById(R.id.txt_driverscore);//4.8 TextView txt_fen =
	 * (TextView) layout.findViewById(R.id.d_fen);//分 // TextView
	 * txt_serviceme_time = (TextView)
	 * layout.findViewById(R.id.txt_serviceme_time); TextView txt_service_time =
	 * (TextView) layout.findViewById(R.id.txt_service_time); TextView txt_title
	 * = (TextView) layout.findViewById(R.id.txt_title); LinearLayout btn_ok =
	 * (LinearLayout) layout.findViewById(R.id.btn_ok); TextView txt_choosehim =
	 * (TextView) layout.findViewById(R.id.txt_choosehim);//选他 //
	 * txt_counttime.setText("("+CountDownTimerTask.getInstance().getRemaingTime()+"秒)");
	 * Button btn_cancle = (Button) layout.findViewById(R.id.btn_cancle);
	 * btn_ok.setOnClickListener(mlistener2);
	 * btn_cancle.setOnClickListener(mlistener1);
	 * txt_choosehim.setText(btn2title); btn_cancle.setText(btn1title);
	 *
	 * txt_title.setText(title); //司机信息 if(driver==null) return;
	 * DriverHelper.getImage(driver.getPhoto(), image_photo);
	 * txt_drivername.setText(driver.getName());
	 * txt_distance.setText(driver.getFormatedDistance());
	 * txt_driverscore.setText(driver.getScore()+"");
	 * txt_serviceme_time.setText(driver.getFormatedServiceMeTime());
	 * txt_service_time.setText(driver.getFormatedServiceTime());
	 *
	 * if(CommonTools.floatCompare(driver.getScore(), 0)){ //暂无评分
	 * txt_driverscore.setVisibility(View.GONE); txt_fen.setText("暂无评分"); }else{
	 * txt_driverscore.setVisibility(View.VISIBLE); txt_fen.setText("分"); }
	 * Window window = mDialog.getWindow(); window.setGravity(Gravity.CENTER);
	 * window.setWindowAnimations(R.style.mystyle);
	 * mDialog.setContentView(layout); mDialog.setCancelable(false);
	 * mDialog.setCanceledOnTouchOutside(false); mDialog.show(); }
	 */

	/*
	 * public void createNoticeAlertDiaog(String title, String btn1title,
	 * OnClickListener mlistener1, String btn2title, OnClickListener mlistener2)
	 * { dismissAlertDialog(); mDialog = new Dialog(mActivity,
	 * R.style.MyDialog); mDialog.setCanceledOnTouchOutside(false);
	 * mDialog.setCancelable(true); LayoutInflater inflater =
	 * LayoutInflater.from(mActivity); View layout =
	 * inflater.inflate(R.layout.layout_notice_dialog, null);
	 * TypefaceHelper.typeface(layout); // RelativeLayout layout_dialog //
	 * =(RelativeLayout)layout.findViewById(R.id.layout_dialog); // Typeface
	 * typeface = //
	 * Typeface.createFromAsset(mContext.getAssets(),APPConfig.ASSETS_FONTS); //
	 * UITools.changeTypeFace(layout_dialog, typeface);
	 *
	 * LinearLayout layout_content = (LinearLayout)
	 * layout.findViewById(R.id.layout_content);
	 *
	 * // LinearLayout layout_havemsg = (LinearLayout) //
	 * layout.findViewById(R.id.layout_havemsg);
	 *
	 * ImageView image = (ImageView) layout.findViewById(R.id.img); // TextView
	 * txt_title = (TextView) layout.findViewById(R.id.txt_title); // TextView
	 * txt_content = (TextView) layout.findViewById(R.id.txt_msg); TextView
	 * txt_onlytitle = (TextView) layout.findViewById(R.id.txt_onlytitle);
	 * Button btn_assuere = (Button) layout.findViewById(R.id.btn_assuere);
	 * Button btn_cancle = (Button) layout.findViewById(R.id.btn_cancle);
	 *
	 * RelativeLayout.LayoutParams contentParams = (RelativeLayout.LayoutParams)
	 * layout_content.getLayoutParams(); // LinearLayout.LayoutParams
	 * havemsgparams = //
	 * (LinearLayout.LayoutParams)layout_havemsg.getLayoutParams(); // if
	 * (imgResoureid > 0) { // image.setImageResource(imgResoureid); //
	 * image.setVisibility(View.VISIBLE); // contentParams.setMargins(0,
	 * CommonTools.dip2px(mContext, 43), 0, 0); // } else { //
	 * havemsgparams.setMargins(0, 25, 0, 0); // image.setVisibility(View.GONE);
	 * // contentParams.setMargins(0, 0, 0, 0); // // } //
	 * layout_havemsg.setLayoutParams(havemsgparams); //
	 * layout_content.setLayoutParams(contentParams); // if
	 * (TextUtils.isEmpty(msg)) { // txt_onlytitle.setVisibility(View.VISIBLE);
	 * // layout_havemsg.setVisibility(View.GONE); // if(title instanceof
	 * SpannableString ){ // txt_onlytitle.setText((SpannableString)title); //
	 * }else{ txt_onlytitle.setText((String) title); // }
	 *
	 * // } else {//msg不为空 // layout_havemsg.setVisibility(View.VISIBLE); //
	 * txt_onlytitle.setVisibility(View.GONE); // if(title instanceof
	 * SpannableString){ // txt_title.setText((SpannableString)title); //
	 * }else{//String // txt_title.setText((String)title); //
	 * if(TextUtils.isEmpty((String)title)){ //
	 * txt_title.setVisibility(View.GONE); // }else{ //
	 * txt_title.setVisibility(View.VISIBLE); // } // } // //
	 * txt_content.setText(msg); // } // if(title instanceof
	 * String&&title.equals("")){ // txt_title.setVisibility(View.GONE); // } //
	 * mDialog.getLayoutInflater(); Window window = mDialog.getWindow();
	 * window.setGravity(Gravity.CENTER); if (btn1title != null && mlistener1 !=
	 * null) { btn_assuere.setText(btn1title);
	 * btn_assuere.setOnClickListener(mlistener1); } if (btn2title != null &&
	 * mlistener2 != null) { btn_cancle.setText(btn2title);
	 * btn_cancle.setOnClickListener(mlistener2);
	 * btn_cancle.setVisibility(View.VISIBLE); } else {
	 * btn_cancle.setVisibility(View.GONE); }
	 *
	 * window.setWindowAnimations(R.style.mystyle);
	 * mDialog.setCancelable(false); mDialog.setCanceledOnTouchOutside(false);
	 * mDialog.setContentView(layout); mDialog.show(); }
	 */
	/**
	 * login waiting
	 */
	public RequestLoadingDialog createLoginWaitting() {
		mProgressDialog = new RequestLoadingDialog(mActivity);
		try {
			mProgressDialog.stateToLoading();
		} catch (Exception e) {
		}
		return mProgressDialog;

	}

	public RequestLoadingDialog createLoginWaitting(String loadingtext) {
		mProgressDialog = new RequestLoadingDialog(mActivity);
		try {
			mProgressDialog.stateToLoading(loadingtext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mProgressDialog;

	}

	public RequestLoadingDialog createRequestLoadingDialog(Context context, String loadingtext) {
		mProgressDialog = new RequestLoadingDialog(context);
		try {
			mProgressDialog.stateToLoading(loadingtext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mProgressDialog;

	}

	public RequestLoadingDialog createRequestLoadingDialog(Context context) {
		return createRequestLoadingDialog(context, "");
	}

	public void dismissAlertDialog() {
		if (mDialog != null && mDialog.isShowing() && mActivity!=null && !((BaseActivity) mActivity).isFinishing()) {
			mDialog.dismiss();
		}
	}

	// public void dissmissDialog() {
	// if (mDialog != null) {
	// mDialog.dismiss();
	// }
	// }

	public void dissmissLoginWaittingDialog() {
		if (mProgressDialog != null) {
			mProgressDialog.stateToNormal();
		}
	}

	public Dialog getmDialog() {
		return mDialog;
	}

	public void dissmissRequestLoadingDialog() {
		if (mProgressDialog != null) {
			mProgressDialog.stateToNormal();
		}
	}

	public void destroyInstance() {
		if (null != instance) {
			instance = null;
		}
	}

	/**
	 * 确定按钮
	 *
	 * @author xiayong
	 *
	 */
	public interface IPositiveButtonDialogListener {

		public void onPositiveButtonClicked(int requestCode);
	}

	/**
	 * 取消按钮
	 *
	 * @author xiayong
	 *
	 */
	public interface INegativeButtonDialogListener {

		public void onNegativeButtonClicked(int requestCode);
	}
//	private void creatBackMoneyDialog(Activity activity, CharSequence title, CharSequence subtitle, int resId, CharSequence positivebutton, final IPositiveButtonDialogListener positiveListener, CharSequence negativebutton, final INegativeButtonDialogListener negativeListener, int gravity) {
//	        //创建之前先dismiss掉之前的对话框
//	        dismissAlertDialog();
//	        // get data
//	        mActivity = activity;
//	        mDialog = new Dialog(mActivity, R.style.MyDialog);
//	        mDialog.setCanceledOnTouchOutside(false);
//	        View layout = LayoutInflater.from(mActivity).inflate(R.layout.layout_backmoneydialog, null);
//	        TypefaceHelper.typeface(layout);
//	        TextView txtTitle = (TextView) layout.findViewById(R.id.txt_title);
//	        TextView txtSubTitle = (TextView) layout.findViewById(R.id.txt_subtitle);
//	        Button btnConfirm = (Button) layout.findViewById(R.id.btn_confirm);
//	        Button btnCancle = (Button) layout.findViewById(R.id.btn_cancle);
//	        txtTitle.setText(title);
//	        txtSubTitle.setText(subtitle);
//	        txtSubTitle.setGravity(gravity);
//	        btnConfirm.setText(positivebutton);
//	        btnCancle.setText(negativebutton);
//	        btnConfirm.setOnClickListener(new OnClickListener() {
//
//	            @Override
//	            public void onClick(View v) {
//	                dismissAlertDialog();
//	                if (positiveListener != null) {
//	                    positiveListener.onPositiveButtonClicked(0);
//	                }
//	            }
//	        });
//	        btnCancle.setOnClickListener(new OnClickListener() {
//
//	            @Override
//	            public void onClick(View v) {
//	                dismissAlertDialog();
//	                if (negativeListener != null) {
//	                    negativeListener.onNegativeButtonClicked(0);
//	                }
//	            }
//	        });
//	        Window window = mDialog.getWindow();
//	        window.setGravity(Gravity.CENTER);
//	        window.setWindowAnimations(R.style.mystyle);
//	        mDialog.setContentView(layout);
//	        mDialog.setCancelable(false);
//	        mDialog.setCanceledOnTouchOutside(false);
//	        mDialog.show();
//	    }

	public void createReOrderDialog(){



	}

}
