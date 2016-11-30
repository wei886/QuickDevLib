package com.eyepetizer.ui.activity;

import android.content.Intent;
import android.widget.ImageView;

import com.eyepetizer.R;
import com.eyepetizer.ui.model.DailyInfo;
import com.eyepetizer.ui.util.ImageUtils;

import ui.activity.BaseActivity;

/**
 * author: midVictor
 * date: on 2016/11/30
 * description:
 */

public class VideoDetailActivity extends BaseActivity {

    public static String KEY_ITEMLISTBEAN = "ItemListBean";
    private ImageView mImgVideoCover;


    @Override
    public int setLayoutId() {
        return R.layout.activity_video_detail;
    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        if (getIntent() != null) {
            DailyInfo.IssueListBean.ItemListBean itemListBean = getIntent().getParcelableExtra(KEY_ITEMLISTBEAN);
            ImageUtils.disPLay(VideoDetailActivity.this, mImgVideoCover, itemListBean.getData().getCover().getDetail());
        }
    }

    @Override
    public void initView() {

        mImgVideoCover = findView(R.id.img_video_cover);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finishAfterTransition();
    }
}
