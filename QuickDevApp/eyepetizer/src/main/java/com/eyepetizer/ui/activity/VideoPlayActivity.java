package com.eyepetizer.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TableLayout;

import com.eyepetizer.R;
import com.eyepetizer.ui.model.DailyInfo;
import com.ijkplayerlib.media.AndroidMediaController;
import com.ijkplayerlib.media.IjkVideoView;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import ui.activity.BaseActivity;

import static com.eyepetizer.ui.activity.VideoDetailActivity.KEY_ITEMLISTBEAN;

/**
 * author: midVictor
 * date: on 2016/12/12
 * description:
 */

public class VideoPlayActivity extends BaseActivity {


    private AndroidMediaController mMediaController;
    private TableLayout mHudView;
    private IjkVideoView mVideoView;
    private boolean mBackPressed;

    private DailyInfo.IssueListBean.ItemListBean mItemListBean;


    @Override
    public int setLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    public void initData(Bundle savedInstanceState) {


        if (getIntent() != null) {
            mItemListBean = getIntent().getParcelableExtra(KEY_ITEMLISTBEAN);
            if (mItemListBean.getData() != null && mItemListBean.getData().getCover() != null) {


                // init player
                mMediaController = new AndroidMediaController(this, false);

                IjkMediaPlayer.loadLibrariesOnce(null);
//                IjkMediaPlayer.native_profileBegin("libijkplayer.so");
                mVideoView.setMediaController(mMediaController);
                mVideoView.setHudView(mHudView);

                mVideoView.setVideoURI(Uri.parse(mItemListBean.getData().getPlayUrl()));

                mVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(IMediaPlayer mp) {
                        mVideoView.start();
                    }
                });

            }
        }
    }

    @Override
    public void initView() {

        mVideoView = findView(R.id.ijk_video_view);
        mHudView = (TableLayout) findViewById(R.id.hud_view);
    }

    @Override
    public void onBackPressed() {
        mBackPressed = true;
        super.onBackPressed();

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBackPressed || !mVideoView.isBackgroundPlayEnabled()) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
        } else {
            mVideoView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }
}
