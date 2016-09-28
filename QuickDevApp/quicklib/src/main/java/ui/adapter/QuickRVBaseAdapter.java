package ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/2/19.
 */
public abstract class QuickRVBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private View headerView;

    private final int TYPE_BASE_HEAD = 1;
    private final int TYPE_BASE_ITEM = 2;
    public Context context;

    private OnItemClickListener onItemClickLintener;
    private OnItemLongClickLintener onItemLongClickListener;
    private OnTouchListener onTouchListener;


    public QuickRVBaseAdapter(Context context) {
        this.context = context;
     }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        int itemType = getItemViewType(i);
        if (itemType == TYPE_BASE_HEAD) {
        } else if (itemType == TYPE_BASE_ITEM) {
            if (hasHeaderView())
                i--;
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
            if (onTouchListener != null) {
                holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        onTouchListener.onTouchListener(v, finalI, event);
                        return false;
                    }
                });
            }
            onBindHolder(holder, i);
        }
    }


    public void setOnItemClickLintener(OnItemClickListener onItemClickLintener) {
        this.onItemClickLintener = onItemClickLintener;
    }

    public void setOnLongClickLintener(OnItemLongClickLintener OnItemLongClickListener) {
        onItemLongClickListener = OnItemLongClickListener;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    public abstract void onBindHolder(RecyclerView.ViewHolder holder, int i);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_BASE_HEAD) {
            return new HeadViewHolder(headerView);
        } else {
            return onCreateHolder(viewGroup, viewType);
        }
    }


    public abstract RecyclerView.ViewHolder onCreateHolder(ViewGroup viewGroup, int viewType);

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int i) {
                    return hasHeaderView() && i == 0 ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }

    public void addHeaderView(View headerView) {
        this.headerView = headerView;
    }

    @Override
    public int getItemViewType(int position) {
        return hasHeaderView() && position == 0 ? TYPE_BASE_HEAD : TYPE_BASE_ITEM;
    }

    @Override
    public int getItemCount() {
        if (hasHeaderView())
            return setItemCount() + 1;
        return setItemCount();
    }

    public abstract int setItemCount();//data的数据个数

    public boolean hasHeaderView() {
        return headerView != null;
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {
        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClickLintener(View view, int position);
    }

    public interface OnItemLongClickLintener {
        void onItemLongCLickLintener(View view, int position);
    }

    public interface OnTouchListener {
        void onTouchListener(View view, int position, MotionEvent arg1);
    }
}
