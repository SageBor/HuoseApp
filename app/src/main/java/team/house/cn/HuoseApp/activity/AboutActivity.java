package team.house.cn.HuoseApp.activity;

import android.view.View;

import android.text.Html;
import android.widget.TextView;
import team.house.cn.HuoseApp.R;

/**
 * Created by kn on 15/11/14.
 */
public class AboutActivity extends BaseActivity{
    private TextView tv_about;
    @Override
    protected void initView() {
        super.initView();
        this.setContentView(R.layout.activity_about);
        tv_about=(TextView)findViewById(R.id.tv_about);
    }

    @Override
    protected void initData() {
        super.initData();
        tv_about.setText(Html.fromHtml(text()));
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    protected void onClickListener(View v) {
        super.onClickListener(v);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleView.setText("用户中心");
        mCityView.setVisibility(View.GONE);
        mLeftView.setVisibility(View.VISIBLE);
        mRightView.setVisibility(View.GONE);
    }
    private String text(){
        return " <span>    长春市天庭家政服务有限公司成立于2013年6月8日，注册资金1510万元，"+
                "坐落于长春市朝阳区普庆路169号天庭大厦，属于有限责任公司。强有力的平台研发团队50余人，"+
                "居家保洁、保姆、月嫂护理等专业培训讲师30余人，现上线阿姨1000余人。</span><p>"+
                "<span>    公司于2015年3月通过移动互联网，创建“三个阿姨”家政服务平台。是一个融在线预定、支付、"+
                "点评为一体的家政公司经纪服务、养老服务和社区商业的综合性平台。通过“三个阿姨”平台网站、手机客户端APP、"+
                "微信公众账号方式为客户提供线上的雇佣查询、预定、在线支付、合同管理、"+
                "雇佣评价与线下以家政经纪人为主导的面试撮合、背景调查、雇后保障等一系列服务，是处于我国领先的家政经纪服务商，"+
                "是家政O2O理念的践行者。目前，已经有三十余家家政公司与我公司签订了战略合作协议，开启全国联盟复制模式，"+
                "加速平台发展步伐。</span><p><span>    “三个阿姨”服务平台，将上线数以万计的阿姨、庞大的家政经纪人群体、"+
                "住宅区物业公司和社区商业服务机构以及社区网格，形成一张立体的服务网络。家政经纪人为雇主找保姆、"+
                "为老人提供一对一的一条龙的服务，筛选被雇阿姨的简历，考察被雇阿姨的技能，诚信背景调查，组织面试，"+
                "签订劳动合同，上岗服务跟踪等等，使雇主找阿姨、保姆等更快更省心，养老服务更便捷更贴心。</span><p>"+
                "<span>    “三个阿姨”家政服务平台的服务特色是：让户主足不出户，轻松发布家政需求招聘，平均60秒完成系统智能匹配，"+
                "让您轻松选阿姨。真正让每个使用者省心、放心、安心。</span><p><b>广告语：家政新主张，服务改变生活。</b><br>"+
                "<b>使 命：提高阿姨家庭收入，提升客户生活品质，为构建和谐社会而奋斗!</b><br>"+
                "<b>愿 景：让天下阿姨不再被收中介费。</b><br>"+
                "<b>价值观：富强、民主、文明、和谐、爱国、敬业、诚信、友善、联盟、共赢!</b>";
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
