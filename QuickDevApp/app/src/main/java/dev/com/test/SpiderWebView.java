package dev.com.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: midVictor
 * date: on 2016/12/20
 * description:
 */

public class SpiderWebView extends View {

    private int mWidth, mHeight;
    private double COS60 = Math.cos(Math.PI / 3);
    private double SIN60 = Math.sin(Math.PI / 3);


    public SpiderWebView(Context context) {
        super(context);
        init();
    }


    public SpiderWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

    }

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            invalidate();
            return false;
        }
    });

    int mAngle = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#cccccc"));

        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.rotate((float) (mAngle));

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        float zhouchang = mWidth * 0.8f;

        int COUNT = 8;
        float perRaius = zhouchang / (COUNT * 2); //每一段的半径

        float oneX = 0;
        float oneY = 0;
        float twoX = 0;
        float twoY = 0;
        float threeX = 0;
        float fourX = 0;
        float sixY = 0;
        float sixX = 0;
        float fourY = 0;
        float fiveX = 0;
        float fiveY = 0;
        float threeY = 0;

        for (int i = 1; i <= COUNT; i++) {
            oneX = perRaius * i;
            oneY = 0;
            twoX = (float) (perRaius * i * COS60);
            twoY = (float) (perRaius * i * SIN60);

            threeX = (float) (-perRaius * i * COS60);
            threeY = (float) (perRaius * i * SIN60);

            fourX = -perRaius * i;
            fourY = 0;
            fiveX = (float) (-perRaius * i * COS60);
            fiveY = (float) (-perRaius * i * SIN60);

            sixX = (float) (perRaius * i * COS60);
            sixY = (float) (-perRaius * i * SIN60);
            Path path = new Path();
            path.moveTo(oneX, oneY);
            path.lineTo(twoX, twoY);
            path.lineTo(threeX, threeY);
            path.lineTo(fourX, fourY);
            path.lineTo(fiveX, fiveY);
            path.lineTo(sixX, sixY);
            path.close();
            canvas.drawPath(path, paint);


        }

        canvas.drawLine(oneX, oneY, fourX, fourY, paint);
        canvas.drawLine(twoX, twoY, fiveX, fiveY, paint);
        canvas.drawLine(threeX, threeY, sixX, sixY, paint);


        int TEXT_LENGTH = 160;
        paint.setTextSize(32);


        Path path = new Path();
        path.moveTo(oneX, oneY);
        path.lineTo((oneX + TEXT_LENGTH), oneY);
        canvas.drawTextOnPath("AADDFSFa", path, 0, 10, paint);

        path.reset();
        path.moveTo(twoX, twoY);
        path.lineTo((float) (twoX + TEXT_LENGTH * COS60), (float) (twoY + TEXT_LENGTH * SIN60));
        canvas.drawTextOnPath("bBSD33423", path, 0, 10, paint);


        path.reset();
        path.moveTo(threeX, threeY);
        path.lineTo((float) (threeX - TEXT_LENGTH * COS60), (float) (threeY + TEXT_LENGTH * SIN60));
        canvas.drawTextOnPath("很好", path, 0, 10, paint);

        path.reset();
        path.moveTo(fourX, fourY);
        path.lineTo(fourX - TEXT_LENGTH, fourY);
        canvas.drawTextOnPath("d", path, 0, 10, paint);

        path.reset();
        path.moveTo(fiveX, fiveY);
        path.lineTo((float) (fiveX - TEXT_LENGTH * COS60), (float) (fiveY - TEXT_LENGTH * SIN60));
        canvas.drawTextOnPath("高效啊e", path, 0, 10, paint);

        path.reset();
        path.moveTo(sixX, sixY);
        path.lineTo((float) (sixX + TEXT_LENGTH * COS60), (float) (sixY - TEXT_LENGTH * SIN60));
        canvas.drawTextOnPath("fdddd", path, 0, 10, paint);



        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAngle >= 360) {
                    mAngle = 0;
                } else
                    mAngle += 6;
                mHandler.sendEmptyMessage(mAngle);
            }
        }, 1000);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);

        int MIN_ = 420;

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
