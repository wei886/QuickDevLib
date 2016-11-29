package com.eyepetizer.ui.http;

/**
 * Created by zsj on 2016/10/1.
 */

public class API {

    public static final String DAILY = "v2/feed?num=2";

    public static final String MORE = "v2/categories";

    public static final String TRENDING = "v3/ranklist?num=10&strategy=%s";

    public static final String DETAIL = "v3/videos?categoryName=%s&strategy=%s";

    public static final String INTERESTING = "/v3/videos?num=10";

    public static final String REPLIES = "http://baobab.wandoujia.com/api/v1/replies/video?id=9962";

    public static final String QUERIES = "http://baobab.wandoujia.com/api/v3/queries/hot";

    public static final String QUERY = "http://baobab.wandoujia.com/api/v1/search?query=Android";
}
