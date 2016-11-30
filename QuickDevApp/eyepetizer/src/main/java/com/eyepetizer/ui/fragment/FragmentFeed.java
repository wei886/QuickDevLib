package com.eyepetizer.ui.fragment;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eyepetizer.R;
import com.eyepetizer.ui.activity.VideoDetailActivity;
import com.eyepetizer.ui.adapter.FeedAdapter;
import com.eyepetizer.ui.model.DailyInfo;
import com.eyepetizer.ui.presenter.FragmentFeedPresent;
import com.eyepetizer.ui.presenter.impl.FragmentFeedPresentImpl;

import java.util.ArrayList;
import java.util.List;

import ui.adapter.BaseRVAdapter;
import ui.fragment.BaseSpecialListFragment;

import static com.eyepetizer.ui.activity.VideoDetailActivity.KEY_ITEMLISTBEAN;

/**
 * author: midVictor
 * date: on 2016/11/29
 * description:
 */

public class FragmentFeed extends BaseSpecialListFragment implements FragmentFeedPresent.View {


    private FragmentFeedPresent mPresent;
    private FeedAdapter mFeedAdapter;
    private long mDateTime;
    List<DailyInfo.IssueListBean.ItemListBean> mData = new ArrayList();

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
        mPresent.getDailyData(mDateTime);
    }

    @Override
    public void initData() {
        mPresent = new FragmentFeedPresentImpl(this);
        mPresent.getDailyData();

        mFeedAdapter.setOnItemClickLintener(new BaseRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClickLintener(View view, int position) {
                DailyInfo.IssueListBean.ItemListBean itemListBean = mData.get(position);
                if ("textHeader".equals(itemListBean.getType())) {
                } else {
                    Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
                    intent.putExtra(KEY_ITEMLISTBEAN, itemListBean);
                    startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view,
                                    getString(R.string.trannsition_item_video)).toBundle());

                }
            }
        });

    }

    @Override
    public void gotDataSuccess(DailyInfo daily) {

        if (daily != null) {
            List<DailyInfo.IssueListBean> issueList = daily.getIssueList();
            if (issueList != null && !issueList.isEmpty()) {
                for (int i = 0; i < issueList.size(); i++) {
                    DailyInfo.IssueListBean issueListBean = issueList.get(i);
                    if (issueListBean != null) {
                        List<DailyInfo.IssueListBean.ItemListBean> itemList = issueListBean.getItemList();
                        mData.addAll(itemList);
                    }

                }
            }
            notifyDataRequestSuccess(issueList);
            String nextPageUrl = daily.getNextPageUrl();
            String date = nextPageUrl.substring(nextPageUrl.indexOf("=") + 1,
                    nextPageUrl.indexOf("&"));
            try {
                mDateTime = Long.parseLong(date);
            } catch (Exception e) {
            }
        } else {
            notifyDataRequestSuccess(null);
        }

    }

    @Override
    public void gotDataFailure(String error) {
        notifyDataRequestFailure();

    }
}
