package team.house.cn.HuoseApp.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.bean.ChoosePriceBean;

/**
 * Created by kenan on 15/11/18.
 */
public class ChoosePriceAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ChoosePriceBean> choosePriceBeanList;
    private Context mContext;
    public ChoosePriceAdapter(Context context, List<ChoosePriceBean> choosePriceBeans){
        inflater = LayoutInflater.from(context);
        choosePriceBeanList = choosePriceBeans;
        mContext = context;
    }
    @Override
    public int getCount() {
        return choosePriceBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return choosePriceBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItems(List<ChoosePriceBean> choosePriceBeans) {
        choosePriceBeanList = choosePriceBeans;
    }
    public List<ChoosePriceBean> getAllItems(){
        return choosePriceBeanList;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_chooseprice, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.iv_aroud = (ImageView) convertView.findViewById(R.id.iv_aroud);
            viewHolder.tv_aroud_line = (TextView) convertView.findViewById(R.id.tv_aroud_line);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        setView(i, viewHolder);
        return convertView;
    }

    private void setView(int position, ViewHolder viewHolder) {
        ChoosePriceBean choosePriceBean = choosePriceBeanList.get(position);
        viewHolder.tv_price.setText(choosePriceBean.getPrice_name());
        if (choosePriceBean.isSelelct()) {
            viewHolder.iv_aroud.setBackgroundResource(R.drawable.bg_blue_round_shape);
            viewHolder.tv_aroud_line.setBackgroundResource(R.color.c07c6ef);
        } else {
            viewHolder.iv_aroud.setBackgroundResource(R.drawable.bg_gray_round_shape);
            viewHolder.tv_aroud_line.setBackgroundResource(R.color.c7f7f7f);
        }
        if (position == 0) {
            viewHolder.tv_aroud_line.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.tv_aroud_line.setVisibility(View.VISIBLE);
        }
    }

    class ViewHolder {
        public TextView tv_price;
        public ImageView iv_aroud;
        public TextView tv_aroud_line;

    }
}
