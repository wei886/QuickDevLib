package dev.com.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import dev.com.quick.R;


/**
 * author: midVictor
 * date: on 2017/1/17
 * description:
 */

public class WaveView extends View {

    private int mHeight;
    private int mWidth;
    int BASE_HEIGHT = 600;


    float dx_ = 0;
    float dy_ = BASE_HEIGHT;
    int WAVE_WIDTH = 620; //波长
    Path path = new Path();
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mBitmap;

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boat);

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
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor("#97FFFF"));

        path.reset();
        int WAVE_LENGTH_QUARTER = WAVE_WIDTH / 4;//波长的四分之一
        path.moveTo(-WAVE_WIDTH + dx_, dy_);
        for (int i = -WAVE_WIDTH; i <= mWidth; i += WAVE_WIDTH) {
            path.rQuadTo(WAVE_LENGTH_QUARTER, -WAVE_LENGTH_QUARTER - i % WAVE_WIDTH * 30, WAVE_LENGTH_QUARTER * 2, 0);
            path.rQuadTo(WAVE_LENGTH_QUARTER, WAVE_LENGTH_QUARTER, WAVE_LENGTH_QUARTER * 2, 0);
        }
        path.lineTo(mWidth, mHeight);
        path.lineTo(0, mHeight);
        path.close();
        canvas.drawPath(path, paint);

        Rect rect = new Rect(mWidth / 2 - 1, 0, mWidth / 2 + 1, mHeight);

        paint.setColor(Color.RED);

//        canvas.drawRect(rect, paint);

        Region region = new Region(rect);
        Region clip = new Region();
        clip.setPath(path, region);
        canvas.drawBitmap(mBitmap, (clip.getBounds().right + clip.getBounds().left) / 2, clip.getBounds().top - mBitmap.getHeight(), paint);


    }


    public void startAnimator() {

        ValueAnimator animator = ValueAnimator.ofFloat(0, WAVE_WIDTH);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
//
        animator.setDuration(2000);
//        animator.start();

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                dx_ = value;
                postInvalidate();
            }
        });


        animator.start();


//        ValueAnimator animatorHeight =ValueAnimator.ofFloat(BASE_HEIGHT,mHeight);
//        animatorHeight.setRepeatCount(ValueAnimator.INFINITE);
//        animatorHeight.setInterpolator(new LinearInterpolator());
//        animatorHeight.setDuration(60000);
//        animator.start();
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                dy_ = (float) animation.getAnimatedValue();
//            }
//        });


//        AnimatorSet animatorSet =new AnimatorSet();
//        animatorSet.playTogether(animator,animatorHeight);
//        animatorSet.start();

    }


}
