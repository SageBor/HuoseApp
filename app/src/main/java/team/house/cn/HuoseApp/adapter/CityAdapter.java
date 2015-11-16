package team.house.cn.HuoseApp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import team.house.cn.HuoseApp.bean.CityBean;
import team.house.cn.HuoseApp.bean.ProvinceBean;

/**
 * Created by kenan on 15/11/16.
 */
public class CityAdapter extends BaseAdapter implements SpinnerAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<CityBean> spinnerList;

    public CityAdapter(ArrayList<CityBean> spinnerList, Context _context) {
        this.spinnerList = spinnerList;
        mContext = _context;
//        inflater = LayoutInflater.from(mContext);
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return spinnerList.size();
    }

    @Override
    public Object getItem(int i) {
        return spinnerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView text;
        if (view != null){
            // Re-use the recycled view here!
            text = (TextView) view;
        } else {
            // No recycled view, inflate the "original" from the platform:
            // Inflate TextView
            text = (TextView) inflater.inflate(android.R.layout.simple_spinner_dropdown_item, viewGroup, false);
        }
        // Text customizations can go here or edit directly on the xml TextView layout
        text.setMinHeight(15);
        text.setTextColor(Color.BLACK);
        text.setText(spinnerList.get(i).getCityName());
        return text;
    }
}
