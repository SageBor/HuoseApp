package team.house.cn.HuoseApp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import team.house.cn.HuoseApp.R;
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

    public void setItems (List<OrderBean> orderBeans) {
        orderBeanList = orderBeans;
    }
    public void addItem (OrderBean orderBean) {
        if (orderBeanList != null) {
            orderBeanList.add(orderBean);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_order, null);
            viewHolder = new ViewHolder();
            viewHolder.publishTimeTextView = (TextView) convertView.findViewById(R.id.tv_ordertime);
            viewHolder.bigTypeTextView = (TextView) convertView.findViewById(R.id.tv_bigType);
            viewHolder.workTimeTextView = (TextView) convertView.findViewById(R.id.tv_serviceTime);
            viewHolder.baseMoneyTextViwe = (TextView) convertView.findViewById(R.id.tv_baseneyney);
            viewHolder.workAddressTextView = (TextView) convertView.findViewById(R.id.tv_serviceAddress);
            viewHolder.orderStatusTextView = (TextView) convertView.findViewById(R.id.tv_orderstatus);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder.title.setText(pictures.get(position).getTitle());
//        viewHolder.image.setImageResource(pictures.get(position).getImageId());
        viewHolder.publishTimeTextView.setText("发布时间:" + orderBeanList.get(position).getOn_time());
        viewHolder.bigTypeTextView.setText("雇主需求:" + orderBeanList.get(position).getIndus_pid());
        viewHolder.workTimeTextView.setText("服务时间:" + orderBeanList.get(position).getStart_time());
        viewHolder.baseMoneyTextViwe.setText("薪资:25元/小时");
        viewHolder.workAddressTextView.setText("服务地址:" + orderBeanList.get(position).getAddress());
        viewHolder.orderStatusTextView.setText("订单状态:" + orderBeanList.get(position).getTask_status_content());
        return convertView;
    }

    class ViewHolder {
        public TextView publishTimeTextView;
        public TextView bigTypeTextView;
        public TextView workTimeTextView;
        public TextView baseMoneyTextViwe;
        public TextView workAddressTextView;
        public TextView orderStatusTextView;
    }
}
