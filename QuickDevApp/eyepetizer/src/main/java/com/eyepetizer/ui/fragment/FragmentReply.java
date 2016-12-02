package com.eyepetizer.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.eyepetizer.R;
import com.eyepetizer.ui.adapter.ReplyAdatper;
import com.eyepetizer.ui.model.DailyInfo;
import com.eyepetizer.ui.model.Replies;
import com.eyepetizer.ui.model.ReplyInfo;
import com.eyepetizer.ui.presenter.FragmentReplyPresent;
import com.eyepetizer.ui.presenter.impl.FragmentReplyPresentImpl;

import java.util.ArrayList;
import java.util.List;

import common.ToastUtils;
import ui.fragment.BaseListFragment;

/**
 * author: midVictor
 * date: on 2016/12/1
 * description:
 */

public class FragmentReply extends BaseListFragment implements FragmentReplyPresent.View {


    private ReplyAdatper mAdatper;
    private DailyInfo.IssueListBean.ItemListBean mBean;
    private List mData;
    private FragmentReplyPresent mPresent;
    private int mLastReply;

    public FragmentReply() {

    }

    public FragmentReply(DailyInfo.IssueListBean.ItemListBean bean) {
        mBean = bean;
    }

    @Override
    public RecyclerView.Adapter setAdapter() {
        mData = new ArrayList();
        mAdatper = new ReplyAdatper(getActivity(), mData);
        return mAdatper;
    }

    @Override
    public void onRefreshData() {
        mData.clear();
        mPresent.getReplyData(mBean.getData().getId());
    }

    @Override
    public void onLoadMore() {
        mPresent.getReplyData(mBean.getData().getId(), mLastReply);
    }

    @Override
    public void initData() {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_layout_replay_head, null);
        TextView tvReplayHeader = (TextView) header.findViewById(R.id.tv_replay_header);
        tvReplayHeader.setText( mBean.getData().getTitle() + "\n"
                + mBean.getData().getDescription());
        mAdatper.addHeader(header);
        mPresent = new FragmentReplyPresentImpl(this);

        mPresent.getReplyData(mBean.getData().getId());
    }

    @Override
    public void gotReplayDataSuccess(Replies replies) {

        if (replies != null) {
            List<ReplyInfo> replyList = replies.replyList;
            if (replyList != null) {
                mData.addAll(replyList);
            }
            notifyDataRequestSuccess(replyList);
        } else {
            notifyDataRequestSuccess(null);
        }


    }

    @Override
    public void gotReplayDataFailure(String msg) {
        ToastUtils.toast(getActivity(), msg);

    }

    @Override
    public void setLastReplyId(int id) {
        mLastReply = id;
    }
}
