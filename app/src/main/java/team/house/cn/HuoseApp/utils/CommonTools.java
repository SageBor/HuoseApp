package team.house.cn.HuoseApp.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Looper;
import android.os.Process;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 帮助类，一些常用的函数可写在此处
 *
 * date 14/3/24
 *
 * Author hxb
 */
public class CommonTools {
  private static Toast mToast;
//  private static RequestLoadingDialog mLoadingDialog;
  private static String mDeviceImei;
  private static String imei_config = "imei";

  private static int showDialogNum = 0; // 提示网络异常对话框次数

  public static Boolean checkPhone(String phone) {
    if (TextUtils.isEmpty(phone))
      return false;
    // if (phone.trim().length() == 11 && phone.startsWith("1")) {
    // return true;
    // }
    return phone.trim().matches("^1\\d{10}$");
  }

  public static Boolean CheckShowCall400() {
    try {
      Date now = new Date();
      if (now.getHours() > 7 && now.getHours() < 20) {
        return true;
      }
    } catch (Exception e) {

    }
    return false;
  }

//  /**
//   * 获取imei,如果获取不到，则生成一个15位号码
//   *
//   * @return 获取imei,如果获取不到，则生成一个15位号码
//   */
//  public static String getImei(final Context context) {
//    if (!TextUtils.isEmpty(mDeviceImei)) {
//      return mDeviceImei;
//    }
//
//    TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//    String imei;
//
//    try {
//      imei = tm.getDeviceId();// imei
//    } catch (Exception e) {
//      imei = null;
//    }
//
//    if (StringUtils.isEmpty(imei) || "0".equals(imei)) {
//      // 如果imei号为空或0，取mac地址作为imei号传递
//      try {
//        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//        WifiInfo info = wifi.getConnectionInfo();
//        imei = info.getMacAddress();
//      } catch (Exception e) {
//        imei = null;
//      }
//
//      // 如果mac地址为空或0，则通过uuid生成的imei号来传递
//      if (StringUtils.isEmpty(imei) || "0".equals(imei)) {
//        imei = PreferenceUtil.getString(context, imei_config);
//
//        if (StringUtils.isEmpty(imei)) {
//          imei = UUIDUtils.getUUID(15);
//          if (StringUtils.isEmpty(imei)) {
//            return "0";
//          }
//          PreferenceUtil.putString(context, imei_config, imei);
//        }
//      }
//    }
//
//    // 　(ly) 转为小写
//    if (imei != null) {
//      imei = imei.toLowerCase(Locale.US);
//    }
//
//    mDeviceImei = imei;
//
//    return imei;
//  }
//
//  /**
//   * 去掉时间的s；
//   *
//   * @param time
//   * @return
//   */
//  public static String DelDateSeconds(String time) {
//    String sRsult = "";
//    try {
//      sRsult = time.substring(0, time.length() - 3);
//    } catch (Exception e) {
//    }
//    return sRsult;
//
//  }
//
//  public static String sendcondtoHour(long seconds) {
//    String sResult = "";
//    try {
//      int Hour = (int) (seconds / 3600);
//      int Minute = (int) ((seconds - Hour * 3600) / 60);
//      int second = (int) (seconds - Hour * 3600 - Minute * 60);
//
//      sResult =
//          String.format("%02d", Hour) + ":" + String.format("%02d", Minute) + ":"
//              + String.format("%02d", second);
//    } catch (Exception e) {
//    }
//    return sResult;
//  }
//
  /**
   * 显示自定义的消息通知
   *
   * @param mcontext
   * @param msg 提示内容
   */
  public static void showcustomAlert(Activity mcontext, String msg) {
    showcustomAlert(mcontext, 0, msg);
  }
//
//  /**
//   * 非UI线程中弹taost
//   *
//   * @param mcontext
//   * @param msg
//   */
//  public static void showcustomAlertNUI(Activity mcontext, String msg) {
//    Looper.prepare();
//    showcustomAlert(mcontext, 0, msg);
//    Looper.loop();
//  }
//
//  public static boolean isNetWorkDialogShowing() {
//    return DialogUtil.getInstance().isDialogShowing();
//  }
//
  /**
   * 显示自定义的待图片的消息通知
   *
   * @param mcontext
   * @param imgResoureid 图片消息id；
   * @param msg
   */
  public static void showcustomAlert(Activity mcontext, int imgResoureid, String msg) {
    showcustomAlert(mcontext,imgResoureid,msg, Toast.LENGTH_SHORT);
  }

//  /**
//   *
//   * @param mcontext
//   * @param msg
//   * @param duration 持续时间
//   */
//  public static void showcustomAlert(Activity mcontext, String msg, int duration){
//    showcustomAlert(mcontext,0,msg,duration);
//  }
////
  /**
   * 显示自定义的待图片的消息通知
   *
   * @param mcontext
   * @param imgResoureid 图片消息id；
   * @param msg
   * @param duration 显示时间
   */
  public static void showcustomAlert(Activity mcontext, int imgResoureid, String msg,int duration) {

//    try {
//      LayoutInflater inflater = mcontext.getLayoutInflater();
//      View layout = inflater.inflate(R.layout.layout_toast, null);
//      ImageView image = (ImageView) layout.findViewById(R.id.img);
//      if (imgResoureid > 0) {
//        image.setImageResource(imgResoureid);
//        image.setVisibility(View.VISIBLE);
//      } else {
//        image.setVisibility(View.GONE);
//      }
//      TextView title = (TextView) layout.findViewById(R.id.txt_msg);
//      title.setText(msg);
//      TypefaceHelper.typeface(title);
//      Toast toast = new Toast(mcontext);
//      toast.setView(layout);
//      toast.setGravity(Gravity.CENTER, 0, 0);
//      toast.setDuration(duration);
//      toast.show();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }

  }
//
//  public static void ShowAlertMsg(Context mcontext, String msg) {
//
//    Toast makeText = Toast.makeText(mcontext, msg, Toast.LENGTH_LONG);
//    makeText.show();
//  }
//
//  public static Intent returntoHome() {
//    Intent intent = new Intent(Intent.ACTION_MAIN);
//    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//    intent.addCategory(Intent.CATEGORY_HOME);
//    return intent;
//  }
//
//  /**
//   * 弹出呼叫号码对话框
//   *
//   * @param mcontext
//   * @param phone
//   */
//  public static void assureCallPhone(final FragmentActivity mcontext, final String name,
//      final String phone) {
//
//    try {
//      String callphonetip = "您正在联系";
//      if (phone.equals(mcontext.getString(R.string.phone))) {
//        callphonetip += "58到家速运客服";
//      } else {
//        callphonetip += name;
//      }
//      DialogUtil.getInstance().createAlertDiaog(mcontext, callphonetip, "呼叫",
//          new DialogUtil.IPositiveButtonDialogListener() {
//
//            @Override
//            public void onPositiveButtonClicked(int requestCode) {
//              Intent phoneIntent =
//                  new Intent("android.intent.action.CALL", Uri.parse("tel:" + phone));
//              mcontext.startActivity(phoneIntent);
//            }
//          }, "取消");
//
//    } catch (Exception e) {
//    }
//  }
//
//  public static void callPhone(Context context, String phone) {
//    if (TextUtils.isEmpty(phone))
//      return;
//    Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phone));
//    context.startActivity(phoneIntent);
//  }
//
//  /**
//   * 跳转到拨号盘
//   *
//   * @param context
//   */
//  public static void Dialing(Context context, String phone) {
//    if (TextUtils.isEmpty(phone))
//      return;
//    Intent phoneIntent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phone));
//    context.startActivity(phoneIntent);
//  }
//
//  public static void showWebActivity(Context mcontext, String title, String weburl) {
//    try {
//      Bundle bundle = new Bundle();
//      if (TextUtils.isEmpty(title) || TextUtils.isEmpty(weburl)) {
//        ShowAlertMsg(mcontext, "标题或链接地址为空，无法打开页面");
//      }
//      WebBundleBean webbean = new WebBundleBean(title, weburl);
//      bundle.putSerializable(WebBundleBean.WEB_SERIALIZABLE_KEY, webbean);
//      Intent intent = new Intent(mcontext, ShareWebActivity.class);
//      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//      intent.putExtras(bundle);
//      mcontext.startActivity(intent);
//    } catch (Exception e) {
//    }
//
//  }
//
//  public static void showCustomWebActivity(Context mcontext, String title, String weburl) {
//    try {
//      System.out.println("==>>");
//      Bundle bundle = new Bundle();
//      if (TextUtils.isEmpty(title) || TextUtils.isEmpty(weburl)) {
//        ShowAlertMsg(mcontext, "标题或链接地址为空，无法打开页面");
//      }
//      WebBundleBean webbean = new WebBundleBean(title, weburl);
//      bundle.putSerializable(WebBundleBean.WEB_SERIALIZABLE_KEY, webbean);
//      Intent intent = new Intent(mcontext, CustomWebActivity.class);
//      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//      intent.putExtras(bundle);
//      mcontext.startActivity(intent);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//
//  }
//
//  /**
//   * 没有标题的url跳转页，显示的标题为生活帮图片
//   *
//   * @param mcontext
//   * @param weburl
//   */
//  public static void showWebActivity(Context mcontext, String weburl) {
//    try {
//      Bundle bundle = new Bundle();
//      WebBundleBean webbean = new WebBundleBean("", weburl);
//      webbean.setShowMainTitle(true);
//      bundle.putSerializable(WebBundleBean.WEB_SERIALIZABLE_KEY, webbean);
//      Intent intent = new Intent(mcontext, ShareWebActivity.class);
//      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//      intent.putExtras(bundle);
//      mcontext.startActivity(intent);
//    } catch (Exception e) {
//    }
//
//  }
//
//  // 判断项目是否显示在最前端
//  public static boolean isAppRuning(Context activity) {
//    try {
//      ActivityManager am = ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE));
//      List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
//      String mainProcessName = activity.getPackageName();
//
//      int myPid = Process.myPid();
//      for (RunningAppProcessInfo info : processInfos) {
//        if (info.pid == myPid && mainProcessName.equals(info.processName)) {
//          return true;
//        }
//      }
//      return false;
//    } catch (Exception e) {
//      return false;
//
//    }
//
//  }
//
//  public static boolean isAppOnForeground(Context context) {
//    // Returns a list of application processes that are running on the
//    // device
//
//    ActivityManager activityManager =
//        (ActivityManager) context.getApplicationContext()
//            .getSystemService(Context.ACTIVITY_SERVICE);
//    String packageName = context.getApplicationContext().getPackageName();
//
//    List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
//    if (appProcesses == null)
//      return false;
//
//    for (RunningAppProcessInfo appProcess : appProcesses) {
//      // The name of the process that this object is associated with.
//      if (appProcess.processName.equals(packageName)
//          && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
//        return true;
//      }
//    }
//
//    return false;
//  }
//
//  /**
//   * 判断一个程序是否显示在前端,根据测试此方法执行效率在11毫秒,无需担心此方法的执行效率
//   *
//   * @param packageName 程序包名
//   * @param context 上 下文环境
//   * @return true--->在前端,false--->不在前端
//   */
//  public static boolean isApplicationShowing(String packageName, Context context) {
//    boolean result = false;
//    ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//    List<RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
//    if (appProcesses != null) {
//      for (RunningAppProcessInfo runningAppProcessInfo : appProcesses) {
//        if (runningAppProcessInfo.processName.equals(packageName)) {
//          int status = runningAppProcessInfo.importance;
//          if (status == RunningAppProcessInfo.IMPORTANCE_VISIBLE
//              || status == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
//            result = true;
//          }
//        }
//      }
//    }
//    return result;
//  }
//
//  /**
//   * 加载本地图片
//   *
//   * @param url
//   * @return
//   */
//  public static Bitmap getLoacalBitmap(String url) {
//    try {
//      FileInputStream fis = new FileInputStream(url);
//      return BitmapFactory.decodeStream(fis); // /把流转化为Bitmap图片
//
//    } catch (FileNotFoundException e) {
//      return null;
//    }
//  }
//
//  /**
//   * 打印消息并且用Toast显示消息
//   *
//   * @param activity
//   * @param message
//   * @param logLevel 填d, w, e分别代表debug, warn, error; 默认是debug
//   */
//  public static final void toastMessage(final Activity activity, final String message,
//      String logLevel) {
//    if ("w".equals(logLevel)) {
//      Log.w("sdkDemo", message);
//    } else if ("e".equals(logLevel)) {
//      Log.e("sdkDemo", message);
//    } else {
//      Log.d("sdkDemo", message);
//    }
//    activity.runOnUiThread(new Runnable() {
//      @Override
//      public void run() {
//        if (mToast != null) {
//          mToast.cancel();
//          mToast = null;
//        }
//        mToast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
//        mToast.show();
//      }
//    });
//  }
//
//  public static final void toastMessageLong(final Activity activity, final String message) {
//    activity.runOnUiThread(new Runnable() {
//      @Override
//      public void run() {
//        if (mToast != null) {
//          mToast.cancel();
//          mToast = null;
//        }
//        mToast = Toast.makeText(activity, message, Toast.LENGTH_LONG);
//        mToast.show();
//      }
//    });
//  }
//
//  /**
//   * 打印消息并且用Toast显示消息
//   *
//   * @param activity
//   * @param message
//   *
//   *        填d, w, e分别代表debug, warn, error; 默认是debug
//   */
//  public static final void toastMessage(final Activity activity, final String message) {
//    toastMessage(activity, message, null);
//  }
//
//  // 获取屏幕宽度
//  public static int getScreehW(Activity context) {
//    int sresult = 0;
//    try {
//      int screenW = context.getWindowManager().getDefaultDisplay().getWidth();
//      sresult = screenW;
//      // sresult = MyHelp.px2dip(context, screenW);
//    } catch (Exception e) {
//    }
//
//    return sresult;
//  }
//
//  public static int px2dip(Context context, float pxValue) {
//    final float scale = context.getResources().getDisplayMetrics().density;
//    return (int) (pxValue / scale + 0.5f);
//  }
//
//  public static int dip2px(Context context, float dipValue) {
//    final float scale = context.getResources().getDisplayMetrics().density;
//    return (int) (dipValue * scale + 0.5f);
//  }
//
//  /**
//   * 停用 通过用设置Activity的切换动画
//   *
//   * @param activity
//   * @param inAnim 界面进入的动画
//   * @param outAnim 界面退出的动画
//   */
//  public static void acitvityTransition(Activity activity, int inAnim, int outAnim) {
//    // activity.overridePendingTransition(inAnim, outAnim);
//  }
//
//  public static String encryptURL(BasicNameValuePair[] beat, String encrypt_keyword) {
//
//    // Enumeration<String> params_enu = beat.
//    Map<String, String> beatObject = new HashMap<String, String>();
//    for (BasicNameValuePair valuse : beat) {
//      beatObject.put(valuse.getName(), valuse.getValue());
//    }
//
//    List<String> params_name = new ArrayList<String>();
//    if (beat == null || beat.length == 0) {
//      return null;
//    }
//    for (BasicNameValuePair valuse : beat) {
//
//      String parmarkey = valuse.getName();
//      while (parmarkey.length() < 5) {
//
//        parmarkey = parmarkey + "`";
//      }
//      params_name.add(parmarkey);
//    }
//
//    Collections.sort(params_name, new Comparator<String>() {
//      @Override
//      public int compare(String b1, String b2) {
//        return MD5.encode(b1).compareTo(MD5.encode(b2));
//      }
//    });
//    StringBuffer result = new StringBuffer();
//    for (String s : params_name) {
//      s = s.replaceAll("`", "");
//      if (result.toString().equals("")) {
//        result.append(s).append("=").append(beatObject.get(s));
//      } else {
//        result.append("&").append(s).append("=").append(beatObject.get(s));
//      }
//    }
//    result.append(encrypt_keyword);
//    Log.w("Request Params:", result.toString());
//    return MD5.encode(result.toString());
//  }
//
//  // nowork dialog
//  public static void showNoWorkDialog(Activity activity) {
//    // show dialog
//    String noworkHint = "我们的工作时间为每天7:00-20:00，如需电话预约或咨询可在该时间段联系我们~";
//    String noworkTitle = " 客服下班啦";
//    String imgFlag = "[img]";
//    SpannableString ss = new SpannableString(imgFlag + noworkTitle);
//
//    Drawable drawable = activity.getResources().getDrawable(R.drawable.emotion);
//    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//
//    ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
//
//    ss.setSpan(span, 0, imgFlag.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//    DialogUtil.getInstance().createAlertDiaog(activity, ss, noworkHint,
//            activity.getResources().getString(R.string.choosecoupons_known), null);
//  }
//
//  public static void OpenUrlBybrowser(Context mContext, String url) {
//    try {
//      Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//      it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
//      mContext.startActivity(it);
//    } catch (Exception e) {
//      ShowAlertMsg(mContext, e.getLocalizedMessage());
//    }
//  }
//
//  public static String getFormatedNowTime(String format) {
//    SimpleDateFormat sdf;
//    try {
//      sdf = new SimpleDateFormat(format);
//    } catch (Exception e) {
//      return "";
//    }
//    return sdf.format(new Date());
//  }
//  public static Date getFormatedTime(String time,String format) {
//    SimpleDateFormat sdf;
//    try {
//      sdf = new SimpleDateFormat(format);
//      return sdf.parse(time);
//    } catch (Exception e) {
//      return null;
//    }
//  }
//
//  /**
//   * 判断网络连接状态
//   *
//   * @param context
//   * @return
//   */
//  public static boolean isNetworkAvailable(Context context) {
//    ConnectivityManager connec =
//        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//    boolean available = false;
//    NetworkInfo[] infos = connec.getAllNetworkInfo();
//    if (infos != null) {
//      for (NetworkInfo info : infos) {
//        if (info.isAvailable() && info.getState() == NetworkInfo.State.CONNECTED) {
//          available = true;
//          break;
//        }
//      }
//    }
//    return available;
//  }
//
//  /**
//   * 跳转到网络设置页面
//   *
//   * @param context
//   */
//  public static void startNetWorkSetting(Context context) {
//    Intent mIntent = new Intent(Settings.ACTION_SETTINGS);
//    // mIntent.setAction(Settings.ACTION_SETTINGS);
//    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//    // context.startActivity(intent);
//    // Intent mIntent = new Intent("/");
//    // ComponentName comp = new ComponentName(
//    // "com.android.settings",
//    // "com.android.settings.WirelessSettings");
//    // mIntent.setComponent(comp);
//    // mIntent.setAction("android.intent.action.VIEW");
//    // startActivityForResult(mIntent,0);
//
//    // Intent intent = new Intent();
//    // intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//    // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//    try {
//      context.startActivity(mIntent);
//    } catch (ActivityNotFoundException ex) {
//      // The Android SDK doc says that the location settings activity
//      // may not be found. In that case show the general settings.
//      // General settings activity
//      mIntent.setAction(Settings.ACTION_SETTINGS);
//      try {
//        context.startActivity(mIntent);
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    }
//
//  }
//
//  public static String getString(String str) {
//    if (TextUtils.isEmpty(str)) {
//      return "";
//    }
//    return str;
//  }
//
//  /**
//   * 浮点数相等比较
//   *
//   * @param value1
//   * @param value2
//   * @return
//   */
//  public static boolean floatCompare(float value1, float value2) {
//    return Math.abs(value1 - value2) < 0.00001;// 0.00001表示一个极小的数
//  }
//
//  /**
//   * 网络down掉后弹出对话框
//   */
//  public static void showDialogWhenNewWorkDown(final Activity context) {
//    // 网络良好，直接返回
//    if (CommonTools.isNetworkAvailable(context))
//      return;
//    // 网络Down了
//    if (!DialogUtil.getInstance().isDialogShowing()) {
//      DialogUtil.getInstance().createAlertDiaog(context, "哎呦，网络好像出问题了哦~ \n请检查网络设置", "去设置",
//          new DialogUtil.IPositiveButtonDialogListener() {
//
//            @Override
//            public void onPositiveButtonClicked(int requestCode) {
//              // 跳转到网络设置页面
//              CommonTools.startNetWorkSetting(context);
//            }
//          }, context.getResources().getString(R.string.choosecoupons_known));
//    }
//
//  }
//
//  public static void showDialogWhenFisrtInApp(final Activity context) {
//    if (showDialogNum == 0) {
//      showDialogNum = 1;
//      CommonTools.showDialogWhenNewWorkDown(context);
//    }
//  }
//
//  /**
//   * 跳转到发短信页面
//   */
//  public static void startMessageSendActivity(Context context, String msgBody) {
//    Intent it = new Intent(Intent.ACTION_VIEW);
//    it.putExtra("sms_body", msgBody);
//    it.setType("vnd.android-dir/mms-sms");
//    context.startActivity(it);
//  }
//
//  public static SpannableStringBuilder getFormatedSizeString(int breakingpoint, int size1,
//      int size2, String object) {
//    SpannableStringBuilder style = new SpannableStringBuilder(object);
//    if (TextUtils.isEmpty(object) || object.length() <= breakingpoint)
//      return style;
//    style.setSpan(new AbsoluteSizeSpan(size1), 0, breakingpoint, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//    style.setSpan(new AbsoluteSizeSpan(size2), breakingpoint, object.length(),
//            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//    return style;
//  }
//
//  public static SpannableStringBuilder getFormatedColorString(int breakingpoint, int color1,
//      int color2, String object) {
//    SpannableStringBuilder style = new SpannableStringBuilder(object);
//    if (TextUtils.isEmpty(object) || object.length() <= breakingpoint)
//      return style;
//    style.setSpan(new ForegroundColorSpan(color1), 0, breakingpoint,
//        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//    style.setSpan(new ForegroundColorSpan(color2), breakingpoint, object.length(),
//        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//    return style;
//  }
//
//  /**
//   * 设置置顶位置的字体大小及颜色，
//   *
//   * @param breakingpoint 两种字体大小和颜色的分割点
//   * @param color1 分割点前一部分的字体颜色
//   * @param color2 分割点后一部分的字体颜色
//   * @param size1 分割点前一部分的字体大小
//   * @param size2 分割点后一部分的字体大小
//   * @param object 一串字符；
//   * @return
//   */
//  public static SpannableStringBuilder getFormatedColorAndSizeString(int breakingpoint, int color1,
//      int color2, int size1, int size2, String object) {
//    SpannableStringBuilder style = new SpannableStringBuilder(object);
//    if (TextUtils.isEmpty(object) || object.length() <= breakingpoint)
//      return style;
//    style.setSpan(new ForegroundColorSpan(color1), 0, breakingpoint,
//        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//    style.setSpan(new ForegroundColorSpan(color2), breakingpoint, object.length(),
//        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//    style.setSpan(new AbsoluteSizeSpan(size1), 0, breakingpoint, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//    style.setSpan(new AbsoluteSizeSpan(size2), breakingpoint, object.length(),
//        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//    return style;
//  }
//
//  public static RequestLoadingDialog createRequestLoadingDialog(Context context) {
//    return createRequestLoadingDialog(context, "");
//  }
//
//  public static RequestLoadingDialog createRequestLoadingDialog(Context context, String loadingtext) {
//    // 创建之前先dismiss之前可能未dismiss的dialog
//    dissmissLoadingDialog();
//    mLoadingDialog = new RequestLoadingDialog(context);
//    try {
//      mLoadingDialog.stateToLoading(loadingtext);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return mLoadingDialog;
//  }
//
//  /**
//   * 充值页面dialog 只有文字 没有车轮
//   * @param context
//   * @param loadingtext
//   * @return
//   */
//  public static RequestLoadingDialog createRequestLoadingDialogForPay(Context context, String loadingtext){
// // 创建之前先dismiss之前可能未dismiss的dialog
//    dissmissLoadingDialog();
//    mLoadingDialog = new RequestLoadingDialog(context);
//    try {
//      mLoadingDialog.setVisibleProgressBar(false);
//      mLoadingDialog.setVisibleText(true);
//      mLoadingDialog.stateToLoading(loadingtext);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return mLoadingDialog;
//  }
//  public static void dissmissLoadingDialog() {
//    if (mLoadingDialog != null) {
//      mLoadingDialog.stateToNormal();
//    }
//  }
//
//  /**
//   * 截取字符串的函数
//   *
//   * @param str
//   * @param size
//   * @return
//   */
//  public static String StringCut(String str, int size) {
//    if (!TextUtils.isEmpty(str)) {
//      return str.length() > size ? str.substring(0, size) : str;
//    }
//    return "";
//  }
//
//  /**
//   * 检查应用是否安装
//   *
//   * @param context
//   * @param packageName
//   * @return
//   */
//  public static boolean checkApkExist(Context context, String packageName) {
//    ApplicationInfo applicationInfo = null;
//    if (TextUtils.isEmpty(packageName)) {
//      return false;
//    }
//
//    try {
//      applicationInfo =
//          context.getPackageManager().getApplicationInfo(packageName,
//              PackageManager.GET_UNINSTALLED_PACKAGES);
//    } catch (NameNotFoundException e) {
//      APPYOUMENG.errorLog(context, e);
//    }
//    if (applicationInfo != null) {
//      return true;
//    }
//    return false;
//  }
//
//  /**
//   * 检查某个文件是否存在
//   *
//   * @param path
//   * @return
//   */
//  public static boolean checkFileExist(String path) {
//    if (path == null)
//      return false;
//
//    return new File(path).exists();
//  }
//
//  /**
//   *
//   * @param dir 注意：目录格式“xx/xxx/xx”
//   * @return true 创建成功或已经存在; false创建失败
//   */
//  public static boolean createDirIfNotExist(String dir) {
//    File file = new File(dir);// /storage/emulated/0/wubasuyun/font
//    if (!file.exists()) {
//      return file.mkdirs();
//    }
//    return true;
//  }
//
//  // 将路径相连起来
//  public static String joinPath(String prefix, String suffix) {
//    int prefixLength = prefix.length();
//    boolean haveSlash = (prefixLength > 0 && prefix.charAt(prefixLength - 1) == File.separatorChar);
//    if (!haveSlash) {
//      haveSlash = (suffix.length() > 0 && suffix.charAt(0) == File.separatorChar);
//    }
//    return haveSlash ? (prefix + suffix) : (prefix + File.separatorChar + suffix);
//  }
//
//  /**
//   * 为HttpGet 的 url 方便的添加多个name value 参数。
//   *
//   * @param url
//   * @param params
//   * @return
//   */
//  public static String attachHttpGetParams(String url, List<BasicNameValuePair> params) {
//    return url + "?" + URLEncodedUtils.format(params, "UTF-8");
//  }
}
