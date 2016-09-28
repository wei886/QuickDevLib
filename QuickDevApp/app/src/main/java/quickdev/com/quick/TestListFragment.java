package quickdev.com.quick;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ui.common.CommonConfig;
import ui.fragment.QuickBaseListFragment;

/**
 * 专辑表情列表
 * Created by Administrator on 2016/9/22.
 */

public class TestListFragment extends QuickBaseListFragment {


    private ArrayList mData;
    private TestListFragmentAdapter mAdapter;

    @Override
    public RecyclerView.Adapter setAdapter() {
        mData = new ArrayList();
        mAdapter = new TestListFragmentAdapter(getContext(), mData);
        return mAdapter;
    }

    @Override
    public void onRefreshData() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                        mData.clear();
                final List temp = new ArrayList();
                if (mData.size() > 30) {
                    for (int i = mData.size(); i < mData.size() + CommonConfig.PAGE_SIZE / 2; i++) {
                        temp.add("" + i);
                    }
                } else {
                    for (int i = mData.size(); i < mData.size() + CommonConfig.PAGE_SIZE; i++) {
                        temp.add("" + i);
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mData.addAll(temp);
                        if (getLoadType() == REQUEST_REFRESH) { //下拉刷新
                            refreshAdapter(mAdapter);
                        } else {

                            mAdapter.notifyDataSetChanged();
                        }

                        notifyDataRequestSuccess(temp, mData);
                    }
                });
            }
        }, 3000);
    }

    @Override
    public void onLoadMore() {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final List temp = new ArrayList();
                if (mData.size() > 30) {
                    for (int i = mData.size(); i < mData.size() + CommonConfig.PAGE_SIZE / 2; i++) {
                        temp.add("" + i);
                    }
                } else {
                    for (int i = mData.size(); i < mData.size() + CommonConfig.PAGE_SIZE; i++) {
                        temp.add("" + i);
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mData.addAll(temp);
                        if (getLoadType() == REQUEST_REFRESH) { //下拉刷新
                            refreshAdapter(mAdapter);
                        } else {

                            mAdapter.notifyDataSetChanged();
                        }

                        notifyDataRequestSuccess(temp, mData);
                    }
                });
            }
        }, 3000);

    }

    @Override
    public void initData() {

    }
}
