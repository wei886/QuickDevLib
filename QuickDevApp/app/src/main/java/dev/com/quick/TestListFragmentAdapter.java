package dev.com.quick;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ui.adapter.BaseRVAdapter;

/**
 * Created by Administrator on 2016/9/28.
 */

public class TestListFragmentAdapter extends BaseRVAdapter {
    private final Context context;
    private final ArrayList list;

    public TestListFragmentAdapter(Context context, ArrayList list) {
        super(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public void onBindHolder(RecyclerView.ViewHolder holder, int i) {


        Holder h = (Holder) holder;

        h.btn.setText(list.get(i).toString());
    }

    @Override
    public int setItemViewType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup viewGroup, int viewType) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.test_item, viewGroup, false));
    }

    @Override
    public int setItemCount() {
        Log.e("cccc","size="+list.size());
        return list == null ? 0 : list.size();
    }


    class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        TextView btn;

        private void initView(View itemView) {
            btn = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
