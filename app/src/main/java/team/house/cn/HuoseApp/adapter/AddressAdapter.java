package team.house.cn.HuoseApp.adapter;

import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.bean.AddressBean;

/**
 * Created by kenan on 15/11/8.
 */
public class AddressAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<AddressBean> mAddressList;

    public AddressAdapter(List<AddressBean> _mAddressList, Context context) {
        super();
        mAddressList = _mAddressList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        mAddressList.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        mAddressList.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        AddressBean addressBean= mAddressList.get(i);
        return i;
    }

    public void addItem(AddressBean bean){
        mAddressList.add(bean);
    }
    public void addItems(List<AddressBean> beans){
        mAddressList = beans;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_address, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_item_address);
//            viewHolder.image = (ImageView) convertView.findViewById(R.id.iv_service);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(mAddressList.get(i).getmAddress());
        return convertView;
    }
    class ViewHolder {
        public TextView title;
        public ImageView image;
    }
}
