package team.house.cn.HuoseApp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.activity.ReservationServiceActivity;
import team.house.cn.HuoseApp.bean.AuntBean;
import team.house.cn.HuoseApp.bean.ServiceContentBean;

/**
 * Created by kn on 15/11/13.
 */
public class ChooseAuntAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<AuntBean> auntBeanList;
    private ReservationServiceActivity reservationServiceActivity;
    public ChooseAuntAdapter(Context context, List<AuntBean> auntBeans){
        inflater = LayoutInflater.from(context);
        auntBeanList = auntBeans;
    }
    public void addItems(List<AuntBean> auntBeans) {
        auntBeanList = auntBeans;
    }
    @Override
    public int getCount() {
        return auntBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return auntBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_aunt, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.txt_auntname);
            viewHolder.mobile = (TextView) convertView.findViewById(R.id.tv_mobile);
            viewHolder.evaluate = (TextView) convertView.findViewById(R.id.txt_auntscore);
            viewHolder.num = (TextView) convertView.findViewById(R.id.txt_service_time);
            viewHolder.service = (TextView) convertView.findViewById(R.id.tv_service);
            viewHolder.age = (TextView) convertView.findViewById(R.id.tv_age);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder.title.setText(pictures.get(position).getTitle());
//        viewHolder.image.setImageResource(pictures.get(position).getImageId());
        viewHolder.name.setText(auntBeanList.get(position).getTruename());
        viewHolder.mobile.setText(auntBeanList.get(position).getMobile());
        viewHolder.evaluate.setText(auntBeanList.get(position).getSeller_good_num() == 0 ? "暂无评价" : "好评" + auntBeanList.get(position).getSeller_good_num() + "次");
        viewHolder.num.setText("服务" + auntBeanList.get(position).getTake_num() +  "次");
        viewHolder.service.setText("技能:" + auntBeanList.get(position).getSkill_names());
        viewHolder.age.setText("年龄:" + auntBeanList.get(position).getTake_num() + "  " + auntBeanList.get(position).getHometown() + "人");

        return convertView;
    }
    class ViewHolder {
        public TextView name;
        public TextView mobile;
        public TextView evaluate;
        public TextView num;
        public TextView service;
        public TextView age;
    }
}
