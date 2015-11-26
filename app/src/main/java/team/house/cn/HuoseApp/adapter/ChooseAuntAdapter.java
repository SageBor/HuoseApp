package team.house.cn.HuoseApp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
    private Context mContext;
    public ChooseAuntAdapter(Context context, List<AuntBean> auntBeans){
        mContext = context;
        inflater = LayoutInflater.from(mContext);
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
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.iv_photo);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        setView(viewHolder, position);

        return convertView;
    }

    private void setView (ViewHolder viewHolder, int position) {
        AuntBean auntBean = auntBeanList.get(position);

        viewHolder.name.setText(auntBean.getTruename());
        viewHolder.mobile.setText(auntBean.getMobile());
        viewHolder.evaluate.setText(auntBean.getSeller_good_num() == 0 ? "暂无评价" : "好评" + auntBean.getSeller_good_num() + "次");
        viewHolder.num.setText("服务" + auntBean.getTake_num() +  "次");
        viewHolder.service.setText("技能:" + auntBean.getSkill_names());
        viewHolder.age.setText("年龄:" + auntBean.getTake_num() + "  " + auntBean.getHometown() + "人");
        loadDriverImg(viewHolder,auntBean.getUser_pic());
    }
    /**
     * 加载阿姨头像
     */
    private void loadDriverImg(ViewHolder holder, String src) {
        if (TextUtils.isEmpty(src) || src.indexOf("none.gif") > 0) {
            holder.photo.setImageResource(R.drawable.user);
            return;
        }
        Picasso.with(mContext)
                .load(src)
                .placeholder(R.drawable.user)
                .error(R.drawable.user)
                .tag(mContext)
                .into(holder.photo);
    }
    class ViewHolder {
        public TextView name;
        public TextView mobile;
        public TextView evaluate;
        public TextView num;
        public TextView service;
        public TextView age;
        public ImageView photo;
    }
}
