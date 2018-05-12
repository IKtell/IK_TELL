package com.robot.tuling.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.robot.tuling.R;

public class PercentageStripView extends View {

    private int mWidth = 640;
    private int mHeight = 24;

    private float percentage;

    private Paint mPaint;

    public PercentageStripView(Context context) {
        super(context);
    }

    public PercentageStripView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PercentageStripView);
        percentage = array.getFloat(R.styleable.PercentageStripView_percentage, 0);
        array.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.parseColor("#66FFFFFF"));
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
        mPaint.setColor(getResources().getColor(R.color.yellow));
        canvas.drawRect(0, 0, (int) (mWidth * percentage / 100), mHeight, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT
                && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT)
            setMeasuredDimension(mWidth, heightSize);
        else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT)
            setMeasuredDimension(widthSize, mHeight);
    }
}
