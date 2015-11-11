package team.house.cn.HuoseApp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.List;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.bean.ServiceWeekBean;

/**
 * Created by kn on 15/11/12.
 * 服务频率
 */
public class ServiceWeekAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ServiceWeekBean> mServiceWeekBeans;
    public ServiceWeekAdapter(List<ServiceWeekBean> mSerivceWeekBean, Context context) {
        this.mServiceWeekBeans = mSerivceWeekBean;
        inflater = LayoutInflater.from(context);

    }
    public void addItems(List<ServiceWeekBean> mSerivceWeekBean) {
        mServiceWeekBeans = mSerivceWeekBean;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
        viewHolder.mToggleButton.setTextOff(serviceWeekBean.getWeek_name());
        viewHolder.mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                toggleButton.setChecked(isChecked);

            }
        });
        viewHolder.mToggleButton.setTextOn(serviceWeekBean.getWeek_name());
        return convertView;
    }
}
