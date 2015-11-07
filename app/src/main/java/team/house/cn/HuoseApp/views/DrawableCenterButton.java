package team.house.cn.HuoseApp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

public class DrawableCenterButton extends Button {
	public DrawableCenterButton(Context context) {
		super(context);
	}

	public DrawableCenterButton(Context context, AttributeSet attrs,
								int defStyle) {
		super(context, attrs, defStyle);
	}

	public DrawableCenterButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Drawable[] drawables = getCompoundDrawables();
		if (drawables != null) {
			Drawable drawableLeft = drawables[0];
			if (drawableLeft != null) {

				float textWidth = getPaint().measureText(getText().toString());
				int drawablePadding = getCompoundDrawablePadding();
				int drawableWidth = 0;
				drawableWidth = drawableLeft.getIntrinsicWidth();
				float bodyWidth = textWidth + drawableWidth + drawablePadding;
				setPadding(0, 0, (int) (getWidth() - bodyWidth), 0);
				canvas.translate((getWidth() - bodyWidth) / 2, 0);
			}
		}
		super.onDraw(canvas);
	}


	/**
	 * 设置该按钮view图片
	 * 
	 * @param endIcon
	 *            图片
	 * @param position
	 *            图片位置 0-左 1-上 2-右 3-下
	 */
	public void setDrawable(Drawable endIcon, int position) {
		endIcon.setBounds(0, 0, endIcon.getMinimumWidth(),
				endIcon.getMinimumHeight());
		switch (position) {
		case 0:
			this.setCompoundDrawables(endIcon, null, null, null);
			break;
		case 1:
			this.setCompoundDrawables(null, endIcon, null, null);
			break;
		case 2:
			this.setCompoundDrawables(null, null, endIcon, null);
			break;
		case 4:
			this.setCompoundDrawables(null, null, null, endIcon);
			break;
		default:
			break;
		}

	}

}
