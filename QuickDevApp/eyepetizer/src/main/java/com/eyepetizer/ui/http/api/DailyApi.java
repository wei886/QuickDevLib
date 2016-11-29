package com.eyepetizer.ui.http.api;

import com.eyepetizer.ui.model.DailyInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author: midVictor
 * date: on 2016/11/29
 * description:
 */

public interface DailyApi {

    @GET("v2/feed?num=2")
    Observable<DailyInfo> getDaily(@Query("date") long date);


    @GET("v2/feed?num=2")
    Observable<DailyInfo> getDaily();



    @GET("v2/feed")
    Observable<DailyInfo> getDaily(@Query("num") String num);


}
