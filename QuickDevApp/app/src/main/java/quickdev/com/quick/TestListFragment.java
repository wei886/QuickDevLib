package quickdev.com.quick;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import common.ToastUtils;
import ui.adapter.BaseRVAdapter;
import ui.common.CommonConfig;
import ui.fragment.BaseListFragment;


public class TestListFragment extends BaseListFragment {


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


        final List temp = new ArrayList();

        for (int i = mData.size(); i < mData.size() + CommonConfig.PAGE_SIZE; i++) {
            temp.add("" + i);
        }


        mData.addAll(temp);
        if (getLoadType() == REQUEST_REFRESH) { //下拉刷新
            resetAdapter(mAdapter);
        } else {
        notifyDataRequestSuccess(temp, mData);

        }


        mAdapter.setOnItemClickLintener(new BaseRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClickLintener(View view, int position) {
                ToastUtils.toast(getActivity(),""+position);
            }
        });

        mAdapter.setOnLongClickLintener(new BaseRVAdapter.OnItemLongClickLintener() {
            @Override
            public void onItemLongCLickLintener(View view, int position) {

                ToastUtils.toast(getActivity(),"long:"+position);
            }
        });



//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                        mData.clear();
//                final List temp = new ArrayList();
//                if (mData.size() > 20) {
//                    for (int i = mData.size(); i < mData.size() + CommonConfig.PAGE_SIZE / 2; i++) {
//                        temp.add("" + i);
//                    }
//                } else {
//                    for (int i = mData.size(); i < mData.size() + CommonConfig.PAGE_SIZE; i++) {
//                        temp.add("" + i);
//                    }
//                }
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        mData.addAll(temp);
//                        if (getLoadType() == REQUEST_REFRESH) { //下拉刷新
//                            resetAdapter(mAdapter);
//                        } else {
//
//                            mAdapter.notifyDataSetChanged();
//                        }
//
//                        notifyDataRequestSuccess(temp, mData);
//                    }
//                });
//            }
//        }, 3000);
    }

    @Override
    public void onLoadMore() {

//        notifyDataRequestFailure();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                final List temp = new ArrayList();
//                if (mData.size() > 20) {
//                    for (int i = mData.size(); i < mData.size() + CommonConfig.PAGE_SIZE / 2; i++) {
//                        temp.add("" + i);
//                    }
//                } else {
//                    for (int i = mData.size(); i < mData.size() + CommonConfig.PAGE_SIZE; i++) {
//                        temp.add("" + i);
//                    }
//                }
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        mData.addAll(temp);
//                        if (getLoadType() == REQUEST_REFRESH) { //下拉刷新
//                            resetAdapter(mAdapter);
//                        } else {
//
//                            mAdapter.notifyDataSetChanged();
//                        }
//
//                        notifyDataRequestSuccess(temp, mData);
//                    }
//                });
//            }
//        }, 3000);

    }

    @Override
    public void initData() {
        mAdapter.addHeader(LayoutInflater.from(getActivity()).inflate(R.layout.head1, null));
        mAdapter.addHeader(LayoutInflater.from(getActivity()).inflate(R.layout.head1, null));
        mAdapter.addHeader(LayoutInflater.from(getActivity()).inflate(R.layout.head1, null));
        mAdapter.addHeader(LayoutInflater.from(getActivity()).inflate(R.layout.head1, null));
        mAdapter.addHeader(LayoutInflater.from(getActivity()).inflate(R.layout.head2, null));
        mAdapter.addFooter(LayoutInflater.from(getActivity()).inflate(R.layout.foot1, null));
        mAdapter.notifyDataSetChanged();
    }
}
