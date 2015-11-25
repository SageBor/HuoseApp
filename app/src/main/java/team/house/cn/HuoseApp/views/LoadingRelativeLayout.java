package team.house.cn.HuoseApp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class LoadingRelativeLayout extends RelativeLayout {

    public LoadingRelativeLayout(Context context) {
        super(context);
    }

    public LoadingRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 截获相对布局的TouchEvent事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
