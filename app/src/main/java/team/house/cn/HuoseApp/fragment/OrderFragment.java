package team.house.cn.HuoseApp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的订单
 */
public class OrderFragment extends Fragment {
//
//	private View mRootView;
//	private XListView xlistView;
//	private RequestLoadingWeb requestLoading;
//	private LinearLayout layout_noOrder;
//	private OrderListAdapter orderListAdapter;

	@Override
	public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
		return super.onCreateAnimation(transit, enter, nextAnim);
	}

/*	private Button btn_towork;
	private DrawableCenterButton mLeftView;
	private TextView mTitle;
	private Resources mResource;
	private UserHelper userHelper;*/

	public static final int REQUESTCODE_ORDERDETAILS = 0;// 订单详情页面
//	public static final int REQUESTCODE_EVALUATEDRIVER = 1;// 评价司机页面

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		/*if (mRootView == null) {
			mRootView = inflater.inflate(R.layout.layout_fragment_order, container, false);
			mResource = getResources();
			mLeftView = (DrawableCenterButton) mRootView.findViewById(R.id.left_btn);
			mLeftView.setVisibility(View.GONE);
			mTitle = (TextView) mRootView.findViewById(R.id.title_text);
			mTitle.setText(getResources().getString(R.string.order_fragment_title));
			xlistView = (XListView) mRootView.findViewById(R.id.order_list);
			orderListAdapter = new OrderListAdapter(this.getActivity(), this);
			orderListAdapter.setOnOkClickListener(OrderFragment.this);
			xlistView.setAdapter(orderListAdapter);
			xlistView.setXListViewListener(this);
			xlistView.setOnItemClickListener(this);
			xlistView.setPullLoadEnable(true);

			requestLoading = new RequestLoadingWeb(mRootView);
			layout_noOrder = (LinearLayout) mRootView.findViewById(R.id.noorder_view);
			requestLoading.setAgainListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					if (requestLoading.getStatus() == RequestLoadingWeb.STATUS_ERROR) {
						startRefresh();
					}
				}
			});
			btn_towork = (Button) mRootView.findViewById(R.id.btn_towork);
			btn_towork.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// 立即用车
					((FragmentTabPager) getActivity()).setFragmentIndicator(0);
				}
			});
			startRefresh();
		}
		initData();
		ViewGroup parent = (ViewGroup) mRootView.getParent();
		if (parent != null) {
			parent.removeView(mRootView);
		}
*/
		return null;
	}



	public void refresh() {
//		this.getDataFromService(1, APPConfig.oneHttpOrderCount);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (!hidden) {
//			// getDataFromService();
//			refresh();
		}

	}
}
