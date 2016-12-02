package com.eyepetizer.ui.http.api;

import com.eyepetizer.ui.model.Replies;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zsj on 2016/10/16.
 */

public interface ReplayApi {

    @GET("v1/replies/video")
    Observable<Replies> fetchReplies(@Query("id") int id);

    @GET("v1/replies/video?num=10")
    Observable<Replies> fetchReplies(@Query("id") int id, @Query("lastId") int lastId);

}
