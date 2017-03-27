package dev.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dev.com.quick.R;
import ui.adapter.BaseRVAdapter;

/**
 * author: midVictor
 * date: on 2017/3/24
 * description:
 */

public class GridTestListAdapter extends BaseRVAdapter {


    private List data;

    public GridTestListAdapter(Context context, List data) {
        super(context);
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup viewGroup, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_grid_adapter, null));
    }

    @Override
    public int setItemCount() {
        return null == data ? 0 : data.size();
    }

    @Override
    public void onBindHolder(RecyclerView.ViewHolder holder, int position) {
        Holder h = (Holder) holder;

        h.textView.setText(data.get(position) + "");
    }

    @Override
    public int setItemViewType(int position) {
        return 0;
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
