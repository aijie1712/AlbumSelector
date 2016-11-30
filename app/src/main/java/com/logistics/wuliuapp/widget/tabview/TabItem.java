package com.logistics.wuliuapp.widget.tabview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.logistics.wuliuapp.R;

/**
 * Created by Mikes on 2016-6-8.
 */
public class TabItem extends View {
    /*字体大小*/
    private int mTextSize = 8;

    /*字体选中的颜色*/
    private int mTextColorSelect = 0xff45c01a;

    /*字体未选择的时候的颜色*/
    private int mTextColorNormal = 0xff777777;

    /*绘制未选中时字体的画笔*/
    private Paint mTextPaintNormal;

    /*绘制已选中时字体的画笔*/
    private Paint mTextPaintSelect;

    /*每个 item 的宽和高，包括字体和图标一起*/
    private int mViewHeight, mViewWidth;

    /*字体的内容*/
    private String mTextValue;

    /*已选中时的图标*/
    private Bitmap mIconNormal;
    private Drawable mIconNormalDrawable;

    /*未选中时的图标*/
    private Bitmap mIconSelect;
    private Drawable mIconSelectDrawable;

    /*用于记录字体大小*/
    private Rect mBoundText;

    /*已选中是图标的画笔*/
    private Paint mIconPaintSelect;

    /*为选中时图标的画笔*/
    private Paint mIconPaintNormal;

    private int mPadding = 10;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.TabView);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.TabView_text_size,mTextSize);
        mTextColorNormal = typedArray.getColor(R.styleable.TabView_text_normal_color, mTextColorNormal);
        mTextColorSelect = typedArray.getColor(R.styleable.TabView_text_select_color, mTextColorSelect);
        mPadding = (int) typedArray.getDimension(R.styleable.TabView_item_padding, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                mPadding, getResources().getDisplayMetrics()));

        mIconNormalDrawable = typedArray.getDrawable(R.styleable.TabView_icon_normal);
        if (mIconNormalDrawable != null)
            mIconNormal = ((BitmapDrawable)mIconNormalDrawable).getBitmap();

        mIconSelectDrawable = typedArray.getDrawable(R.styleable.TabView_icon_selected);
        if (mIconSelectDrawable != null)
            mIconSelect = ((BitmapDrawable)mIconSelectDrawable).getBitmap();

        mTextValue = typedArray.getString(R.styleable.TabView_text_content);

        typedArray.recycle();
        initView();
        initText();
    }

    /*初始化一些东西*/
    private void initView() {
        mBoundText = new Rect();
        setPadding(mPadding,mPadding,mPadding,mPadding);
    }

    /*初始化画笔，并设置出是内容*/
    private void initText() {
        mTextPaintNormal = new Paint();
        mTextPaintNormal.setTextSize(mTextSize);
        mTextPaintNormal.setColor(mTextColorNormal);
        mTextPaintNormal.setAntiAlias(true);
        mTextPaintNormal.setAlpha(0xff);

        mTextPaintSelect = new Paint();
        mTextPaintSelect.setTextSize(mTextSize);
        mTextPaintSelect.setColor(mTextColorSelect);
        mTextPaintSelect.setAntiAlias(true);
        mTextPaintSelect.setAlpha(0);

        mIconPaintSelect = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIconPaintSelect.setAlpha(0);

        mIconPaintNormal = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIconPaintNormal.setAlpha(0xff);
    }

    /*测量字体的大小*/
    private void measureText() {
        mTextPaintNormal.getTextBounds(mTextValue, 0, mTextValue.length(), mBoundText);
    }

    /*测量字体和图标的大小，并设置自身的宽和高*/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0, height = 0;

        measureText();
        int contentWidth = Math.max(mBoundText.width(), mIconNormal.getWidth());
        int desiredWidth = getPaddingLeft() + getPaddingRight() + contentWidth;
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                width = Math.min(widthSize, desiredWidth);
                break;
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                width = desiredWidth;
                break;
        }
        int contentHeight = mBoundText.height() + mIconNormal.getHeight();
        int desiredHeight = getPaddingTop() + getPaddingBottom() + contentHeight;
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                height = Math.min(heightSize, desiredHeight);
                break;
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                height = contentHeight;
                break;
        }
        setMeasuredDimension(width, height);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBitmap(canvas);
        drawText(canvas);
    }

    /*话图标，先画未选中的图标，在画已选中的图标*/
    private void drawBitmap(Canvas canvas) {
        int left = (mViewWidth - mIconNormal.getWidth()) / 2;
        int top = (mViewHeight - mIconNormal.getHeight() - mBoundText.height()) / 2;
        canvas.drawBitmap(mIconNormal, left, top, mIconPaintNormal);
        canvas.drawBitmap(mIconSelect, left, top, mIconPaintSelect);
    }

    /*画字体*/
    private void drawText(Canvas canvas) {
        float x = (mViewWidth - mBoundText.width()) / 2.0f;
        float y = (mViewHeight + mIconNormal.getHeight() + mBoundText.height()) / 2.0F;
        canvas.drawText(mTextValue, x, y, mTextPaintNormal);
        canvas.drawText(mTextValue, x, y, mTextPaintSelect);
    }

    public void setTextSize(int textSize) {
        this.mTextSize = textSize;
        mTextPaintNormal.setTextSize(textSize);
        mTextPaintSelect.setTextSize(textSize);
    }

    public void setTextColorSelect(int mTextColorSelect) {
        this.mTextColorSelect = mTextColorSelect;
        mTextPaintSelect.setColor(mTextColorSelect);
        mTextPaintSelect.setAlpha(0);
    }

    public void setTextColorNormal(int mTextColorNormal) {
        this.mTextColorNormal = mTextColorNormal;
        mTextPaintNormal.setColor(mTextColorNormal);
        mTextPaintNormal.setAlpha(0xff);
    }

    public void setTextValue(String TextValue) {
        this.mTextValue = TextValue;
    }

    public void setIconText(int[] iconSelId, String TextValue) {
        this.mIconSelect = BitmapFactory.decodeResource(getResources(), iconSelId[0]);
        this.mIconNormal = BitmapFactory.decodeResource(getResources(), iconSelId[1]);
        this.mTextValue = TextValue;
    }

    /*通过 alpha 来设置 每个画笔的透明度，从而实现现实的效果*/
    public void setTabAlpha(float alpha) {
        int paintAlpha = (int) (alpha * 255);
        mIconPaintSelect.setAlpha(paintAlpha);
        mIconPaintNormal.setAlpha(255 - paintAlpha);
        mTextPaintSelect.setAlpha(paintAlpha);
        mTextPaintNormal.setAlpha(255 - paintAlpha);
        invalidate();
    }
}
