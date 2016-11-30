package Http.retrofit;

import Http.config.CookieManager;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/22.
 */

public enum RetrofitClient implements ApiConstacts {
    INSTANCE;

    private final Retrofit retrofit;
    private OkHttpClient mOkhttpClient;

    RetrofitClient() {
        mOkhttpClient = OkFactory.INSTACE.getOkhttpClient();
        retrofit = new Retrofit.Builder().
                client(mOkhttpClient).
//                addConverterFactory(new FastJsonConverterFactory()).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                baseUrl(BASE_URL_EYEPETIZER).build();
    }


    RetrofitClient(String baseUrl) {
        mOkhttpClient = OkFactory.INSTACE.getOkhttpClient();
        retrofit = new Retrofit.Builder().
                client(mOkhttpClient).
                addConverterFactory(  GsonConverterFactory.create()).
                baseUrl(baseUrl).build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


    public void clearCookie() {
        if (mOkhttpClient != null) {
            CookieJar cookieJar = mOkhttpClient.cookieJar();
            if (cookieJar != null && cookieJar instanceof CookieManager) {
                CookieManager manager = (CookieManager) cookieJar;
                manager.clearCookie();
            }
        }
    }


}
