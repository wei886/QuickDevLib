package dev.com.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import dev.com.quick.R;


/**
 * author: midVictor
 * date: on 2016/12/21
 * description:
 */

public class BitmapView extends View {

    private Bitmap mBitmapOrigin;

    private String TAG = "BitmapView";
    private int mBitmpaWidth, mBitmapHeight;
    private Paint mPaint;

    private int num = 1;
    private int min_h;
    private int min_w;
    private Handler mHandle;

    public BitmapView(Context context) {
        super(context);
        init(context);
    }


    public BitmapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setAntiAlias(true);
        mBitmapOrigin = BitmapFactory.decodeResource(getResources(), R.drawable.checkmark);
        min_h = mBitmapOrigin.getHeight();
        min_w = mBitmapOrigin.getWidth();


        Log.e(TAG, "init" + " h=" + min_h + " w=" + min_w);


        mHandle = new Handler() {
            @Override
            public synchronized void handleMessage(Message msg) {
                super.handleMessage(msg);
                synchronized (BitmapView.this) {
                    int n = msg.what;
                    if (n < 13) {
                        num++;
                        invalidate();
                        mHandle.sendEmptyMessageDelayed(num, 40);
                    } else {
                        num = 1;
                    }
                }
            }
        };


        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                mHandle.sendEmptyMessageDelayed(num, 40);

//                mHandle.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (num > 13) {
//                         } else {
//                            num++;
//                        }
//                        mHandle.sendEmptyMessage(num);
//                     }
//                }, 40);

            }
        });

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        int height = MeasureSpec.getSize(heightMeasureSpec);
//        int wMode = MeasureSpec.getMode(widthMeasureSpec);
//        int hMode = MeasureSpec.getMode(heightMeasureSpec);
//
//
//        if (wMode == MeasureSpec.UNSPECIFIED) {
//            width = MIN_W;
//        }
//        if (hMode == MeasureSpec.UNSPECIFIED) {
//            height = MIN_H;
//        }
//        if (wMode == MeasureSpec.AT_MOST || wMode == MeasureSpec.EXACTLY && width > MIN_W) {
//            width = MIN_W;
//        }
//        if (hMode == MeasureSpec.AT_MOST || wMode == MeasureSpec.EXACTLY && height > MIN_H) {
//            height = MIN_H;
//        }
//
//        mBitmpaWidth = width;
//        mBitmapHeight = height;


        setMeasuredDimension(min_h, min_h);


    }


    @Override
    protected void onDraw(Canvas canvas) {

//        int MIN_H = mBitmapOrigin.getHeight();
//        int MIN_W = mBitmapOrigin.getWidth();
//
//        if (MIN_H == 0 || MIN_W == 0 || mBitmapHeight == 0 || mBitmpaWidth == 0) {
//            return;
//        }
//
//        if (MIN_W > mBitmpaWidth || MIN_W > mBitmapHeight) {
//            Matrix matrix = new Matrix();
//            matrix.postScale((float) mBitmpaWidth / MIN_W, (float) mBitmapHeight / MIN_H);
//            bitmap = Bitmap.createBitmap(mBitmapOrigin, 0, 0, MIN_W, MIN_H, matrix, false);
//        }
//
//        if (bitmap != null) {
//            Rect src = new Rect(0, 0, bitmap.getHeight() * num, bitmap.getHeight());
//            RectF dsc = new RectF();
//            canvas.drawBitmap(bitmap, src, dsc, mPaint);
//        }

        Rect src = new Rect(min_h * (num - 1), 0, min_h * num, min_h);
        RectF dsc = new RectF(0, 0, min_h, min_h);
        canvas.drawBitmap(mBitmapOrigin, src, dsc, mPaint);


    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
