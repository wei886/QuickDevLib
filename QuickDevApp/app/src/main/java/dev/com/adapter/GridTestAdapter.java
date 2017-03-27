package dev.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dev.com.quick.R;

/**
 * author: midVictor
 * date: on 2017/3/24
 * description:
 */

public class GridTestAdapter extends RecyclerView.Adapter {


    private final Context context;
    private final List data;

    public GridTestAdapter(Context context, List data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_grid_adapter, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder h = (Holder) holder;

        h.textView.setText(data.get(position) + "");
    }

    @Override
    public int getItemCount() {
        return null == data ? 0 : data.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

}
