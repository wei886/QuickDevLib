package com.eyepetizer.ui.presenter;

import com.eyepetizer.ui.model.DailyInfo;

/**
 * author: midVictor
 * date: on 2016/11/29
 * description:
 */

public interface FragmentFeedPresent extends BasePresenterImpl {


    /**
     * 获取每天的数据
     *
     * @return
     */
    void getDailyData();
    void getDailyData(long date);


    interface View {

        void gotDataSuccess(DailyInfo daily);

        void gotDataFailure(String error);

    }
}
