package dev.com.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import dev.com.quick.R;


/**
 * author: midVictor
 * date: on 2017/1/17
 * description:
 */

public class BoatRunView extends View {

    private int mHeight;
    private int mWidth;
    int BASE_HEIGHT = 600;


//    float dx_ = 0;
//    float dy_ = BASE_HEIGHT;
    int WAVE_WIDTH = 620; //波长
    Path path = new Path();
    Path pathWave = new Path();
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mBitmap;
    private PathMeasure mWavePathMeasure;

    float[] pos = new float[2];
    float[] tan = new float[2];


    public BoatRunView(Context context) {
        super(context);
    }

    public BoatRunView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boat);



        mWavePathMeasure = new PathMeasure(path, false);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);


        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {



        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#97FFFF"));


        int WAVE_LENGTH_QUARTER = WAVE_WIDTH / 4;//波长的四分之一
        path.moveTo(0 , BASE_HEIGHT);
        pathWave.moveTo(0 , BASE_HEIGHT);
        for (int i = 0; i <= mWidth; i += WAVE_WIDTH) {
            path.rQuadTo(WAVE_LENGTH_QUARTER, -WAVE_LENGTH_QUARTER , WAVE_LENGTH_QUARTER * 2, 0);
            path.rQuadTo(WAVE_LENGTH_QUARTER, WAVE_LENGTH_QUARTER, WAVE_LENGTH_QUARTER * 2, 0);

            pathWave.rQuadTo(WAVE_LENGTH_QUARTER, -WAVE_LENGTH_QUARTER , WAVE_LENGTH_QUARTER * 2, 0);
            pathWave.rQuadTo(WAVE_LENGTH_QUARTER, WAVE_LENGTH_QUARTER, WAVE_LENGTH_QUARTER * 2, 0);
        }

        path.lineTo(mWidth, mHeight);
        path.lineTo(0, mHeight);
        path.close();
        mWavePathMeasure = new PathMeasure(pathWave, false);
        canvas.drawPath(path, paint);
//
//        Rect rect = new Rect(mWidth / 2 - 1, 0, mWidth / 2 + 1, mHeight);
//
//        paint.setColor(Color.RED);
//
////        canvas.drawRect(rect, paint);
//
//        Region region = new Region(rect);
//        Region clip = new Region();
//        clip.setPath(path, region);
//        canvas.drawBitmap(mBitmap, (clip.getBounds().right + clip.getBounds().left) /  (clip.getBounds().right + clip.getBounds().left) / 2, clip.getBounds().top - mBitmap.getHeight(), paint);
        canvas.drawBitmap(mBitmap, pos[0]- mBitmap.getWidth()/2, pos[1] -mBitmap.getHeight()/2, paint);





    }


    public void startAnimator() {

        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                ValueAnimator animator = ValueAnimator.ofFloat(0, mWavePathMeasure.getLength());
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setInterpolator(new LinearInterpolator());
//
                animator.setDuration(2100);

                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                        mWavePathMeasure.getPosTan((Float) animation.getAnimatedValue(), pos, tan);

                        invalidate();
                    }
                });


                animator.start();
            }
        },100);




    }


}
