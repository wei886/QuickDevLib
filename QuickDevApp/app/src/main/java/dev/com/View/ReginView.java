package dev.com.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: midVictor
 * date: on 2016/12/19
 * description:
 */

public class ReginView extends View {

    private int mWidth;
    private int mHeight;

    public ReginView(Context context) {
        super(context);
    }

    public ReginView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        mWidth = w;
        mHeight = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {


        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);

        mPaint.setStyle(Paint.Style.FILL);

        Path path =new Path();

        RectF rect =new RectF(0,0,mWidth,mHeight);
        RectF rect1 =new RectF(0,0,mWidth*0.5f,mHeight*0.5f);



        mPaint.setColor(Color.BLACK);
        path.addArc(rect,0,30);
        path.arcTo(rect1,0,30);
        path.close();

        canvas.drawPath(path,mPaint);


//        canvas.drawArc(rect,0,30,false,mPaint);


    }
}
