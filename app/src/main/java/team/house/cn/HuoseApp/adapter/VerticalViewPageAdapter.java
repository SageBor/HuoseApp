package team.house.cn.HuoseApp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.text.Html;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.activity.MainActivity;
import team.house.cn.HuoseApp.activity.WebActivity;
import team.house.cn.HuoseApp.bean.NoticeBean;
import team.house.cn.HuoseApp.bean.WebBundleBean;
import team.house.cn.HuoseApp.utils.Spanny;

public class VerticalViewPageAdapter extends PagerAdapter {

    private final LayoutInflater inflater;
    private List<NoticeBean> mData;
    private Context context;
    public VerticalViewPageAdapter(Context _context){
        this.context = _context;
        inflater = LayoutInflater.from(context);
        mData = new ArrayList<NoticeBean>();
    }
    @Override
    public int getCount() {
        return mData.size();
    }
    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View)object);
    }

    public void setData (List<NoticeBean> datas) {
        mData = datas;
        this.notifyDataSetChanged();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View convertView = inflater.inflate(R.layout.item_viewpage, null);
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        Spanny spanny = new Spanny(mData.get(position).getArt_title()).append("详情").append("请点击",new UnderlineSpan(),new ForegroundColorSpan(0xFFE6454A));
        title.setText(spanny);
//        title.setText(mData.get(position).getArt_title() + Html.fromHtml("<a href=\"http://blog.csdn.net/CAIYUNFREEDOM\">请点击</a>"));
//        txtCodeUnreceived.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity)context;
                Intent intent = new Intent(mainActivity, WebActivity.class);
                intent.putExtra(WebBundleBean.WEB_SERIALIZABLE_KEY, new WebBundleBean(mData.get(position).getArt_title(), mData.get(position).getArt_source()));
                mainActivity.startActivity(intent);
            }
        });
        view.addView(convertView);
        return convertView;
    }
}


