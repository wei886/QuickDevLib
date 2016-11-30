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
import com.eyepetizer.ui.util.ImageUtils;

import java.util.List;

import ui.adapter.BaseRVAdapter;

/**
 * author: midVictor
 * date: on 2016/11/29
 * description:
 */

public class FeedAdapter extends BaseRVAdapter {

    private final int TYPE_ITME_TITLE = 1;
    private final int TYPE_ITME_CONTENT = 0;

    private List<DailyInfo.IssueListBean.ItemListBean> list;

//    private ArrayList<Integer> mTitlePostions = new ArrayList<>();

    public FeedAdapter(Context context, List<DailyInfo.IssueListBean.ItemListBean> list) {
        super(context);
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_ITME_TITLE) {
            return new TitleHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_hot_title, viewGroup, false));
        } else {
            return new ContentHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_hot, viewGroup, false));
        }
    }

    @Override
    public int setItemCount() {
        return list.size();
    }

    @Override
    public int setItemViewType(int position) {
        return isTitleItem(position) ? TYPE_ITME_TITLE : TYPE_ITME_CONTENT;
    }

    public boolean isTitleItem(int position) {
        DailyInfo.IssueListBean.ItemListBean itemListBean = list.get(position);
        if ("textHeader".equals(itemListBean.getType())) {
            return true;
        }
        return false;

    }


    @Override
    public void onBindHolder(RecyclerView.ViewHolder holder, int i) {

        int type = setItemViewType(i);
        if (type == TYPE_ITME_TITLE) {
            TitleHolder titleHolder = (TitleHolder) holder;
            DailyInfo.IssueListBean.ItemListBean itemListBean = list.get(i);
            titleHolder.tvTitle.setText("" + i);
            if (itemListBean != null) {
                titleHolder.tvTitle.setText(itemListBean.getData().getText());
            }
        } else if (type == TYPE_ITME_CONTENT) {
            ContentHolder h = (ContentHolder) holder;
            DailyInfo.IssueListBean.ItemListBean itemListBean = list.get(i);
            if (itemListBean != null) {
                h.tvContentTitle.setText(itemListBean.getData().getTitle());
                ImageUtils.disPLay(context, h.img, itemListBean.getData().getCover().getDetail());
            }
        }
    }


    class ContentHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tvContentTitle;

        public ContentHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            img = (ImageView) itemView.findViewById(R.id.img);
            tvContentTitle = (TextView) itemView.findViewById(R.id.tv_content_title);
        }
    }

    class TitleHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;

        public TitleHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
