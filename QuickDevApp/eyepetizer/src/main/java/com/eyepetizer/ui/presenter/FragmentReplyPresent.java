package com.eyepetizer.ui.presenter;

import com.eyepetizer.ui.model.Replies;

/**
 * author: midVictor
 * date: on 2016/11/29
 * description:
 */

public interface FragmentReplyPresent extends BasePresenterImpl {

    void getReplyData(int id);
    void getReplyData(int id, int lastId);


    interface View {

        void gotReplayDataSuccess(Replies replies);

        void gotReplayDataFailure(String errMsg);

        void setLastReplyId(int id);

    }
}
