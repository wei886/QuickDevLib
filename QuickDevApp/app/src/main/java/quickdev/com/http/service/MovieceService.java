package quickdev.com.http.service;

import java.util.List;

import quickdev.com.http.bean.MovieItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author: midVictor
 * date: on 2016/11/24
 * description:
 */

public interface MovieceService {


    //电影条目搜索  Example:GET /v2/movie/search?q=张艺谋   GET /v2/movie/search?tag=喜剧

    @GET("/v2/movie/search")
    Call<List<MovieItem>> getMovieListByAutor(@Query("q") String author, @Query("tag") String tag, @Query("start") String start, @Query("count") String count);


}
