package com.eyepetizer.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.eyepetizer.R;

import ui.activity.BaseActivity;

public class SplashActivity extends BaseActivity {


    private ImageView mImgEyeInner;
    private ImageView mImgEyeOuter;
    private AnimatorSet animatorSet;
    private View mRlBg;

    @Override
    public int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(mRlBg, "alpha", 0.6f, 0.8f, 1, 1),
                ObjectAnimator.ofFloat(mRlBg, "scaleX", 1f, 1.05f, 1.1f, 1.02f, 1f),
                ObjectAnimator.ofFloat(mRlBg, "scaleY", 1f, 1.05f, 1.1f, 1.02f, 1f));
        animatorSet.setDuration(1000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();

    }

    @Override
    public void initView() {
        mRlBg = findView(R.id.rl_bg);
        mImgEyeInner = findView(R.id.img_eye_inner);
        mImgEyeOuter = findView(R.id.img_eye_outer);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (animatorSet != null) {
            animatorSet.cancel();
        }
    }
}
