package team.house.cn.HuoseApp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.activity.CityListActivity;
import team.house.cn.HuoseApp.bean.CityBean;
import team.house.cn.HuoseApp.bean.ProvinceBean;
import team.house.cn.HuoseApp.constans.AppConfig;
import team.house.cn.HuoseApp.utils.CityUtil;
import team.house.cn.HuoseApp.views.ExpandableLayoutListView.ExpandableLayoutItem;
import team.house.cn.HuoseApp.views.ScrollGridView;

/**
 * Created by kn on 15/11/25.
 * 首页城市列表adapter
 */
public class ProvinceListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ProvinceBean> provinceBeanList;

    private List<CityBean> allCityList;
    public ProvinceListAdapter(List<ProvinceBean> provinceBeans, List<CityBean> cityBeans, Context _context) {
        provinceBeanList = provinceBeans;
        allCityList = cityBeans;
        mContext = _context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return provinceBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return provinceBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return provinceBeanList.get(position).getPro_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_row, null);
            viewHolder = new ViewHolder();
            viewHolder.header_text = (TextView) convertView.findViewById(R.id.header_text);
            viewHolder.gv_citycontent = (ScrollGridView) convertView.findViewById(R.id.gv_citycontent);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        setView(position, viewHolder);
        return convertView;
    }
    private void setView(int position, ViewHolder viewHolder) {
        ProvinceBean provinceBean = provinceBeanList.get(position);
        viewHolder.header_text.setText(provinceBean.getPro_name());
        final List<CityBean> cityBeans = getCityBean(provinceBean.getPro_id());
        if (cityBeans.size() > 0) {
            CityAdapter cityAdapter = new CityAdapter(mContext, cityBeans);
            viewHolder.gv_citycontent.setAdapter(cityAdapter);
        }

    }
    private List<CityBean> getCityBean(int pro_id) {
        List<CityBean> cityBeans = new ArrayList<CityBean>();
        for (CityBean cityBean : allCityList) {
            if (pro_id == cityBean.getProvinceId()) {
                cityBeans.add(cityBean);
            }

        }
        return cityBeans;
    }
    class ViewHolder {
        public ExpandableLayoutItem expandableLayoutItem;
        public TextView header_text;
//        public ListView lv_citycontent;
        public ScrollGridView  gv_citycontent;

    }

    private class CityAdapter extends BaseAdapter {
        private List<CityBean> cityList = new ArrayList<CityBean>();
        private LayoutInflater mInflater;
        private Context context;

        public CityAdapter (Context _context, List<CityBean> cityList) {

            this.context = _context;
            this.cityList = cityList;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return cityList.size();
        }

        @Override
        public Object getItem(int i) {
            return cityList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = mInflater.inflate(R.layout.item_city, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_name);
            tv.setText(cityList.get(i).getCityName());
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CityBean cityBean = cityList.get(i);
                    CityUtil.saveCity(cityBean.getProvinceId(), cityBean.getCityId(), cityBean.getCityName(), AppConfig.Preference_ChooseCityNameFromService);
                    ((CityListActivity)context).finish();
                }
            });
            return view;
        }
    }
}
