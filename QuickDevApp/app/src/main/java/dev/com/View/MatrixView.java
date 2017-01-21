package dev.com.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import dev.com.quick.R;


/**
 * author: midVictor
 * date: on 2017/1/21
 * description:
 */

public class MatrixView extends View {

    private Bitmap mBitmap;
    private int width;
    private int height;
    private Region regin1;
    private Region regin2;
    private Region regin3;
    private Region regin4;
    private Rect rect1;
    private Rect rect2;
    private Rect rect3;
    private Rect rect4;

    public MatrixView(Context context) {
        super(context);
        init(context);
    }

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.landing_background);
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);


        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);

        Matrix scale = new Matrix();

        scale.setScale((float) width / (float) mBitmap.getWidth(), (float) height / (float) mBitmap.getHeight());
        Bitmap bit;
        bit = mBitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), scale, false);
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setScale(0.5f, 0.5f);
        canvas.drawBitmap(bit, matrix, paint);

        Matrix polyMatrix = new Matrix();

        float src[] = new float[]{

        };

        float dst[] = new float[]{

        };


        polyMatrix.setPolyToPoly(src, 0, dst, 0, src.length);

        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);


        rect1 = new Rect(0, 0, circleWidth, circleWidth);
        regin1 = new Region(rect1);


        rect2 = new Rect(bit.getWidth() - circleWidth, 0, bit.getWidth(), circleWidth);
        regin2 = new Region(rect2);


        rect3 = new Rect(0, bit.getHeight() - circleWidth, circleWidth, bit.getHeight());
        regin3 = new Region(rect3);


        rect4 = new Rect(bit.getWidth() - circleWidth, bit.getHeight() - circleWidth, bit.getWidth(), bit.getHeight());
        regin4 = new Region(rect4);


        canvas.drawCircle((rect1.right + rect1.left) / 2, (rect1.bottom + rect1.top) / 2, circleRidus, paint);
        canvas.drawCircle((rect2.right + rect2.left) / 2, (rect2.bottom + rect2.top) / 2, circleRidus, paint);
        canvas.drawCircle((rect3.right + rect3.left) / 2, (rect3.bottom + rect3.top) / 2, circleRidus, paint);
        canvas.drawCircle((rect4.right + rect4.left) / 2, (rect4.bottom + rect4.top) / 2, circleRidus, paint);
//        canvas.drawCircle(0, bit.getHeight(), circleWidth, paintCircle);
//        canvas.drawCircle(bit.getWidth(), bit.getHeight(), circleWidth, paintCircle);
//        canvas.drawCircle(bit.getWidth(), bit.getHeight(), circleWidth, paintCircle);


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        if (width < height) {
            height = width;
        }

        setMeasuredDimension(width, height);


    }


    private Rect clickrect;

    int circleRidus = 20;
    int circleWidth = circleRidus * 2;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Rect orgionRect = null;

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                clickrect = handleCicle(event.getX(), event.getY());
                if (clickrect != null) {
                    clickrect.set(clickrect);
                }
                break;
            case MotionEvent.ACTION_MOVE:

                if (clickrect == null) {
                    return true;
                }

                clickrect.set((int) (event.getX() - circleRidus), (int) (event.getY() - circleRidus),
                        (int) (event.getX() + circleRidus), (int) (event.getY() + circleRidus));
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                clickrect = null;
                clickrect.set(orgionRect);
                invalidate();
                break;

        }

        return super.onTouchEvent(event);
    }

    private Rect handleCicle(float x, float y) {
        if (containPoint(rect1, x, y)) {
            return rect1;
        } else if (containPoint(rect2, x, y)) {
            return rect2;
        } else if (containPoint(rect3, x, y)) {
            return rect3;
        } else if (containPoint(rect4, x, y)) {
            return rect4;
        }
        return null;
    }

    private boolean containPoint(Rect rect, float x, float y) {
        return rect.contains((int) y, (int) x);
    }
}
