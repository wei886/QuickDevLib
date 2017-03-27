package ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import dev.com.quicklib.R;
import ui.adapter.BaseRVAdapter;
import ui.common.CommonConfig;
import ui.fragment.BaseListFragment;
import ui.widiget.LoadMoreWrapper;

/**
 * author: midVictor
 * date: on 2017/3/24
 * description:
 */

public abstract class BaseGridListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private LoadMoreWrapper mLoadMoreWrapper;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private final String TAG = BaseListFragment.class.getSimpleName();

    public final int REQUEST_REFRESH = 0; //下拉刷新
    public final int REQUEST_lOAD_MORE = 1; //加载更多
    private int mLoadType = REQUEST_REFRESH;
    private RecyclerView mRecyclerView;
    private int spanCount = 1;


    @Override
    public int setLayoutId() {
        return R.layout.base_list_content;
    }


    /**
     * 设置adapter
     *
     * @return
     */
    public abstract RecyclerView.Adapter setAdapter();

    /**
     * 下拉刷新
     */
    public abstract void onRefreshData();

    /**
     * 加载更多
     */
    public abstract void onLoadMore();

    /**
     * 每行几列
     *
     * @return
     */
    public abstract int setSpanCount();


    @Override
    public void initView() {
        setupView();
        if (setAdapter() != null) {
            mLoadMoreWrapper = new LoadMoreWrapper(setAdapter());
            mRecyclerView.setAdapter(mLoadMoreWrapper);
            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    if (mSwipeRefreshLayout.isRefreshing()) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                    mLoadType = REQUEST_lOAD_MORE;
                    onLoadMore();
                }
            });
        }
        mSwipeRefreshLayout.setRefreshing(true);
    }

    /**
     * 请求数据成功通知刷新footer状态
     *
     * @param ResponseList
     */
    public void notifyDataRequestSuccess(List ResponseList) {
        if (mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);
        if (null != ResponseList) {
            if (ResponseList.size() < CommonConfig.PAGE_SIZE) {
                mLoadMoreWrapper.setLoadStatus(LoadMoreWrapper.LOADSTATUS_DONE);//全部加载完成
            } else {
                mLoadMoreWrapper.setLoadStatus(LoadMoreWrapper.LOADSTATUS_NORMAL);
            }
        } else {
            mLoadMoreWrapper.setLoadStatus(LoadMoreWrapper.LOADSTATUS_DONE);
        }
        mLoadMoreWrapper.notifyInnerAdapter();
    }

    /**
     * 请求数据失败通知刷新footer状态
     */
    public void notifyDataRequestFailure() {
        if (mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);
        if (mLoadMoreWrapper != null) {
            mLoadMoreWrapper.setLoadStatus(LoadMoreWrapper.LOADSTATUS_ERROR);
        }
    }

    /**
     * 设置adapter
     *
     * @param adapter
     */
    public void resetAdapter(RecyclerView.Adapter adapter) {
        mLoadMoreWrapper = new LoadMoreWrapper(adapter);
        mRecyclerView.setAdapter(mLoadMoreWrapper);
        mLoadMoreWrapper.notifyInnerAdapter();
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                mLoadType = REQUEST_lOAD_MORE;
                onLoadMore();
            }
        });
    }

    private void setupView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.black));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setProgressViewOffset(true, 0, getResources().getDimensionPixelSize(R.dimen.quick_srlayout_offset));
        if (setSpanCount() > 1) {
            spanCount = setSpanCount();
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //每个item占几个span(一行是有spancount个)
                int itemViewType = mLoadMoreWrapper.getItemViewType(position);
                switch (itemViewType) {
                    case LoadMoreWrapper.TYPE_LOAD_MORE:
                        return spanCount;
                    case BaseRVAdapter.TYPE_BASE_FOOTER:
                        return spanCount;
                    case BaseRVAdapter.TYPE_BASE_HEAD:
                        return spanCount;
                    default:
                        return 1;
                }
            }
        });

        mRecyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onRefresh() {
        mLoadType = REQUEST_REFRESH;
        onRefreshData();
    }


    public int getLoadType() {
        return mLoadType;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
