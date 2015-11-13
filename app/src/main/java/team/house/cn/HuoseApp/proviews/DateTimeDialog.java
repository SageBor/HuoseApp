package team.house.cn.HuoseApp.proviews;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.bean.HourBean;
import team.house.cn.HuoseApp.bean.ServiceEmploymentMonthBean;
import team.house.cn.HuoseApp.bean.ServiceTryDayBean;
import team.house.cn.HuoseApp.utils.CommonTools;
import team.house.cn.HuoseApp.utils.TypefaceHelper;
import team.house.cn.HuoseApp.widget.ArrayWheelAdapter;
import team.house.cn.HuoseApp.widget.OnWheelChangedListener;
import team.house.cn.HuoseApp.widget.WuBaNumberPicker;

/**
 * Created by jiazhaoyang modified by xiayong
 */
public class DateTimeDialog {

    private Context mContext;
    private Dialog dialog;
    private DateTimeChange dateTimeChange;

    private Map<String, Date> wheelDays = new LinkedHashMap<String, Date>();

    private Map<String, String> wheelHours = new LinkedHashMap<String, String>();
    private Map<String, String> wheelMinutes = new LinkedHashMap<String, String>();
    private static final int offset = 30;// 间隔时间，30min
    private static final int DEFAULT_COUNT = 6;// 默认有多少项（“立即用车”+天数）
    private static final int HOURS_COUNT = 24;
    private static final int MINUTES_COUNT = 4;
    private List<HourBean> mHourContent;
    private List<ServiceTryDayBean> mTryDayContent;
    private List<ServiceEmploymentMonthBean> mMonthContent;
    private int mType;//型  0 - hour 1 - 试用天数 2 - 雇用时间 一个月

    /**
     * @param context
     * @param mContent 展示内容集合
     *
     */
    public DateTimeDialog(Context context, List<HourBean> mContent) {
        this.mContext = context;
        this.mHourContent = mContent;
        mType = 0;
    }
    /**
     * @param context
     * @param mContent 展示内容集合
     */
    public DateTimeDialog(Context context, List<ServiceTryDayBean> mContent, String tag) {
        this.mContext = context;
        this.mTryDayContent = mContent;
        mType = 1;
    }
    /**
     * @param context
     * @param mContent 展示内容集合
     */
    public DateTimeDialog(Context context, List<ServiceEmploymentMonthBean> mContent, int tag) {
        this.mContext = context;
        this.mMonthContent = mContent;
        mType = 2;
    }

    private void initWheelData() {

        Calendar calendar = Calendar.getInstance();

    /*
     * if (days <= 0) days = 5;
     */

        for (int i = 0; i < DEFAULT_COUNT; i++) {
            String day =
                    (String) DateFormat.format(mContext.getResources().getString(R.string.np_format_date),
                            calendar.getTime());
            day = day.replace("星期", "周");
            wheelDays.put(day, calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
//      }
        }

        switch (mType) {
            case 0:
                for (int i = 0; i < mHourContent.size(); i++) {
                    HourBean hourBean = (HourBean) mHourContent.get(i);
//          String hour = String.format("%02d", i);
                    wheelHours.put(hourBean.getHour_name(), hourBean.getHour() + "");
                }
                break;
            case 1:
                for (int i = 0; i < mTryDayContent.size(); i++) {
                    ServiceTryDayBean hourBean = (ServiceTryDayBean) mTryDayContent.get(i);
//          String hour = String.format("%02d", i);
                    wheelHours.put(hourBean.getTry_days_name(), hourBean.getTry_days() + "");
                }
                break;
            case 2:
                for (int i = 0; i < mMonthContent.size(); i++) {
                    ServiceEmploymentMonthBean monthBean = (ServiceEmploymentMonthBean) mMonthContent.get(i);
//          String hour = String.format("%02d", i);
                    wheelHours.put(monthBean.getEmployment_month_name(), monthBean.getEmployment_month() + "");
                }
                break;
        }
//    for (int i = 0; i < mContent.size(); i++) {
//      String hour = String.format("%02d", i);
//      wheelHours.put(hour + "时", hour);
//    }
//
//    for (int i = 0; i < MINUTES_COUNT; i++) {
//      String min = String.format("%02d", i * 15);
//      wheelMinutes.put(min + "分", min);
//    }

    }

    public void init() {

        initWheelData();// 初始化 wheelDays,wheelHours,wheelMinutes

        dialog = new Dialog(mContext, R.style.MyDialog);
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popmenu, null);
        TypefaceHelper.typeface(view);

        final WuBaNumberPicker wv_day = (WuBaNumberPicker) view.findViewById(R.id.wvMonth);
        final WuBaNumberPicker wv_hour = (WuBaNumberPicker) view.findViewById(R.id.wvHour);
        final WuBaNumberPicker wv_Minute = (WuBaNumberPicker) view.findViewById(R.id.wvMinute);
        wv_hour.setVisibility(View.VISIBLE);

        switch (mType) {
            case 0:
                wv_Minute.setVisibility(View.GONE);
                break;
            case 1:

            case 2:
                wv_day.setVisibility(View.GONE);
                break;
        }

        /** 月份*天 **/
        wv_day.setAdapter(new ArrayWheelAdapter<String>(wheelDays.keySet().toArray(new String[0]),
                wheelDays.size()));
        wv_day.TEXT_SIZE = CommonTools.dip2px(mContext, 20f);
        wv_day.setCurrentItem(0);// 默认选择：立即用车

        // wv_hour.setVisibility(View.GONE);
        // wv_Minute.setVisibility(View.GONE);
        wv_day.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WuBaNumberPicker wheel, int oldValue, int newValue) {
//        int[] minIndex = getMinDHM(offset);
//        if (newValue == 0) {// 立即用车
//          wv_hour.setVisibility(View.GONE);
//          wv_Minute.setVisibility(View.GONE);
//          return;
//        }
//        if (oldValue == 0) {
//          // 表示由“立即用車”轉換過來的
//          wv_hour.setVisibility(View.VISIBLE);
//          wv_Minute.setVisibility(View.VISIBLE);
//        }
                // 前提：newValue !=0
//        if (newValue <= minIndex[0]) {
//          wv_day.setCurrentItem(minIndex[0]);
//          wv_hour.setCurrentItem(minIndex[1]);
//          wv_Minute.setCurrentItem(minIndex[2]);
//        } else {
//          wv_hour.setCurrentItem(0);
//          wv_Minute.setCurrentItem(0);
//        }
            }
        });

        /** 小时 **/
        wv_hour.setAdapter(new ArrayWheelAdapter<String>(wheelHours.keySet().toArray(new String[0]),
                wheelHours.size()));
        wv_hour.TEXT_SIZE = CommonTools.dip2px(mContext, 20f);
        wv_hour.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WuBaNumberPicker wheel, int oldValue, int newValue) {
//        int[] minIndex = getMinDHM(offset);
//        if (wv_day.getCurrentItem() == minIndex[0]) {
//          if (newValue <= minIndex[1]) {
//            wv_hour.setCurrentItem(minIndex[1]);
//            if (wv_Minute.getCurrentItem() < minIndex[2])
//              wv_Minute.setCurrentItem(minIndex[2]);
//          }
//        }
            }
        });

        /** 分钟 **/
        wv_Minute.setAdapter(new ArrayWheelAdapter<String>(
                wheelMinutes.keySet().toArray(new String[0]), wheelMinutes.size()));
        wv_Minute.TEXT_SIZE = CommonTools.dip2px(mContext, 20f);
        wv_Minute.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WuBaNumberPicker wheel, int oldValue, int newValue) {
                int[] minIndex = getMinDHM(offset);
                if (wv_day.getCurrentItem() == minIndex[0] && wv_hour.getCurrentItem() == minIndex[1]) {
                    if (newValue <= minIndex[2])
                        wv_Minute.setCurrentItem(minIndex[2]);
                }
            }
        });

        Button btn_sure = (Button) view.findViewById(R.id.btnOK);
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String dayKey = wv_day.getAdapter().getItem(wv_day.getCurrentItem());
                String hourKey = wv_hour.getAdapter().getItem(wv_hour.getCurrentItem());

                String date = DateFormat.format("yyyy-MM-dd", wheelDays.get(dayKey)).toString();
                String time = wheelHours.get(hourKey);
                dateTimeChange.onDateTimeChange(date, time, wv_day.getCurrentItem(), wv_hour.getCurrentItem());
                dialog.dismiss();
            }
        });

        Button btn_cancel = (Button) view.findViewById(R.id.btnCancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });

    /*
     * int[] minIndex = getMinDHM(offset); wv_day.setCurrentItem(minIndex[0]);
     * wv_hour.setCurrentItem(minIndex[1]); wv_Minute.setCurrentItem(minIndex[2]);
     */

        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.popuStyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        Activity mActivity = (Activity) mContext;
        wl.width = mActivity.getWindowManager().getDefaultDisplay().getWidth();
        window.setGravity(Gravity.BOTTOM);
        dialog.onWindowAttributesChanged(wl);
        dialog.setCanceledOnTouchOutside(true);

        dialog.show();
    }

    /**
     * 获取距当前offset分钟后的最小Day，Hour和Minute的最小item
     *
     * @param offset
     * @return
     */
    private int[] getMinDHM(int offset) {
        int[] minIndex = new int[3];
        Calendar currCalAddOffset = Calendar.getInstance();// 当前时间
//    currCalAddOffset.add(Calendar.MINUTE, offset);
//    if (currCalAddOffset.get(Calendar.MINUTE) > 45) {// 仅限offset单位是分钟时，原因：在“分钟”里最大值是45，也就是说超过45min的就应该进位，不然表示不了
//      currCalAddOffset.add(Calendar.HOUR_OF_DAY, 1);
//      currCalAddOffset.set(Calendar.MINUTE, 0);
//    }
        minIndex[0] = isTomorrow(currCalAddOffset) ? 2 : 1;
        minIndex[1] = currCalAddOffset.get(Calendar.HOUR_OF_DAY);// 24小时制
        minIndex[2] = (int) Math.ceil((double) currCalAddOffset.get(Calendar.MINUTE) / 15) % 4;
        return minIndex;
    }

    private boolean isTomorrow(Calendar calen) {
        if (calen == null)
            return false;
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        return calen.get(Calendar.DAY_OF_MONTH) == tomorrow.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @param dateTimeChanged
     */
    public void setOnDateTimeChanged(DateTimeChange dateTimeChanged) {
        this.dateTimeChange = dateTimeChanged;

    }

    public interface DateTimeChange {
        /**
         * @param
         */
        public void onDateTimeChange(String date, String time, int dayItem, int hourItem);
    }
}
