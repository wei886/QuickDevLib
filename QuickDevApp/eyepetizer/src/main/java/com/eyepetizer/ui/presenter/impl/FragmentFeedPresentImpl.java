package com.eyepetizer.ui.presenter.impl;

import android.util.Log;

import com.eyepetizer.ui.http.api.DailyApi;
import com.eyepetizer.ui.model.DailyInfo;
import com.eyepetizer.ui.presenter.FragmentFeedPresent;

import Http.retrofit.RetrofitClient;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import ui.utils.RxUtil;

/**
 * author: midVictor
 * date: on 2016/11/29
 * description:
 */

public class FragmentFeedPresentImpl implements FragmentFeedPresent {


    private View view;
    private CompositeSubscription mComsiteSubscription;

    public FragmentFeedPresentImpl(View view) {
        this.view = view;
        mComsiteSubscription = RxUtil.getNewCompositeSubIfUnsubscribed(mComsiteSubscription);
    }


    @Override
    public void getDailyData() {
         DailyApi dailyApi = RetrofitClient.INSTANCE.getRetrofit().create(DailyApi.class);

        Subscription subscribe = dailyApi.getDaily().compose(RxUtil.<DailyInfo>applySchedulers()).subscribe(new Subscriber<DailyInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "FragmentFeedPresentImpl_getDailyData" + e.getMessage().toString());
                view.gotDataFailure("获取每日数据失败");
            }

            @Override
            public void onNext(DailyInfo daily) {
                view.gotDataSuccess(daily);
            }
        });


        mComsiteSubscription.add(subscribe);

    }

    @Override
    public void getDailyData(long date) {
        DailyApi dailyApi = RetrofitClient.INSTANCE.getRetrofit().create(DailyApi.class);
        Subscription subscribe = dailyApi.getDaily(date).compose(RxUtil.<DailyInfo>applySchedulers()).subscribe(new Subscriber<DailyInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "FragmentFeedPresentImpl_getDailyData1 e=" + e.getMessage().toString());
                view.gotDataFailure("获取每日数据失败");
            }

            @Override
            public void onNext(DailyInfo dailyInfo) {
                view.gotDataSuccess(dailyInfo);

            }
        });

        mComsiteSubscription.add(subscribe);


    }

    @Override
    public void onDestory() {
        RxUtil.unsubscribeIfNotNull(mComsiteSubscription);
    }
}
