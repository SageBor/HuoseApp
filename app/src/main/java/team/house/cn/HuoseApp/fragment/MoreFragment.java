package team.house.cn.HuoseApp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import team.house.cn.HuoseApp.R;

/**
 * 客服中心
 */
public class MoreFragment extends Fragment {

	private View mRootView;
	private RelativeLayout aboutOurs;
	private RelativeLayout tellPhone;
	private RelativeLayout user_help;
	private RelativeLayout user_account;// 账户余额
	private RelativeLayout user_coupons;// 优惠券
	private RelativeLayout my_frequently_routes;// 我的常用路线
	private RelativeLayout user_share;//
	private RelativeLayout  user_price_standard; // 2.3 版本 收费标准
	private RelativeLayout user_bonuspoints;// 我的积分
	private Button btn_login;
	private TextView user_name;
	private TextView txtCouponsAmount;// 优惠券数量
	private TextView txtPoints;//我的积分
	private TextView tvCredit;//信用额度
	private TextView tv_share_coupons;// 邀请好友送券文案
	private RelativeLayout logout;
	private LinearLayout layoutUserInfo;
	private Resources mResources;
	private String usertype = "1"; // 用户类型  1 - 普通用户 2 - 信用用户
	private String creditamount = "0"; // 信用额度
	private String balance = "";//账户余额

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (mRootView == null) {
			mRootView = inflater.inflate(R.layout.layout_fragment_more, container, false);
//			PreferenceHelper.getInstance().setContext(getActivity());
			initView(mRootView);
//			initEvent();
		}
//		initData();
		
		ViewGroup parent = (ViewGroup) mRootView.getParent();
		if (parent != null) {
			parent.removeView(mRootView);
		}
		return mRootView;
	}

	@Override
	public void onResume() {
		super.onResume();
//		getDataFromServie();// 获取账户余额和优惠券
		this.updateView();
		// APPYOUMENG.onPageStart("我的:MoreFragment");

	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {
		super.onOptionsMenuClosed(menu);
	}

	/**
	 * 更新UI
	 */
	public void updateView() {
//		updateLayout(userHelper.isLogin());
	}

	private void initView(View view) {}

	
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
//            getDataFromServie();// 获取账户余额和优惠券
            this.updateView();
        }

    }
}
