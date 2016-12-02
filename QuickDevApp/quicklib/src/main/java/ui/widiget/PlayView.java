package ui.widiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import ui.utils.StatusBarUtils;

/**
 * author: midVictor
 * date: on 2016/12/2
 * description:
 */

public class PlayView extends View {

    Paint mPaintOutLine, mPaintFill, mPaintShadow;
    int mWidth, mHeight, mCenterX, mCenterY;

    String TAG = "PlayView";

    public PlayView(Context context) {
        super(context);
        init(context);
    }

    public PlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {


        mPaintOutLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintOutLine.setAntiAlias(true);
        mPaintOutLine.setStrokeWidth(8);
        mPaintOutLine.setColor(Color.BLACK);
        mPaintOutLine.setStyle(Paint.Style.STROKE);

        mPaintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFill.setAntiAlias(true);
        mPaintFill.setColor(Color.GRAY);
        mPaintFill.setStyle(Paint.Style.FILL);


        mPaintShadow = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintShadow.setAntiAlias(true);
        mPaintShadow.setColor(Color.parseColor("#cccccc"));
        mPaintShadow.setStyle(Paint.Style.FILL);
        mPaintShadow.setStyle(Paint.Style.FILL);


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;

        mCenterX = w / 2;
        mCenterY = h / 2;

        Log.e(TAG, "w=" + w);
        Log.e(TAG, "onSizeChanged=" + mCenterX);

    }

    int RECT_width = 320;
    int RECT_height = 240;

    @Override
    protected void onDraw(Canvas canvas) {

        Log.e(TAG, "onDraw=" + mCenterX);
        Log.e(TAG, "onDraw=" + (float) (30 * Math.PI / 180));

        canvas.translate(0, StatusBarUtils.getStatusBarOffsetPx(getContext()));

        RectF rect = new RectF();

        rect.left = mCenterX - RECT_width / 2;
        rect.right = mCenterX + RECT_width / 2;
        rect.top = mCenterY - RECT_height / 2;
        rect.bottom = mCenterY + RECT_height / 2;
        canvas.save();


        canvas.rotate(-4, mCenterX, mCenterY);


        //两个外切圆 半径比6:7
        float rectCenterX = (rect.left + rect.right) / 2;
        int twoWidth = RECT_width - 96;
        int oneCircle = (int) (twoWidth * 6f / 26);
        int twoCircle = (int) (twoWidth * 7f / 26);

        canvas.drawCircle(rectCenterX - oneCircle, rect.top - oneCircle, oneCircle, mPaintFill);
        canvas.drawCircle(rectCenterX + twoCircle, rect.top - twoCircle, twoCircle, mPaintFill);

        //画矩形

        canvas.drawRoundRect(rect, 24, 24, mPaintFill);
        canvas.drawRoundRect(rect, 24, 24, mPaintOutLine);

        canvas.restore();



        //画腿

        int Leg_Right = 148;
        int Leg_Left = 136;

        Path leg = new Path();
        leg.moveTo(rectCenterX, rect.bottom);
        leg.lineTo((float) (rectCenterX + Leg_Right * Math.sin(30)), (float) (rect.bottom + (Leg_Right * Math.cos(30))));
        canvas.drawPath(leg,mPaintOutLine);

        leg.moveTo(rectCenterX, rect.bottom);
        leg.lineTo((float) (rectCenterX - Leg_Left * Math.sin(30)), (float) (rect.bottom + (Leg_Left * Math.cos(30))));
        canvas.drawPath(leg,mPaintOutLine);


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int size = MeasureSpec.getSize(widthMeasureSpec);
        int size1 = MeasureSpec.getSize(heightMeasureSpec);

        Log.e(TAG, "size=" + size);
        Log.e(TAG, "size1=" + size1);

        setMeasuredDimension(size, size1);
    }
}
