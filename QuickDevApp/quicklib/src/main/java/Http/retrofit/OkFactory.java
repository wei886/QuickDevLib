package Http.retrofit;

import java.io.File;
import java.util.concurrent.TimeUnit;

import Http.Helper.HttpHelper;
import Http.config.AuthenticatorManager;
import Http.config.CookieManager;
import Http.interceptor.LoggingInterceptor;
import Http.interceptor.NetInterceptor;
import Http.interceptor.UserAgentInterceptor;
import app.BaseApplication;
import okhttp3.Cache;
import okhttp3.OkHttpClient;


public enum OkFactory {

    INSTACE;

    private final OkHttpClient mOkhttpClient;

    OkFactory() {
        mOkhttpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new NetInterceptor())
                .addInterceptor(new LoggingInterceptor())
                //添加UA
                .addInterceptor(new UserAgentInterceptor(HttpHelper.getUserAgent()))
                //必须是设置Cache目录
                .cache(new Cache(new File(BaseApplication.mContext.getExternalCacheDir(), "http_cache"), 1024 * 1024 * 100))
////                    //走缓存，两个都要设置
//
//                    .addInterceptor(new OnOffLineCachedInterceptor())
//                    .addNetworkInterceptor(new OnOffLineCachedInterceptor())
                .cookieJar(new CookieManager())
                .authenticator(new AuthenticatorManager())
                .build();
    }


    public OkHttpClient getOkhttpClient() {
        return mOkhttpClient;
    }


}
