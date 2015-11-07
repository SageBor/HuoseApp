package team.house.cn.HuoseApp.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.utils.ActivityManager;


/**
 * @author kenan Activity基类
 */
public abstract class BaseActivity extends Activity implements
        View.OnClickListener {
    @SuppressWarnings("unused")
    private static final String TAG = "BaseActivity";
    /**
     * head left TextView
     */
    protected TextView mCityView;
    protected TextView mLeftView;
    /**
     * head right TextView
     */
    protected TextView mRightView;
    /**
     * head title
     */
    protected TextView mTitleView;

    protected TextView mMain;
    protected TextView mOrder;
    protected TextView mUcenter;
    protected TextView mHotLine;

    protected Resources resources;
//    protected UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        resources = this.getResources();
        initView();
        initPublicView();
        initEvent();
        initData();
        initTitle();
        Log.e("theone", "theone Base");
//        TypefaceHelper.typeface(this);
      }

    /**
     * 设置Layout布局文件
     */
    protected void initView() {
    }


    protected void initData() {
    }

    protected void initEvent() {
        if (mLeftView != null) {
            // 退出键
            mLeftView.setOnClickListener(this);
        }

        if (mRightView != null) {
            mRightView.setOnClickListener(this);
        }

        if (mMain != null) {
            mMain.setOnClickListener(this);
        }

        if (mOrder != null) {
            mOrder.setOnClickListener(this);
        }

        if (mUcenter != null) {
            mUcenter.setOnClickListener(this);
        }

        if (mHotLine != null) {
            mHotLine.setOnClickListener(this);
        }
    }


    protected void onClickListener(View v) {
    }


    /**
     * 初始化title栏，如果默认只需要设置文字就可以mTitleTextView.setText
     */
    protected void initTitle() {
    }
    private void initPublicView() {
        mCityView = (TextView) this.findViewById(R.id.tv_city);
        mLeftView = (TextView) this.findViewById(R.id.tv_left);
        mTitleView = (TextView) this.findViewById(R.id.tv_title);
        mRightView = (TextView) findViewById(R.id.tv_right);
        mMain = (TextView) findViewById(R.id.tv_main);
        mOrder = (TextView) findViewById(R.id.tv_order);
        mUcenter = (TextView) findViewById(R.id.tv_ucenter);
        mHotLine = (TextView) findViewById(R.id.tv_hotline);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_left:
                onBackPressed();
                break;
            default:
                onClickListener(v);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        APPYOUMENG.onResume(this);
        //固定应用的字体大小，以免用户手动设置系统字体时改乱布局
        Resources resource = getResources();
        Configuration c = resource.getConfiguration();
        c.fontScale = 1.0f;
        resource.updateConfiguration(c, resource.getDisplayMetrics());
    }

    @Override
    protected void onPause() {
        super.onPause();
//        APPYOUMENG.onPause(this);
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().removeActivity(this);
        super.onDestroy();
    }

    public void destroyActivity() {
        this.finish();
    }

    /**
     * 设置该按钮view图片
     *
     * @param endIcon  图片
     * @param position 图片位置 0-左 1-上 2-右 3-下
     */
    public void setDrawable(TextView view, Drawable endIcon, int position) {
        endIcon.setBounds(0, 0, endIcon.getMinimumWidth(),
                endIcon.getMinimumHeight());
        switch (position) {
            case 0:
                view.setCompoundDrawables(endIcon, null, null, null);
                break;
            case 1:
                view.setCompoundDrawables(null, endIcon, null, null);
                break;
            case 2:
                view.setCompoundDrawables(null, null, endIcon, null);
                break;
            case 4:
                view.setCompoundDrawables(null, null, null, endIcon);
                break;
            default:
                break;
        }

    }
}
