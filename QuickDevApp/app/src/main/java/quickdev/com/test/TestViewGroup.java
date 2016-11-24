package quickdev.com.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/10/25.
 */

public class TestViewGroup extends ViewGroup implements View.OnClickListener {


    final String TAG = TestViewGroup.class.getSimpleName();
    private int width;
    private int height;

    public TestViewGroup(Context context) {
        super(context);
        initView(context);

    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {

        for (int i = 0; i < 4; i++) {
            View view = new View(context);
            view.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
            view.setTag(i);
            view.setOnClickListener(this);
            view.setBackgroundColor(context.getResources().getColor(android.R.color.black));
            addView(view);

            view.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
        }

    }





    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        Log.e(TAG, "h= " + height + " w=" + width);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            if (i == 0) {
                View child0 = getChildAt(i);
                Log.e(TAG, "a=" + child0.getMeasuredHeight());
                Log.e(TAG, "b=" + child0.getMeasuredWidth());
                child0.layout(0, 0, child0.getMeasuredWidth(), child0.getMeasuredHeight());
            } else if (i == 1) {
                View child1 = getChildAt(i);
                child1.layout(width - child1.getMeasuredWidth(), 0, width, child1.getMeasuredHeight());
            } else if (i == 2) {
                View child2 = getChildAt(i);
                child2.layout(0, height - child2.getMeasuredHeight(), child2.getMeasuredWidth(), height);
            } else if (i == 3) {
                View child3 = getChildAt(i);
                child3.layout(width - child3.getMeasuredWidth(), height - child3.getMeasuredHeight(), width, height);
            }
        }
    }

    boolean isClick = false;

    @Override
    public void onClick(View v) {
        switch ((int) v.getTag()) {
            case 1:
                isClick = true;
                Log.e(TAG, "1111111111111");
                break;
            case 0:
                isClick = false;
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isClick;
    }
}
