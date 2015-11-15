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
import team.house.cn.HuoseApp.activity.ReservationServiceActivity;
import team.house.cn.HuoseApp.bean.ServiceModelBean;
import team.house.cn.HuoseApp.bean.ServiceWeekBean;

/**
 * Created by kn on 15/11/12.
 */
public class ServiceModelAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private List<ServiceModelBean> mServiceModelBeans;
    private ReservationServiceActivity reservationServiceActivity;
    public ServiceModelAdapter(List<ServiceModelBean> mServiceModelBeans, ReservationServiceActivity context) {
        this.mServiceModelBeans = mServiceModelBeans;
        inflater = LayoutInflater.from(context);
        reservationServiceActivity = context;

    }
    public void addItems(List<ServiceModelBean> _mServiceModelBeans) {
        mServiceModelBeans = _mServiceModelBeans;
    }
    @Override
    public int getCount() {
        return mServiceModelBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mServiceModelBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mServiceModelBeans.get(position).getEmployment_typ();
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
        ServiceModelBean serviceModelBean = mServiceModelBeans.get(position);
        viewHolder.mToggleButton.setText(serviceModelBean.getEmployment_typ_name());
        viewHolder.mToggleButton.setTextOff(serviceModelBean.getEmployment_typ_name());
        viewHolder.mToggleButton.setChecked(serviceModelBean.isChecked());
        viewHolder.mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    updateServiceContent(position);
                    reservationServiceActivity.serviceModel_ItemChecked(mServiceModelBeans.get(position));
                }
            }
        });
        viewHolder.mToggleButton.setTextOn(serviceModelBean.getEmployment_typ_name());
        return convertView;
    }
    private void updateServiceContent (int mPosition){

        for (int j = 0; j < mServiceModelBeans.size(); j++){
            if (j != mPosition) {
                mServiceModelBeans.get(j).setIsChecked(false);
            } else {
                mServiceModelBeans.get(j).setIsChecked(true);
            }

        }
        this.notifyDataSetChanged();
    }
}
