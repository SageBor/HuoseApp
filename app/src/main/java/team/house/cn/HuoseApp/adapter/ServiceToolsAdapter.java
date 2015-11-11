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
import team.house.cn.HuoseApp.bean.ServiceModelBean;
import team.house.cn.HuoseApp.bean.ServiceToolsBean;

/**
 * Created by kn on 15/11/12.
 */
public class ServiceToolsAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ServiceToolsBean> mServiceToolsBeans;
    public ServiceToolsAdapter(List<ServiceToolsBean> mServiceToolsBeans, Context context) {
        this.mServiceToolsBeans = mServiceToolsBeans;
        inflater = LayoutInflater.from(context);

    }
    public void addItems(List<ServiceToolsBean> mServiceToolsBeans) {
        mServiceToolsBeans = mServiceToolsBeans;
    }
    @Override
    public int getCount() {
        return mServiceToolsBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mServiceToolsBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mServiceToolsBeans.get(position).getSupplies_id();
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
        ServiceToolsBean serviceToolsBean = mServiceToolsBeans.get(position);
        viewHolder.mToggleButton.setText(serviceToolsBean.getSupplies_name());
        viewHolder.mToggleButton.setTextOff(serviceToolsBean.getSupplies_name());
        viewHolder.mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                toggleButton.setChecked(isChecked);

            }
        });
        viewHolder.mToggleButton.setTextOn(serviceToolsBean.getSupplies_name());
        return convertView;
    }
}
