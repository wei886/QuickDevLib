package com.eyepetizer.ui.presenter.impl;

import android.util.Log;

import com.eyepetizer.ui.http.api.ReplayApi;
import com.eyepetizer.ui.model.Replies;
import com.eyepetizer.ui.model.ReplyInfo;
import com.eyepetizer.ui.presenter.FragmentReplyPresent;

import java.util.List;

import Http.retrofit.RetrofitClient;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import ui.utils.RxUtil;

/**
 * author: midVictor
 * date: on 2016/11/29
 * description:
 */

public class FragmentReplyPresentImpl implements FragmentReplyPresent {

    private FragmentReplyPresent.View mView;
    private final CompositeSubscription mCompositeSubscription;

    public FragmentReplyPresentImpl(FragmentReplyPresent.View view) {

        mCompositeSubscription = new CompositeSubscription();
        this.mView = view;
    }


    @Override
    public void getReplyData(int id) {

        ReplayApi replayApi = RetrofitClient.INSTANCE.getRetrofit().create(ReplayApi.class);
        Observable<Replies> repliesObservable = replayApi.fetchReplies(id);

        Subscription subscribe = repliesObservable.compose(RxUtil.<Replies>applySchedulers()).subscribe(new Subscriber<Replies>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "e=" + e);
                mView.gotReplayDataFailure("获取评论失败");
            }

            @Override
            public void onNext(Replies replies) {
                Log.e(TAG, "a=" + replies);
                mView.gotReplayDataSuccess(replies);
            }
        });
        RxUtil.getNewCompositeSubIfUnsubscribed(mCompositeSubscription).add(subscribe);
    }


    @Override
    public void getReplyData(int id, int lastId) {

        final ReplayApi replayApi = RetrofitClient.INSTANCE.getRetrofit().create(ReplayApi.class);
        Observable<Replies> repliesObservable = replayApi.fetchReplies(id, lastId);

        Subscription subscribe = repliesObservable.compose(RxUtil.<Replies>applySchedulers()).subscribe(new Subscriber<Replies>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "e=" + e);
                mView.gotReplayDataFailure("获取评论失败");
            }

            @Override
            public void onNext(Replies replies) {
                Log.e(TAG, "a=" + replies);
                mView.gotReplayDataSuccess(replies);
                if (replies != null && replies.replyList != null && !replies.replyList.isEmpty()) {
                    List<ReplyInfo> replyList = replies.replyList;
                    mView.setLastReplyId(replyList.get(replyList.size() - 1).sequence);
                }
            }
        });
        RxUtil.getNewCompositeSubIfUnsubscribed(mCompositeSubscription).add(subscribe);
    }

    @Override
    public void onDestory() {
        RxUtil.unsubscribeIfNotNull(mCompositeSubscription);
    }
}
