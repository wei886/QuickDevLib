package dev.com.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;

/**
 * author: midVictor
 * date: on 2016/12/21
 * description:
 */

public class PathMeasureView extends BaseView {
    public PathMeasureView(Context context) {
        super(context);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        canvas.translate(mBaseWidth / 2, mBaseHeight / 2);


        Path mPath = new Path();

        int w = (int) (mBaseWidth / 2 * 0.6);


        mPath.addRect(-w,-w,w,w, Path.Direction.CW);

        canvas.drawPath(mPath ,mBasePaint);

        canvas.save();

        Path dst =new Path();

        PathMeasure pathMeasure =new PathMeasure(mPath,false);

        pathMeasure.getSegment(0,100,dst,true);

        mBasePaint.setColor(Color.YELLOW);

        canvas.drawPath(dst,mBasePaint);

        canvas.restore();

    }
}
