package team.house.cn.HuoseApp.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import team.house.cn.HuoseApp.constans.AppConfig;

// 源码库：https://github.com/norbsoft/android-typeface-helper

/**
 * Helper class for setting custom typefaces to {@link android.view.View} and
 * {@link android.view.ViewGroup}. Supports String styling using
 * {@link android.text.SpannableString}
 *
 * Applies typeface to all child views recursively.
 *
 * For detailed information and guide: https://github.com/jskierbi/android-typeface-helper
 *
 * @author Jakub Skierbiszewski
 */
public class TypefaceHelper {

  private static/* volatile */TypefaceCollection sDefaultTypefaceCollection = null;
  public static final String fontDir = "font";
  public static final String fontName = "HYQiH2312F45.ttf";

  /**
   * Initialize helper with typeface collection. This should be called inside
   * {@link android.app.Application#onCreate()}
   *
   * @param typefaceCollection
   */
  public static void init(TypefaceCollection typefaceCollection) {
    sDefaultTypefaceCollection = typefaceCollection;
  }

  public static TypefaceCollection getTypefaceCollection() {

    if (sDefaultTypefaceCollection == null) {
      throw new IllegalStateException(
          "Default typeface collection not initialized. Forgot to call init()?");
    }
    return sDefaultTypefaceCollection;
  }

  /**
   * 是否初始化了
   *
   * @return
   */
  public static boolean isInited() {
    return sDefaultTypefaceCollection != null;
  }

  /**
   * Return spannable string with default typeface style (style: Typeface.NORMAL) see:
   * http://stackoverflow.com/questions/8607707/how-to-set-a-custom-font-in-the-actionbar-title
   *
   * @param context to obtain string resource
   * @param strResId string resource id, content
   *
   * @throws IllegalStateException {@link #init(TypefaceCollection)} has not been called
   * @return SpannableString that can be used in TextView.setText() method
   */
  public static SpannableString typeface(Context context, int strResId) {
    // commented out by xiayong ,using default typeface rather than crashing !
    /*
     * if (sDefaultTypefaceCollection == null) { throw new IllegalStateException(
     * "Default typeface collection not initialized. Forgot to call init()?"); }
     */
    return typeface(context.getString(strResId), sDefaultTypefaceCollection, Typeface.NORMAL);
  }

  /**
   * Return spannable string with typeface in certain style see:
   * http://stackoverflow.com/questions/8607707/how-to-set-a-custom-font-in-the-actionbar-title
   *
   * @param context to obtain string resource
   * @param strResId string resource id, content
   * @param style Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC or Typeface.BOLD_ITALIC
   *
   * @throws IllegalStateException {@link #init(TypefaceCollection)} has not been calledC
   * @return SpannableString that can be used in TextView.setText() method
   */
  public static SpannableString typeface(Context context, int strResId, int style) {
    /*
     * if (sDefaultTypefaceCollection == null) { throw new IllegalStateException(
     * "Default typeface collection not initialized. Forgot to call init()?"); }
     */
    return typeface(context.getString(strResId), sDefaultTypefaceCollection, style);
  }

  /**
   * Return spannable string with typeface in certain style see:
   * http://stackoverflow.com/questions/8607707/how-to-set-a-custom-font-in-the-actionbar-title
   *
   * @param context to obtain string resource
   * @param strResId string resource id, content
   * @param collection TypefaceCollection instance
   * @param style Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC or Typeface.BOLD_ITALIC
   *
   * @return SpannableString that can be used in TextView.setText() method
   */
  public static SpannableString typeface(Context context, int strResId,
      TypefaceCollection collection, int style) {
    return typeface(context.getString(strResId), collection, style);
  }

  /**
   * Return spannable string with default typeface style (style: Typeface.NORMAL) see:
   * http://stackoverflow.com/questions/8607707/how-to-set-a-custom-font-in-the-actionbar-title
   *
   * @param sequence to typeface typeface to
   *
   * @throws IllegalStateException {@link #init(TypefaceCollection)} has not been called
   * @return SpannableString that can be used in TextView.setText() method
   */
  public static SpannableString typeface(CharSequence sequence) {
    // commented out by xiayong ,using default typeface rather than crashing !
    /*
     * if (sDefaultTypefaceCollection == null) { throw new IllegalStateException(
     * "Default typeface collection not initialized. Forgot to call init()?"); }
     */
    return typeface(sequence, sDefaultTypefaceCollection, Typeface.NORMAL);
  }

  /**
   * Return spannable string with applied typeface in certain style see:
   * http://stackoverflow.com/questions/8607707/how-to-set-a-custom-font-in-the-actionbar-title
   *
   * @param sequence content
   * @param typefaceCollection Collection of typefaces
   * @param style Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC or Typeface.BOLD_ITALIC
   * @throws IllegalArgumentException when invalid style is passed
   * @return SpannableString that can be used in TextView.setText() method
   */
  public static SpannableString typeface(CharSequence sequence,
      TypefaceCollection typefaceCollection, int style) {
    checkTypefaceStyleThrowing(style);
    SpannableString s = new SpannableString(sequence);
    if (typefaceCollection != null) {
      s.setSpan(new TypefaceSpan(typefaceCollection.getTypeface(style)), 0, s.length(),
          Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    return s;
  }

  /**
   * Return spannable string with default typeface style (style: Typeface.NORMAL) see:
   * http://stackoverflow.com/questions/8607707/how-to-set-a-custom-font-in-the-actionbar-title
   *
   * @param sequence content
   * @param typefaceCollection Collection of typefaces
   * @return SpannableString that can be used in TextView.setText() method
   */
  public static SpannableString typeface(CharSequence sequence,
      TypefaceCollection typefaceCollection) {
    return typeface(sequence, typefaceCollection, Typeface.NORMAL);
  }

  /**
   * Apply typefaces to main acitivty view (android.R.id.content).
   *
   * @throws IllegalStateException {@link #init(TypefaceCollection)} has not been called
   * @param activity to typeface custom typefaces to
   */
  public static void typeface(Activity activity) {
    // commented out by xiayong ,using default typeface rather than crashing !
    /*
     * if (sDefaultTypefaceCollection == null) { throw new IllegalStateException(
     * "Default typeface collection not initialized. Forgot to call init()?"); }
     */
    if (sDefaultTypefaceCollection != null) {
      typeface(activity.findViewById(android.R.id.content), sDefaultTypefaceCollection);// android.R.id.content
                                                                                        // find the
                                                                                        // root view
                                                                                        // attached
                                                                                        // to this
                                                                                        // activity
    }
  }

  /**
   * Apply typefaces to view
   *
   * @throws IllegalStateException {@link #init(TypefaceCollection)} has not been called
   * @param view to typeface custom typefaces to
   */
  public static void typeface(View view) {
    // commented out by xiayong ,using default typeface rather than crashing !
    /*
     * if (sDefaultTypefaceCollection == null) { throw new IllegalStateException(
     * "Default typeface collection not initialized. Forgot to call init()?"); }
     */
    if (sDefaultTypefaceCollection != null) {
      typeface(view, sDefaultTypefaceCollection);
    }
  }

  /**
   * Apply typefaces to main acitivty view (android.R.id.content).
   *
   * @param activity to typeface custom typefaces to
   * @param typefaceCollection to obtain typefaces SparseArray.
   */
  public static void typeface(Activity activity, TypefaceCollection typefaceCollection) {
    typeface(activity.findViewById(android.R.id.content), typefaceCollection);
  }

  /**
   * Apply fonts from map to all children of view (or view itself)
   *
   * @param view view for which to typeface fonts
   * @param typefaceCollection Collection of typefaces
   */
  public static void typeface(View view, TypefaceCollection typefaceCollection) {

    if (view instanceof ViewGroup) {
      applyTypeface((ViewGroup) view, typefaceCollection);
    } else {
      applyForView(view, typefaceCollection);
    }
  }

  /**
   * 检查是否存在字体文件
   *
   * @return
   */
  public static boolean existTypeface(String fontNameWithPath) {
    // String fontNameWithPath = getTypefacePath() +File.separator+ TypefaceHelper.fontName;//
    // 例如：/storage/emulated/0/wubasuyun/font/HYQiH18030F45.ttf
    return CommonTools.checkFileExist(fontNameWithPath);
  }

  /**
   * 获取字体存储的路径
   *
   * @return
   */
  public static String getTypefacePath(Context context) {

    if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
      /*
       * return Environment.getExternalStorageDirectory().toString() + File.separator +
       * APPConfig.AppRootDir + File.separator + TypefaceHelper.fontDir;
       */
      File sdFile = Environment.getExternalStorageDirectory();
      /*
       * String sdFilestr = sdFile.getPath();///storage/emulated/0 sdFilestr = sdFile.toString();//
       * /storage/emulated/0 sdFilestr = sdFile.getAbsolutePath();
       *
       * File inFile = context.getFilesDir();// /data/data/com.wuba.huoyun/files : 应用在内部存储的根目录
       * String inFilestr = inFile.getAbsolutePath(); inFilestr = inFile.toString(); inFilestr =
       * inFile.getPath();
       */
      String fullPath =
          CommonTools.joinPath(sdFile.toString(), AppConfig.AppRootDir + File.separator
              + TypefaceHelper.fontDir);// /storage/emulated/0/wubasuyun/font
      return fullPath;
    } else {
      String fullPath =
          CommonTools.joinPath(context.getFilesDir().toString(), TypefaceHelper.fontDir);
      return fullPath;
    }
  }

  /**
   * 获取字体库所在的全路径名称
   *
   * @param context
   * @return
   */
  public static String getTypefaceFullName(Context context) {
    return getTypefacePath(context) + File.separator + TypefaceHelper.fontName;
  }

  /**
   * 将字体文件转化成二进制流
   *
   * @param context
   * @return
   */
  public static byte[] readFontFiles(Context context) {
    byte[] buffer = null;
    String assetFontPath = "fonts";
    try {
      String assetFileNames[] = context.getAssets().list(assetFontPath);// asset中所有字体文件
      for (int i = 0; i < assetFileNames.length; i++) {
        // if (TypefaceHelper.fontName.equals(assetFileNames[i])) {
        if ("HYQiH.ttf".equals(assetFileNames[i])) {
          InputStream in = null;
          try {
            in = context.getAssets().open(assetFontPath + File.separator + assetFileNames[i]);// font/HYQiH18030F45.ttf
            buffer = new byte[in.available()];// TODO 存在oom的风险吗？in.available() == 496772
                                              // 即需要496772byte 约0.47M内存
            in.read(buffer);
          } catch (IOException e) {
            e.printStackTrace();
          } finally {
            if (in != null) {
              in.close();
            }
          }
          break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return buffer;
  }

  /**
   * Check if typeface style int is one of:
   * <ul>
   * <li>{@link android.graphics.Typeface#NORMAL}</li>
   * <li>{@link android.graphics.Typeface#BOLD}</li>
   * <li>{@link android.graphics.Typeface#ITALIC}</li>
   * <li>{@link android.graphics.Typeface#BOLD_ITALIC}</li>
   * </ul>
   * 
   * @param style
   */
  private static void checkTypefaceStyleThrowing(int style) {
    switch (style) {
      case Typeface.NORMAL:
      case Typeface.BOLD:
      case Typeface.ITALIC:
      case Typeface.BOLD_ITALIC:
        break;
      default:
        throw new IllegalArgumentException(
            "Style have to be in (Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC, Typeface.BOLD_ITALIC)");
    }
  }

  /**
   * Apply typeface to all ViewGroup childs
   * 
   * @param viewGroup to typeface typeface
   * @param typefaceCollection typeface collection
   */
  private static void applyTypeface(ViewGroup viewGroup, TypefaceCollection typefaceCollection) {

    for (int i = 0; i < viewGroup.getChildCount(); i++) {
      View childView = viewGroup.getChildAt(i);
      if (childView instanceof ViewGroup) {
        applyTypeface((ViewGroup) childView, typefaceCollection);
      } else {
        applyForView(childView, typefaceCollection);
      }
    }
  }

  /**
   * Apply typeface to single view
   * 
   * @param view to typeface typeface
   * @param typefaceCollection typeface collection
   */
  private static void applyForView(View view, TypefaceCollection typefaceCollection) {

    if (view instanceof TextView) {
      TextView textView = (TextView) view;
      Typeface oldTypeface = textView.getTypeface();
      final int style = oldTypeface == null ? Typeface.NORMAL : oldTypeface.getStyle();
      textView.setTypeface(typefaceCollection.getTypeface(style));
      textView.setPaintFlags(textView.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
  }

  private static class TypefaceSpan extends MetricAffectingSpan {

    Typeface typeface;

    TypefaceSpan(Typeface typeface) {
      this.typeface = typeface;
    }

    @Override
    public void updateMeasureState(TextPaint p) {
      p.setTypeface(typeface);
      p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
      tp.setTypeface(typeface);
      tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
  }
}
