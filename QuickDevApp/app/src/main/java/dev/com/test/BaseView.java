package dev.com.test;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: midVictor
 * date: on 2016/12/21
 * description:
 */

public class BaseView extends View {

      Paint mBasePaint;
    int mBaseHeight, mBaseWidth;
    int BASE_MIN_ = 600;

    public BaseView(Context context) {
        super(context);
        init();
    }

    private void init() {

        mBasePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mBasePaint.setStyle(Paint.Style.STROKE);
        mBasePaint.setAntiAlias(true);
        mBasePaint.setColor(Color.parseColor("#CCCCCC"));
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);


        if (wMode == MeasureSpec.UNSPECIFIED) {
            width = BASE_MIN_;
        }
        if (hMode == MeasureSpec.UNSPECIFIED) {
            height = BASE_MIN_;
        }

        if (wMode == MeasureSpec.AT_MOST && width > BASE_MIN_) {
            width = BASE_MIN_;
        }
        if (hMode == MeasureSpec.AT_MOST && height > BASE_MIN_) {
            height = BASE_MIN_;
        }

        if (width <= height) {
            mBaseHeight = mBaseWidth = width;
            setMeasuredDimension(width, width);
        } else {
            mBaseHeight = mBaseWidth = height;
            setMeasuredDimension(height, height);
        }
    }
}
