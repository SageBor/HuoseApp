package team.house.cn.HuoseApp.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import team.house.cn.HuoseApp.Dao.Users;
import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.utils.ActivityManager;
import team.house.cn.HuoseApp.utils.UserUtil;


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
    protected Users mUserBean;
//    protected UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        resources = this.getResources();
        mUserBean = UserUtil.getUserinfoFromSharepreference();
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
        Intent intent;
        int viewId = v.getId();
        switch (viewId) {
            case R.id.tv_main:
                intent = new Intent(new Intent(this, MainActivity.class));
                startActivity(intent);
                this.finish();
                break;
            case R.id.tv_order:

                intent = new Intent(new Intent(this, CurrentOrderActivity.class));
                goIntent(intent);
//                this.finish();
                break;
            case R.id.tv_ucenter:

                intent = new Intent(new Intent(this, UserAccountActivity.class));
                goIntent(intent);
//                this.finish();
                break;
            case R.id.tv_hotline:
                AlertDialog.Builder dialog = new AlertDialog.Builder(BaseActivity.this);
                dialog.setTitle("呼叫客服").setMessage("400-701-0168").setPositiveButton("呼叫", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:400-701-0168"));

                        if (ContextCompat.checkSelfPermission(BaseActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for Activity#requestPermissions for more details.
                            return;
                        }
                        BaseActivity.this.startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();//取消弹出框
                    }
                }).create().show();

        }
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
        mLeftView.setText("<");
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
    protected void goIntent(Intent intent) {
        int loginSate = UserUtil.getUseridFromSharepreference();
        if (loginSate == 0) {
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);

    }


}
