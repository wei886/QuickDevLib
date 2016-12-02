package com.eyepetizer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eyepetizer.R;
import com.eyepetizer.ui.model.ReplyInfo;
import com.eyepetizer.ui.util.ImageUtils;

import java.util.List;

import ui.adapter.BaseRVAdapter;

/**
 * author: midVictor
 * date: on 2016/12/1
 * description:
 */

public class ReplyAdatper extends BaseRVAdapter {

    private List<ReplyInfo> data;

    public ReplyAdatper(Context context, List data) {
        super(context);
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup viewGroup, int viewType) {
        return new ReplayHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_replay, viewGroup, false));
    }

    @Override
    public int setItemCount() {
        return data.size();
    }

    @Override
    public void onBindHolder(RecyclerView.ViewHolder holder, int i) {
            ReplyInfo replyInfo = data.get(i);
            ReplayHolder replayHolder = (ReplayHolder) holder;

            if (replyInfo != null) {
                if (replyInfo.user != null) {

                    ImageUtils.INSTANCE.disPLayCircle(context, replayHolder.imgAvatar, replyInfo.user.avatar);
                    replayHolder.tvName.setText(replyInfo.user.nickname);
                }
                replayHolder.tvContent.setText(replyInfo.message);

            }
    }

    @Override
    public int setItemViewType(int position) {
        return 0;
    }

    class ReplayHolder extends RecyclerView.ViewHolder {


        TextView tvName;
        TextView tvContent;
        ImageView imgAvatar;

        public ReplayHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {

            imgAvatar = (ImageView) itemView.findViewById(R.id.img_avatar);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);


        }
    }

}
