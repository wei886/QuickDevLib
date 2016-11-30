package ui.widiget;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import quickdev.com.quicklib.R;


/**
 * Created by Administrator on 2016/9/21.
 */

public class LoadMoreWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final int TYPE_LOAD_MORE = 1000;
//    private final int TYPE_ITEM = 1001;

    public static final int LOADSTATUS_NORMAL = 0;//默认状态
    public static final int LOADSTATUS_LOADING = 1;//正在加载
    public static final int LOADSTATUS_DONE = 2;//所有数据加载完全
    public static final int LOADSTATUS_ERROR = 3;//请求异常
    public static final int LOADSTATUS_LESS_ONE = 4;//第一页数据未达到一页则不显示footer

    private int mStatus;
    private RecyclerView.Adapter mInnerAdapter;

    private String TAG = LoadMoreWrapper.class.getSimpleName();
    private LoadMoreViewHolder mFootViewHolder;

    public LoadMoreWrapper(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e(TAG,"position--="+position);
        if (getItemViewType(position) == TYPE_LOAD_MORE) {//加载更多footer
            mFootViewHolder = (LoadMoreViewHolder) holder;
            refreshFooter();
            if ((mStatus == LOADSTATUS_NORMAL || mStatus == LOADSTATUS_ERROR) && mOnLoadMoreListener != null) {
                mStatus = LOADSTATUS_LOADING;
                mOnLoadMoreListener.onLoadMoreRequested();
            }
            mFootViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnLoadMoreListener != null && mStatus == LOADSTATUS_ERROR) { //重新加载
                        setLoadStatus(LOADSTATUS_NORMAL);
                        mOnLoadMoreListener.onLoadMoreRequested();
                    }
                }
            });

        } else {
            mInnerAdapter.onBindViewHolder(holder, position);
        }
    }

    private void refreshFooter() {
        switch (getLoadStatus()) {
            case LOADSTATUS_NORMAL:
                mFootViewHolder.itemView.setVisibility(View.VISIBLE);
                mFootViewHolder.mLoadBar.setVisibility(View.VISIBLE);
                mFootViewHolder.mLoadTv.setText(R.string.quick_lv_load_more);
                break;
            case LOADSTATUS_LESS_ONE:
                mFootViewHolder.itemView.setVisibility(View.GONE);
                break;
            case LOADSTATUS_LOADING:
                mFootViewHolder.itemView.setVisibility(View.VISIBLE);
                mFootViewHolder.mLoadBar.setVisibility(View.VISIBLE);
                mFootViewHolder.mLoadTv.setText(R.string.quick_lv_load_more);
                break;
            case LOADSTATUS_DONE:
                mFootViewHolder.itemView.setVisibility(View.VISIBLE);
                mFootViewHolder.mLoadBar.setVisibility(View.GONE);
                mFootViewHolder.mLoadTv.setText(R.string.quick_lv_no_more_data);
                break;
            case LOADSTATUS_ERROR:
                mFootViewHolder.itemView.setVisibility(View.VISIBLE);
                mFootViewHolder.mLoadBar.setVisibility(View.GONE);
                mFootViewHolder.mLoadTv.setText(R.string.quick_lv_retry);
                break;
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMoreRequested();
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public LoadMoreWrapper setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null) {
            mOnLoadMoreListener = loadMoreListener;
        }
        return this;
    }


    public int getLoadStatus() {
        return mStatus;
    }

    public void setLoadStatus(int status) {
        mStatus = status;
        if (mFootViewHolder != null) {
            refreshFooter();
        }
//        else {
//            Log.e(TAG, "mFootViewHolder==null");
//            if (mInnerAdapter.getItemCount() > 0) { //刷新footer
//                notifyItemChanged(mInnerAdapter.getItemCount());
//            }
//        }

    }


    @Override
    public int getItemCount() {
        return mInnerAdapter != null && mInnerAdapter.getItemCount() > 0 ? mInnerAdapter.getItemCount() + 1 : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return isLoadMorePos(position) ? TYPE_LOAD_MORE : mInnerAdapter == null ? 0 : mInnerAdapter.getItemViewType(position);
    }

    private boolean isLoadMorePos(int position) {
        if (mInnerAdapter != null && position >= mInnerAdapter.getItemCount()) {
            return true;
        }
        return false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View inflate;
        if (viewType == TYPE_LOAD_MORE) {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.quick_recyclerview_footer, viewGroup, false);
            return new LoadMoreViewHolder(inflate);
        } else {
            return mInnerAdapter == null ? null : mInnerAdapter.onCreateViewHolder(viewGroup, viewType);
        }
    }

    private class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar mLoadBar;
        private TextView mLoadTv;

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            mLoadTv = (TextView) itemView.findViewById(R.id.load_tv);
            mLoadBar = (ProgressBar) itemView.findViewById(R.id.load_progress_bar);
        }
    }

    public void notifyInnerAdapter() {
        if (mInnerAdapter != null) {
            mInnerAdapter.notifyItemRangeChanged(0,mInnerAdapter.getItemCount());
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mInnerAdapter.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        return mInnerAdapter.onFailedToRecycleView(holder);
    }


    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        mInnerAdapter.unregisterAdapterDataObserver(observer);
    }


    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        mInnerAdapter.registerAdapterDataObserver(observer);
    }

}
