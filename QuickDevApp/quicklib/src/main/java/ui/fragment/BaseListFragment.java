package ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dev.com.quicklib.R;
import ui.common.CommonConfig;
import ui.widiget.LoadMoreWrapper;

public abstract class BaseListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private LoadMoreWrapper mLoadMoreWrapper;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private final String TAG = BaseListFragment.class.getSimpleName();

    public final int REQUEST_REFRESH = 0; //下拉刷新
    public final int REQUEST_lOAD_MORE = 1; //加载更多
    private int mLoadType = REQUEST_REFRESH;
    private RecyclerView mRecyclerView;


    public BaseListFragment() {
        // Required empty public constructor
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
     * onViewCreated 中加载数据
     */
    public abstract void initData();

    /**
     * 请求数据成功通知刷新footer状态
     *
     * @param ResponseList
     */
    public void notifyDataRequestSuccess(List ResponseList) {
        if (mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);
        if (null != ResponseList) {
//            int mCurrentPage = 0; //当前第几页
//            if (allData != null) {
//                mCurrentPage = allData.size() / CommonConfig.PAGE_SIZE;
//            }
            if (ResponseList.size() < CommonConfig.PAGE_SIZE) {
//                if (mCurrentPage == 0) {//第一次未超过一页数据不显示footer
//                    mLoadMoreWrapper.setLoadStatus(LoadMoreWrapper.LOADSTATUS_LESS_ONE);
//                } else {
                    mLoadMoreWrapper.setLoadStatus(LoadMoreWrapper.LOADSTATUS_DONE);//全部加载完成
//                }
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quick_base_list_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
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

        initData();
        mSwipeRefreshLayout.setRefreshing(true);
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

    private void setupView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.black));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setProgressViewOffset(true, 0, getResources().getDimensionPixelSize(R.dimen.quick_srlayout_offset));
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
//        mSwipeRefreshLayout.setRefreshing(true);
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
