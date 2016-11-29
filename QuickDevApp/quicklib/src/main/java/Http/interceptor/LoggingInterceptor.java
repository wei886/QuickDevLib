package Http.interceptor;


import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class LoggingInterceptor implements Interceptor {
    String TAG = "LoggingInterceptor";


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        long t1 = System.nanoTime();

        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
//        Log.e(TAG, "t2=" + t2);

        if (request != null && request.url() != null) {
            Log.e(TAG, "url=" + request.url());
        }

        return response;
    }
}
