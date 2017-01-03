package dev.com.test;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import common.util.ScreenUtils;

/**
 * author: midVictor
 * date: on 2016/12/21
 * description:
 */

public class QuadView extends View {


    private int mHeight, mWidth;
    private Paint mPaint;
    private int mCenterX;
    private int mCenterY;


    private String TAG = "QuadView";
    private float percentage;

    public QuadView(Context context) {
        super(context);
        init(context);
    }

    void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);

        mPaint.setColor(Color.parseColor("#cccccc"));
    }

    public QuadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {


        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
        canvas.translate(mCenterX, mCenterY);

        float radius = mWidth / 4;

        RectF rect = new RectF(-radius, -radius, radius, radius);
//        canvas.drawOval(rect,mPaint);
        canvas.drawArc(rect, 90, 180, true, mPaint);

        Path pathQuad = new Path();

        pathQuad.moveTo(0, -radius);

        float controlX = radius * 1.5f + percentage * radius * 2f;
        float controlY = 0;

        float endX = 0;
        float endY = radius;

        pathQuad.quadTo(controlX, controlY, endX, endY);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(pathQuad, mPaint);
    }

    public void startMove() {
        ObjectAnimator animator;
        final float startX = this.getX();
        animator = ObjectAnimator.ofFloat(this, "translationX", startX, ScreenUtils.getScreenWidth(getContext()) / 2).setDuration(10000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();

//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                Object animatedValue = animation.getAnimatedValue();
//                if (animatedValue != null && animatedValue instanceof Float) {
//                    Float aFloat = Float.valueOf(animatedValue.toString());
//                    percentage = aFloat / (ScreenUtils.getScreenWidth(getContext()) / 2);
//                    Log.e(TAG, "V=" + percentage);
//                    invalidate();
//                }
//                Log.e(TAG, startX + "--" + ScreenUtils.getScreenWidth(getContext()) / 2 + "-----" + animatedValue.toString());
//            }
//        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);

        int MIN_ = 240;

        if (wMode == MeasureSpec.UNSPECIFIED) {
            width = MIN_;
        }
        if (hMode == MeasureSpec.UNSPECIFIED) {
            height = MIN_;
        }

        if (wMode == MeasureSpec.AT_MOST && width > MIN_) {
            width = MIN_;
        }
        if (hMode == MeasureSpec.AT_MOST && height > MIN_) {
            height = MIN_;
        }

        if (width <= height) {
            mHeight = mWidth = width;
            setMeasuredDimension(width, width);
        } else {
            mHeight = mWidth = height;
            setMeasuredDimension(height, height);
        }


    }
}
