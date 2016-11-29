package com.eyepetizer.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.eyepetizer.ui.adapter.FeedAdapter;
import com.eyepetizer.ui.model.DailyInfo;
import com.eyepetizer.ui.presenter.FragmentFeedPresent;
import com.eyepetizer.ui.presenter.impl.FragmentFeedPresentImpl;

import java.util.ArrayList;
import java.util.List;

import ui.fragment.BaseListFragment;

/**
 * author: midVictor
 * date: on 2016/11/29
 * description:
 */

public class FragmentFeed extends BaseListFragment implements FragmentFeedPresent.View {


    private List<DailyInfo.IssueListBean> mData;
    private FragmentFeedPresent mPresent;
    private FeedAdapter mFeedAdapter;

    @Override
    public RecyclerView.Adapter setAdapter() {
        mData = new ArrayList<>();
        mFeedAdapter = new FeedAdapter(getContext(), mData);
        return mFeedAdapter;
    }

    @Override
    public void onRefreshData() {
        mData.clear();
        mPresent.getDailyData();
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void initData() {
        mPresent = new FragmentFeedPresentImpl(this);
        mPresent.getDailyData();
    }

    @Override
    public void gotDataSuccess(DailyInfo daily) {
        List<DailyInfo.IssueListBean> issueList = daily.getIssueList();
        if (issueList != null) {
            if (getLoadType() == REQUEST_REFRESH) { //下拉刷新
                mData.addAll(issueList);
                resetAdapter(mFeedAdapter);
            } else {
                mData.addAll(issueList);
            }

            notifyDataRequestSuccess(issueList);
        }

    }

    @Override
    public void gotDataFailure(String error) {
        notifyDataRequestFailure();

    }
}
