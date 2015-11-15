package team.house.cn.HuoseApp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
/**
 * ScrollView 中嵌套GridView的解决办法（之一）
 * @author xiayong
 *
 */
public class ScrollGridView extends GridView {

	public ScrollGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public ScrollGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public ScrollGridView(Context context) {
		super(context);
	}
	/** 
     * 设置不滚动 
     */  
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)  
    {  
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
                MeasureSpec.AT_MOST);  
        super.onMeasure(widthMeasureSpec, expandSpec);  
  
    }  
}
