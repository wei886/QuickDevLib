package dev.com.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/11/4.
 */

public class Dot extends View {

    public static String TAG = "Dot";

    private int mHeight;
    private int mWidth;
    private Paint mRedDotPaint;
    private GestureDetector mGD;

    private final int DOT_RADIUS = 20;
    private final int DRAG_DOT_RADIUS = 30;
    private int mCurX;
    private int mCurY;


    public Dot(Context context) {
        super(context);
        init(context);

    }

    public Dot(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public Dot(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {

        mGD = new GestureDetector(context, new DotGestLintener());

        setBackgroundColor(Color.parseColor("#cccccc"));

        mRedDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRedDotPaint.setColor(Color.RED);

        // 抗锯齿
        mRedDotPaint.setAntiAlias(true);
        // 防抖动
        mRedDotPaint.setDither(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        drawRedCircle(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "W" + w + " - " + h + " - " + oldh + " - " + oldw);
    }

    private void drawRedCircle(Canvas canvas) {
        canvas.drawCircle(mWidth / 2, mHeight / 2, DOT_RADIUS, mRedDotPaint);

        //手指按下的圆

        canvas.drawCircle(mCurX, mCurY, DRAG_DOT_RADIUS, mRedDotPaint);

        canvas.save();

        int tempX=300;
        int tempY=300;
        

        Path path = new Path();
        path.moveTo(mWidth / 2, mHeight / 2);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        mWidth = width;
        int height = MeasureSpec.getSize(heightMeasureSpec);
        mHeight = height;
        setMeasuredDimension(width, height);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        mGD.onTouchEvent(event);
        float startX = 0;
        float startY = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();



                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    class DotGestLintener implements GestureDetector.OnGestureListener {


        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            mCurX = (int) e2.getX();
            mCurY = (int) e2.getY();
            log("x=" + mCurX + " y=" + mCurY);
            return false;
        }
    }

    void log(String s) {
        Log.e(TAG, s);
    }

}
