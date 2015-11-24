package team.house.cn.HuoseApp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.bean.ServiceModelBean;
import team.house.cn.HuoseApp.bean.ServiceToolsBean;
import team.house.cn.HuoseApp.bean.ServiceWeekBean;

/**
 * Created by kn on 15/11/12.
 */
public class ServiceToolsAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ServiceToolsBean> mServiceToolsBeans;

    private List<ServiceToolsBean> mchooseServiceToolsBeans;
    public ServiceToolsAdapter(List<ServiceToolsBean> mServiceToolsBeans, Context context) {
        this.mServiceToolsBeans = mServiceToolsBeans;
        inflater = LayoutInflater.from(context);
        mchooseServiceToolsBeans = new ArrayList<ServiceToolsBean>();

    }
    public void addItems(List<ServiceToolsBean> _mServiceToolsBeans) {
        mServiceToolsBeans = _mServiceToolsBeans;
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
        ServiceToolsBean serviceToolsBean = mServiceToolsBeans.get(position);
        viewHolder.mToggleButton.setText(serviceToolsBean.getSupplies_name());
        viewHolder.mToggleButton.setTextOff(serviceToolsBean.getSupplies_name());
        viewHolder.mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    addOrRemoveServiceToolBean(position, true);
                } else {
                    addOrRemoveServiceToolBean(position, false);
                }

            }
        });
        viewHolder.mToggleButton.setTextOn(serviceToolsBean.getSupplies_name());
        return convertView;
    }

    private void addOrRemoveServiceToolBean(int position, boolean isAdd) {
        boolean isIn = false;
        if (mchooseServiceToolsBeans.size() > 0){
            for (int i = 0; i < mchooseServiceToolsBeans.size(); i++) {
                if (mchooseServiceToolsBeans.get(i).getSupplies_id() == mServiceToolsBeans.get(position).getSupplies_id()){
                    isIn = true;
                    break;
                }
            }
        }
        if (isAdd) {
            if (!isIn){
                mchooseServiceToolsBeans.add(mServiceToolsBeans.get(position));
            }
        } else {
            if (isIn) {
                mchooseServiceToolsBeans.remove(mServiceToolsBeans.get(position));
            }
        }

    }
    public List<ServiceToolsBean> getCHooseServiceToolsList () {

        return mchooseServiceToolsBeans;
    }
}
