package Http.retrofit;

import Http.config.CookieManager;
import Http.config.FastJsonConverterFactory;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
                addConverterFactory(new FastJsonConverterFactory()).
                baseUrl(BASE_URL).build();
    }


    RetrofitClient(String baseUrl) {
        mOkhttpClient = OkFactory.INSTACE.getOkhttpClient();
        retrofit = new Retrofit.Builder().
                client(mOkhttpClient).
                addConverterFactory(new FastJsonConverterFactory()).
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
