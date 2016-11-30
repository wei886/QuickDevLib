package ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/19.
 */
public abstract class BaseRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //    private final int TYPE_BASE_ITEM = -302;
    private final int TYPE_BASE_HEAD = -300;
    private final int TYPE_BASE_FOOTER = -301;
    public Context context;

    private OnItemClickListener onItemClickLintener;
    private OnItemLongClickLintener onItemLongClickListener;

    private ArrayList<View> mHeadViews = new ArrayList<>();
    private ArrayList<Integer> mHeadItemViewTypes = new ArrayList<>(); //head的 itemtype
    //    private ArrayList<View> mFooterViews;
    private View mFooterView;
    private String TAG = BaseRVAdapter.class.getSimpleName();

    public BaseRVAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
//        Log.e("TAG", "onBindViewHolder_i=" + i);
        int itemType = getItemViewType(i);
        if (itemType == setItemViewType(i)) {
            i -= getHeadSize();
            final int finalI = i;
            if (onItemClickLintener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickLintener.onItemClickLintener(v, finalI);
                    }
                });
            }
            if (onItemLongClickListener != null) {
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onItemLongClickListener.onItemLongCLickLintener(v, finalI);
                        return true;
                    }
                });
            }

            onBindHolder(holder, i);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_BASE_FOOTER) {
            return new SimpleHolder(mFooterView);
        } else if (isHeadItemViewType(viewType)) {
            View header = getHeaderByViewType(viewType);
            return header == null ? null : new SimpleHolder(header);
        } else {
            return onCreateHolder(viewGroup, viewType);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return isHeader(position) ? getHeadItemViewType(position) : isFooter(position) ? TYPE_BASE_FOOTER : setItemViewType(position);
    }

    private boolean isHeadItemViewType(int viewType) {
        return mHeadItemViewTypes.contains(viewType);
    }

    private int getHeadItemViewType(int position) {
        return !mHeadItemViewTypes.isEmpty() && mHeadItemViewTypes.size() > position ? mHeadItemViewTypes.get(position) : setItemViewType(position);
    }

    private View getHeaderByViewType(int viewType) {
        if (isHeadItemViewType(viewType)) {
            int index = viewType - TYPE_BASE_HEAD - 1;
            if (index < mHeadViews.size()) {
                return mHeadViews.get(index);
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
//        Log.e(TAG,"BBBBB=" +setItemCount() + getHeadSize() + getFooterSize());
        return setItemCount() + getHeadSize() + getFooterSize();
    }


    public abstract RecyclerView.ViewHolder onCreateHolder(ViewGroup viewGroup, int viewType);

    public abstract int setItemCount();//data的数据个数

    public abstract void onBindHolder(RecyclerView.ViewHolder holder, int i);

    public abstract int setItemViewType(int position);

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int i) {
                    return isHeader(i) || isFooter(i) ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }

    public void addHeader(View headerView) {
        mHeadViews.add(headerView);
        mHeadItemViewTypes.add(TYPE_BASE_HEAD + mHeadViews.size());
    }


    public void addFooter(View footerView) {
        mFooterView = footerView;
    }

    private boolean isHeader(int position) {
        return mHeadViews.size() > position;
    }

    private int getHeadSize() {
        return mHeadViews.size();
    }

    private int getFooterSize() {
        return mFooterView == null ? 0 : 1;
    }

    private boolean isFooter(int position) {
        return mFooterView != null && position >= getItemCount() - 1;
    }


    public static class SimpleHolder extends RecyclerView.ViewHolder {
        public SimpleHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClickLintener(View view, int position);
    }

    public interface OnItemLongClickLintener {
        void onItemLongCLickLintener(View view, int position);
    }

    public void setOnItemClickLintener(OnItemClickListener onItemClickLintener) {
        this.onItemClickLintener = onItemClickLintener;
    }

    public void setOnLongClickLintener(OnItemLongClickLintener OnItemLongClickListener) {
        onItemLongClickListener = OnItemLongClickListener;
    }
}
