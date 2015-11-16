package team.house.cn.HuoseApp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;


/**
 * 
 * @author xiayong
 *请使用LinearListView
 */
public class ScrollListView extends ListView {
	private View mFooterView;
	private int mMaxCountForRemoveFooterView = -1;//default value

	public ScrollListView(Context context) {
		super(context);
	}

	public ScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ScrollListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);

	}

	@Override
	public void addFooterView(View v) {
//		TypefaceHelper.typeface(v);
		super.addFooterView(v);
		mFooterView = v;
	}

	@Override
	public void addHeaderView(View v) {
//		TypefaceHelper.typeface(v);
		super.addHeaderView(v);
	}
	@Override
	public boolean removeFooterView(View v) {
		return super.removeFooterView(v);

	}

	@Override
	public boolean removeHeaderView(View v) {
		return super.removeHeaderView(v);
	}

	public void setMaxCountForRemoveFooterView(int maxCountForRemoveFooterView) {
		mMaxCountForRemoveFooterView = maxCountForRemoveFooterView;
	}

//	@Override
//	public void OnItemCountChange(int count) {
//		if (mFooterView != null && mMaxCountForRemoveFooterView != -1) {
//			if (count >= mMaxCountForRemoveFooterView) {
//				hideFooterView(mFooterView);
//			} else {
//				showFooterView(mFooterView);
//			}
//		}
//	}

//	public void showFooterView() {
//		if (mFooterView == null) {
//			return;
//		}
//		View v = mFooterView.findViewById(R.id.btn_add_next_address);
//		if (v != null && !v.isShown()) {
//			v.setVisibility(View.VISIBLE);
//		}
//	}

//	public void hideFooterView() {
//		if (mFooterView == null) {
//			return;
//		}
//		View v = mFooterView.findViewById(R.id.btn_add_next_address);
//		if (v != null /*&& v.isShown()*/) {
//			v.setVisibility(View.GONE);
//		}
//	}
}
