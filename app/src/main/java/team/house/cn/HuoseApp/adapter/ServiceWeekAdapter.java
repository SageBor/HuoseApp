package team.house.cn.HuoseApp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.bean.ServiceWeekBean;

/**
 * Created by kn on 15/11/12.
 * 服务频率
 */
public class ServiceWeekAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ServiceWeekBean> mServiceWeekBeans;
    private List<ServiceWeekBean> mchooseServiceWeekBeans;
    public ServiceWeekAdapter(List<ServiceWeekBean> mSerivceWeekBean, Context context) {
        this.mServiceWeekBeans = mSerivceWeekBean;
        inflater = LayoutInflater.from(context);
        mchooseServiceWeekBeans = new ArrayList<ServiceWeekBean>();

    }
    public void addItems(List<ServiceWeekBean> mSerivceWeekBean) {
        mServiceWeekBeans = mSerivceWeekBean;
    }

    @Override
    public int getCount() {

        return mServiceWeekBeans.size();
    }

    @Override
    public Object getItem(int position) {

        return mServiceWeekBeans.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ToggleButton_ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_reservationservice, null);
            viewHolder = new ToggleButton_ViewHolder();
            viewHolder.mToggleButton = (ToggleButton) convertView.findViewById(R.id.btn_check);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ToggleButton_ViewHolder) convertView.getTag();
        }
        ServiceWeekBean serviceWeekBean = mServiceWeekBeans.get(position);
        viewHolder.mToggleButton.setText(serviceWeekBean.getWeek_name());
        viewHolder.mToggleButton.setTextOff(serviceWeekBean.getWeek_name());
        viewHolder.mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    addOrRemoveServiceWeekBean(position, true);
                } else {
                    addOrRemoveServiceWeekBean(position, false);
                }
            }
        });
        viewHolder.mToggleButton.setTextOn(serviceWeekBean.getWeek_name());
        return convertView;
    }

    private void addOrRemoveServiceWeekBean(int position, boolean isAdd) {
        boolean isIn = false;
        if (mchooseServiceWeekBeans.size() > 0){
            for (int i = 0; i < mchooseServiceWeekBeans.size(); i++) {
                if (mchooseServiceWeekBeans.get(i).getWeek_id() == mServiceWeekBeans.get(position).getWeek_id()){
                    isIn = true;
                    break;
                }
            }
        }
        if (isAdd) {
            if (!isIn){
                mchooseServiceWeekBeans.add(mServiceWeekBeans.get(position));
            }
        } else {
            if (isIn) {
                mchooseServiceWeekBeans.remove(mServiceWeekBeans.get(position));
            }
        }

    }
    public List<ServiceWeekBean> getCHooseServiceWeeksList () {

        return mchooseServiceWeekBeans;
    }
}
