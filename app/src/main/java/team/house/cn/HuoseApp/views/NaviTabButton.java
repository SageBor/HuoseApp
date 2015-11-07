package team.house.cn.HuoseApp.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import team.house.cn.HuoseApp.R;
public class NaviTabButton extends FrameLayout {
	private int mIndex;

	private ImageView mImage;
	private TextView mTitle;
	private Drawable mSelectedImg;
	private Drawable mUnselectedImg;
	private Context mContext;
	private RelativeLayout mContainer;
	private  boolean isSelected = false;
	public NaviTabButton(Context context) {
		this(context, null);
	}

	public NaviTabButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NaviTabButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		this.mContext = context;
//		typeface = ((HuoYunApplication)mContext.getApplicationContext()).getTypeface();

//		View.OnClickListener clickListner = new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if(mIndex==1){//订单管理页面
//					UserHelper.getInstance().setContext(mContext);
//					if(!UserHelper.getInstance().isLogin()){
//						
//					}
//				}
//				((FragmentTabPager) mContext).setFragmentIndicator(mIndex);
//			}
//		};

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View inflate = inflater.inflate(R.layout.layout_navi_tab_button, this, true);
		mContainer = (RelativeLayout) findViewById(R.id.tab_btn_container);

		mImage = (ImageView) findViewById(R.id.tab_btn_default);
		mTitle = (TextView) findViewById(R.id.tab_btn_title);
//		mTitle.setTypeface(typeface);
//
//		container.setOnClickListener(clickListner);
	}

	public void setIndex(int index) {
		this.mIndex = index;
	}

	public void setUnselectedImage(Drawable img) {
		this.mUnselectedImg = img;
	}

	public void setSelectedImage(Drawable img) {
		this.mSelectedImg = img;
	}

	/**
	 * 更换字体颜色
	 * 
	 * @param selected
	 */
	private void setSelectedColor(Boolean selected) {
		if (selected) {
			// 选中
			mTitle.setTextColor(this.getResources().getColor(R.color.ce6454a));
		} else {
			// 不选中
			mTitle.setTextColor(this.getResources().getColor(R.color.c4d4d4d));
		}
	}
	public void setOnClickListener(OnClickListener l){
		if(mContainer != null)
		mContainer.setOnClickListener(l);
	}
	public void setSelectedButton(Boolean selected) {
		isSelected = selected;
		setSelectedColor(selected);
		if (selected) {
			mImage.setImageDrawable(mSelectedImg);
		} else {
			mImage.setImageDrawable(mUnselectedImg);
		}
	}
	public void refresh(){
		setSelectedButton(isSelected);
	}
	public void setTitle(String title) {
		mTitle.setText(title);
	}
}
