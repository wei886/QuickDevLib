package Http.retrofit;

import android.content.Context;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import Http.config.CookieManager;
import Http.config.FastJsonConverterFactory;
import Http.retrofit.BaseEntity.BaseResponseEntity;
import Http.retrofit.BaseEntity.NetworkResponse;
import Http.util.NetErrStringUtils;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class NetWorkClient {

    public static final String TAG = NetWorkClient.class.getSimpleName();

    private NetWorkClient() {

    }

    private static NetWorkClient sInstance = new NetWorkClient();

    public static NetWorkClient getInstance() {
        if (sInstance == null) {
            synchronized (NetWorkClient.class) {
                if (sInstance == null) {
                    sInstance = new NetWorkClient();
                }
            }
        }
        return sInstance;
    }

    private Map<String, Map<Integer, Call>> mRequestMap = new ConcurrentHashMap<>();

    public Context mContext;

    private Retrofit mRetrofit;

    private OkHttpClient mOkHttpClient;


    /**
     * 初始化Retrofit
     *
     * @param context
     */
    public NetWorkClient init(Context context, String baseURL) {
        this.mContext = context;
        synchronized (NetWorkClient.this) {

            mRetrofit = new Retrofit.Builder()
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .baseUrl(baseURL)//主机地址
                    .client(mOkHttpClient)
                    .build();
        }
        return this;
    }


    public <T> T create(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

    public void clearCookie() {
        ((CookieManager) mOkHttpClient.cookieJar()).clearCookie();
    }

    /**
     * 异步请求
     *
     * @param TAG
     * @param requestCall
     * @param responseListener
     * @param <T>
     * @return
     */
    public <T extends BaseResponseEntity> void asyncNetWork(final String TAG, final int requestCode, final Call<T> requestCall, final NetworkResponse<T> responseListener) {
        if (responseListener == null) {
            return;
        }

        Call<T> call;

        if (requestCall.isExecuted()) {
            call = requestCall.clone();
        } else {
            call = requestCall;
        }
        addCall(TAG, requestCode, call);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                cancelCall(TAG, requestCode);
                if (response.isSuccessful()) {
                    T result = response.body();
                    if (result == null) {
                        responseListener.onDataError(requestCode, response.code(), "");
                        return;
                    }
                    result.requestCode = requestCode;
                    result.serverTip = response.message();
                    result.responseCode = response.code();
                    responseListener.onDataReady(result);
                } else {
                    responseListener.onDataError(requestCode, response.code(), NetErrStringUtils.getErrString(mContext, response.code()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                cancelCall(TAG, requestCode);
                responseListener.onDataError(requestCode, 0, NetErrStringUtils.getErrString(mContext, t));
            }
        });
    }

    /**
     * 同步请求
     *
     * @param TAG
     * @param requestCall
     * @param responseListener
     * @param <T>
     * @return
     */
    public <T extends BaseResponseEntity> void syncNetWork(final String TAG, final int requestCode, final Call<T> requestCall, final NetworkResponse<T> responseListener) {
        if (responseListener == null) {
            return;
        }
        Call<T> call;
        try {
            if (requestCall.isExecuted()) {
                call = requestCall.clone();
            } else {
                call = requestCall;
            }

            Response<T> response = call.execute();
            addCall(TAG, requestCode, call);
            if (response.isSuccessful()) {
                T result = response.body();
                if (result == null) {
                    responseListener.onDataError(requestCode, response.code(), "");
                    return;
                }
                result.requestCode = requestCode;
                result.serverTip = response.message();
                result.responseCode = response.code();
                responseListener.onDataReady(result);
            } else {
                responseListener.onDataError(requestCode, response.code(), NetErrStringUtils.getErrString(mContext, response.code()));
            }
        } catch (IOException e) {
            responseListener.onDataError(requestCode, 0, NetErrStringUtils.getErrString(mContext, e));
        } finally {
            cancelCall(TAG, requestCode);
        }
    }

    /**
     * 添加call到Map
     *
     * @param TAG
     * @param call
     */
    private void addCall(String TAG, Integer code, Call call) {
        if (TAG == null) {
            return;
        }
        if (mRequestMap.get(TAG) == null) {
            Map<Integer, Call> map = new ConcurrentHashMap<>();
            map.put(code, call);
            mRequestMap.put(TAG, map);
        } else {
            mRequestMap.get(TAG).put(code, call);
        }
    }

    /**
     * 取消某个call
     *
     * @param TAG
     * @param code
     */
    public boolean cancelCall(String TAG, Integer code) {
        if (TAG == null) {
            return false;
        }
        Map<Integer, Call> map = mRequestMap.get(TAG);
        if (map == null) {
            return false;
        }
        if (code == null) {
            //取消整个context请求
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Integer key = (Integer) iterator.next();

                Call call = map.get(key);
                if (call == null) {
                    continue;
                }
                call.cancel();
            }
            mRequestMap.remove(TAG);
            return false;
        } else {
            //取消一个请求
            if (map.containsKey(code)) {
                Call call = map.get(code);
                if (call != null) {
                    call.cancel();
                }
                map.remove(code);
            }
            if (map.size() == 0) {
                mRequestMap.remove(TAG);
                return false;
            }
        }
        return true;
    }

    /**
     * 取消整个tag请求，关闭页面时调用
     *
     * @param TAG
     */
    public void cancelTagCall(String TAG) {
        cancelCall(TAG, null);
    }
}
