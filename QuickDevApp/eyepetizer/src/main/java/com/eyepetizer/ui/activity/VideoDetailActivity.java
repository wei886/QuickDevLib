package com.eyepetizer.ui.activity;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.eyepetizer.R;
import com.eyepetizer.ui.fragment.FragmentReply;
import com.eyepetizer.ui.model.DailyInfo;
import com.eyepetizer.ui.util.ImageUtils;

import ui.activity.BaseActivity;
import ui.utils.StatusBarUtils;

/**
 * author: midVictor
 * date: on 2016/11/30
 * description:
 */

public class VideoDetailActivity extends BaseActivity {

    public static String KEY_ITEMLISTBEAN = "ItemListBean";
    private ImageView mImgVideoCover;

    private final String FRAGMENT_TAG = "replay_fragment";
    private DailyInfo.IssueListBean.ItemListBean mItemListBean;


    @Override
    public int setLayoutId() {
        return R.layout.activity_video_detail;
    }


    @Override
    public void initData(Bundle savedInstanceState) {


        if (getIntent() != null) {
            mItemListBean = getIntent().getParcelableExtra(KEY_ITEMLISTBEAN);
            ImageUtils.INSTANCE.disPLay(VideoDetailActivity.this, mImgVideoCover, mItemListBean.getData().getCover().getDetail());
        }


        if (mItemListBean == null) {
            finish();
        }

        Fragment fragmentByTag = null;

        if (savedInstanceState != null) {
            fragmentByTag = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        }
        if (fragmentByTag == null) {

            fragmentByTag = new FragmentReply(mItemListBean);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentByTag, FRAGMENT_TAG).commit();
        setupWindowTransition();

        setupBackImg();

    }

    private void setupBackImg() {
        ImageView imgBack = new ImageView(this);
        imgBack.setBackgroundResource(R.drawable.ic_back);
        RelativeLayout mRlRoot = findView(R.id.rl_root);
        mRlRoot.addView(imgBack);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imgBack.getLayoutParams();
        layoutParams.topMargin = StatusBarUtils.getStatusBarOffsetPx(this) + 6;
        layoutParams.leftMargin = 12;
        imgBack.setLayoutParams(layoutParams);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });
    }

    @Override
    public void initView() {

        mImgVideoCover = findView(R.id.img_video_cover);

    }

    private void setupWindowTransition() {
//
//        ChangeBounds ts =new ChangeBounds();
//
//        ts.addListener(new Transition.TransitionListener() {
//            @Override
//            public void onTransitionStart(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionEnd(Transition transition) {
//                animateRevealShow(mImgVideoCover);
//            }
//
//            @Override
//            public void onTransitionCancel(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionPause(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionResume(Transition transition) {
//
//            }
//        });
//
//        getWindow().setSharedElementEnterTransition(ts);


        mImgVideoCover.getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
    }

    ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            animateRevealShow(mImgVideoCover);
        }
    };


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealShow(View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        int finalRadius = Math.max(viewRoot.getWidth(), viewRoot.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, finalRadius / 3, finalRadius);
        viewRoot.setVisibility(View.VISIBLE);
        anim.setDuration(1000);
        anim.start();

        mImgVideoCover.getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //设置一个显示的动画
//                mImgVideoCover.animate().alpha(1).setStartDelay(100).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finishAfterTransition();
    }
}
