package team.house.cn.HuoseApp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import team.house.cn.HuoseApp.bean.AddressBean;
import team.house.cn.HuoseApp.bean.OrderBean;

/**
 * Created by kn on 15/11/17.
 */
public class OrderListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<OrderBean> orderBeanList;

    public OrderListAdapter(List<OrderBean> orderBeans, Context context) {
        super();
        orderBeanList = orderBeans;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return orderBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
