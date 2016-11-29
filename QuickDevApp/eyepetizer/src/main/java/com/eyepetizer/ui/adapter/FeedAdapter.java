package com.eyepetizer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eyepetizer.R;
import com.eyepetizer.ui.model.DailyInfo;

import java.util.List;

import ui.adapter.BaseRVAdapter;

/**
 * author: midVictor
 * date: on 2016/11/29
 * description:
 */

public class FeedAdapter extends BaseRVAdapter {
    private List<DailyInfo.IssueListBean> list;

    public FeedAdapter(Context context, List<DailyInfo.IssueListBean> list) {
        super(context);
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup viewGroup, int viewType) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_feed, viewGroup, false));
    }

    @Override
    public int setItemCount() {
        return list.size();
    }

    @Override
    public void onBindHolder(RecyclerView.ViewHolder holder, int i) {

        Holder h= (Holder) holder;
//        Daily daily = list.get(i);
//        if(daily !=null)
        h.tv.setText("aaa");




    }

    class Holder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv;

        public Holder(View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            img = (ImageView) itemView.findViewById(R.id.img);
            tv = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
