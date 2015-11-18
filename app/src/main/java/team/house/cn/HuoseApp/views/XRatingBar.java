package team.house.cn.HuoseApp.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import team.house.cn.HuoseApp.R;


/**
 * 自定义评分条
 *
 * @author xiayong
 */
public class XRatingBar extends View {

    private static final int NORMAL = 0;
    private static final int SMALL = 1;

    Bitmap drawable;
    Bitmap progressBackground;
    Context mContext;
    private int mNumStars = 5;
    private float mRating = 0;
    private boolean mIndicator;
    private float slidePosition;
    private int mType;
    private int space = 0;//星星之间的间距，默认0
    private boolean isTouching = false;//default value

    /**
     * A callback that notifies clients when the rating has been changed. This
     * includes changes that were initiated by the user through a touch gesture
     * or arrow key/trackball as well as changes that were initiated
     * programmatically.
     */
    public interface OnRatingBarChangeListener {

        /**
         * Notification that the rating has changed. Clients can use the
         * fromUser parameter to distinguish user-initiated changes from those
         * that occurred programmatically. This will not be called continuously
         * while the user is dragging, only when the user finalizes a rating by
         * lifting the touch.
         *
         * @param ratingBar The RatingBar whose rating has changed.
         * @param rating    The current rating. This will be in the range
         *                  0..numStars.
         * @param fromUser  True if the rating change was initiated by a user's
         *                  touch gesture or arrow key/horizontal trackbell movement.
         */
        void onRatingChanged(XRatingBar ratingBar, float rating, boolean fromUser);

        void onRatingFinished(XRatingBar ratingBar, float rating);

    }

    private OnRatingBarChangeListener mOnRatingBarChangeListener;

    public XRatingBar(Context context) {
        this(context, null);
    }

    public XRatingBar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.coloredRatingBarStyle);
    }

    public XRatingBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ColoredRatingBar,
                defStyle, 0);
        final boolean indicator = a.getBoolean(R.styleable.ColoredRatingBar_indicator, false);
        final float rating = a.getFloat(R.styleable.ColoredRatingBar_rating, -1);
        final int type = a.getInt(R.styleable.ColoredRatingBar_type, 0);
        final int space = (int) a.getDimension(R.styleable.ColoredRatingBar_space, 0);
        a.recycle();

        setIndicator(indicator);
        setRating(rating);
        setType(type);
        setSpace(space);
        init(context);
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
    }

    private void init(Context context) {
        mContext = context;
        Resources res = getResources();
        if (mType == SMALL) {
            drawable = BitmapFactory.decodeResource(res, R.drawable.star_blue);//new Bitmap[]{BitmapFactory.decodeResource(res, R.drawable.ratered_big),BitmapFactory.decodeResource(res, R.drawable.star_orange_small),BitmapFactory.decodeResource(res, R.drawable.star_green_small)};
            progressBackground = BitmapFactory.decodeResource(res, R.drawable.start_gray);
        } else {
            drawable = BitmapFactory.decodeResource(res, R.drawable.star_blue);//new Bitmap[]{BitmapFactory.decodeResource(res, R.drawable.star_red),BitmapFactory.decodeResource(res, R.drawable.star_orange),BitmapFactory.decodeResource(res, R.drawable.star_green)};
            progressBackground = BitmapFactory.decodeResource(res, R.drawable.start_gray);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw empty stars bg
        for (int i = 0; i < mNumStars; i++) {
            drawStar(canvas, i);
        }

    }


    private void drawStar(Canvas canvas, int position) {
        float fraction = mRating - (position);
        Bitmap ratedStar = getRatedStar();
        if ((position + 1) < mRating) {
            canvas.drawBitmap(ratedStar, (position * (ratedStar.getWidth() + space)), 0, null);
        } else {
            //fraction 小数部分
            if (fraction > 0 && fraction <= 1) {
                int sourceWidth = ratedStar.getWidth();
                int sourceHeight = ratedStar.getHeight();

                int targetWidth = (int) (ratedStar.getWidth() * fraction);
                int bgWidth = sourceWidth - targetWidth;

                if (targetWidth > 0) {
                    Bitmap croppedBmp = Bitmap.createBitmap(ratedStar, 0, 0, targetWidth, sourceHeight);
                    canvas.drawBitmap(croppedBmp, (position * (sourceWidth + space)), 0, null);
//                    croppedBmp.recycle();
                }
                if (bgWidth > 0) {
                    Bitmap croppedBg = Bitmap.createBitmap(progressBackground, targetWidth, 0, bgWidth, sourceHeight);
                    canvas.drawBitmap(croppedBg, (position * (sourceWidth + space)) + targetWidth, 0, null);
//                    croppedBg.recycle();
                }
            } else {
                canvas.drawBitmap(progressBackground, (position * (progressBackground.getWidth() + space)), 0, null);
            }
        }


    }

    private Bitmap getRatedStar() {
        return drawable;
       /* if(mRating <=1.6f){
            return drawables[0];
        }else if(mRating <=3.2f){
            return drawables[1];
        }else {
            return drawables[2];
        }*/
    }

    public int getNumStars() {
        return mNumStars;
    }

    public void setNumStars(int numStars) {
        this.mNumStars = numStars;
        if (mNumStars < 1) {
            mNumStars = 1;
        }
    }

    public float getRating() {
        return mRating;
    }

    public boolean rated() {
        return mRating != 0;
    }

    public void setRating(float rating) {
        setRating(rating, false);
    }

    public void setRating(float rating, boolean fromUser) {
        if (rating > mNumStars) {
            this.mRating = mNumStars;
        }
        this.mRating = rating;
        invalidate();
        dispatchRatingChange(fromUser);
    }

    public boolean isIndicator() {
        return mIndicator;
    }

    public void setIndicator(boolean indicator) {
        this.mIndicator = indicator;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (progressBackground != null) {

            final int width = progressBackground.getWidth() * mNumStars + (mNumStars - 1) * space;
            final int height = progressBackground.getHeight();
            setMeasuredDimension(resolveSizeAndState2(width, widthMeasureSpec, 0),
                    resolveSizeAndState2(height, heightMeasureSpec, 0));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mIndicator) {
            return false;
        }

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isTouching = true;
                rating(event);
                Log.e("xiayong", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                rating(event);
                Log.e("xiayong", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("xiayong", "ACTION_UP");
                isTouching = false;
                if (mOnRatingBarChangeListener != null)
                    mOnRatingBarChangeListener.onRatingFinished(this, getRating());
               /* slidePosition = getRelativePosition(event.getX());
                newRating = (int) slidePosition + 1;
                if (newRating != mRating) {
                    setRating(newRating, true);
                }
                if (action == MotionEvent.ACTION_UP) {
                    isTouching = false;
                    if (mOnRatingBarChangeListener != null)
                        mOnRatingBarChangeListener.onRatingFinished(this, getRating());
                }*/
                break;
            case MotionEvent.ACTION_CANCEL:
                isTouching = false;
                break;
            default:
                break;
        }

        return true;
    }

    private void rating(MotionEvent event) {
        slidePosition = getRelativePosition(event.getX());
        float newRating = slidePosition + 1;
        if(newRating != mRating){
            setRating(newRating, true);
        }
    }

    private float getRelativePosition(float x) {
        float position = x / progressBackground.getWidth();
        position = Math.max(position, 0);
        return Math.min(position, mNumStars - 1);
    }

    /**
     * Sets the listener to be called when the rating changes.
     *
     * @param listener The listener.
     */
    public void setOnRatingBarChangeListener(OnRatingBarChangeListener listener) {
        mOnRatingBarChangeListener = listener;
    }

    public static int resolveSizeAndState2(int size, int measureSpec, int childMeasuredState) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                if (specSize < size) {
                    result = specSize | MEASURED_STATE_TOO_SMALL;
                } else {
                    result = size;
                }
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result | (childMeasuredState & MEASURED_STATE_MASK);
    }

    /**
     * 是否正在评分
     * @return
     */
    public boolean isReating(){

        return isTouching;
    }
    /**
     * @return The listener (may be null) that is listening for rating change
     * events.
     */
    public OnRatingBarChangeListener getOnRatingBarChangeListener() {
        return mOnRatingBarChangeListener;
    }

    void dispatchRatingChange(boolean fromUser) {
        if (mOnRatingBarChangeListener != null) {
            mOnRatingBarChangeListener.onRatingChanged(this, getRating(),
                    fromUser);
        }
    }


}
