package ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/11/14.
 */

public abstract class BaseFragment extends Fragment {


    private View mContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayoutId(), container, false);
    }

    /**
     * 设置布局resourceid
     *
     * @return
     */
    public abstract int setLayoutId();

    public abstract void initData();

    public abstract void initView();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mContainer = view;
        initView();
        initData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public <T extends View> T findView(int id) {
        return mContainer != null ? (T) mContainer.findViewById(id) : null;
    }

}
