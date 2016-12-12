package com.eyepetizer.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.eyepetizer.R;
import com.eyepetizer.ui.fragment.FragmentCategory;
import com.eyepetizer.ui.fragment.FragmentFeed;
import com.eyepetizer.ui.fragment.FragmentPgc;
import com.eyepetizer.ui.fragment.FragmentProfile;

import java.util.ArrayList;
import java.util.List;

import common.ToastUtils;
import ui.activity.BaseActivity;

public class HomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    private RadioGroup mRgHome;
    private List<Fragment> mFragments = new ArrayList<>();

    private String[] TAG_FRAGMENT =
            {"TAG_FEED", "TAG_PGC", "TAG_CATEGORY", "TAG_PROFILE"};

    private int mCurrentPos = 0;
    private Handler testHandle = new Handler();

    @Override
    public int setLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mRgHome.setOnCheckedChangeListener(this);


        mFragments.add(new FragmentFeed());
        mFragments.add(new FragmentPgc());
        mFragments.add(new FragmentCategory());
        mFragments.add(new FragmentProfile());


        getSupportFragmentManager().beginTransaction().add(R.id.container, mFragments.get(mCurrentPos), TAG_FRAGMENT[mCurrentPos]).commit();


        testHandle.post(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ToastUtils.toast(HomeActivity.this, "ddsdsd");
                    }
                });
            }
        });



    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        ToastUtils.toast(HomeActivity.this, "x");

    }

    @Override
    public void initView() {

        mRgHome = findView(R.id.rg_home);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_one:
                replaceFragment(0);
                break;
            case R.id.rb_two:
                replaceFragment(1);
                break;
            case R.id.rb_three:
                replaceFragment(2);
                break;
            case R.id.rb_four:
                replaceFragment(3);
                break;
        }
    }


    /**
     * 设置Fragment
     *
     * @param position
     */
    private void replaceFragment(int position) {
        if (position != mCurrentPos) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.hide(mFragments.get(mCurrentPos));

            Fragment fragment = mFragments.get(position);
            if (fragment.isAdded()) {
                fragmentTransaction.show(fragment).commit();
            } else {
                fragmentTransaction.add(R.id.container, fragment, TAG_FRAGMENT[position]).commit();
            }
            mCurrentPos = position;
        }
    }


}
