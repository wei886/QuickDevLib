package quickdev.com.http;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import Http.retrofit.RetrofitClient;
import quickdev.com.http.bean.MovieItem;
import quickdev.com.http.service.MovieceService;
import quickdev.com.quick.R;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ui.activity.BaseActivity;

public class RetActivity extends BaseActivity {


    private Button mBtnRetrofitGet;
    private TextView mTvRetrofitGet;

    @Override
    public int setLayoutId() {
        return R.layout.activity_ret;
    }

    @Override
    public void initData(Bundle savedInstanceState) {


        mBtnRetrofitGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = RetrofitClient.INSTANCE.getRetrofit();
                MovieceService movieceService = retrofit.create(MovieceService.class);
                final Call<List<MovieItem>> call = movieceService.getMovieListByAutor("张艺谋", "", "0", "100");
                Observable.create(new Observable.OnSubscribe<Response<List<MovieItem>>>() {
                    @Override
                    public void call(Subscriber<? super Response<List<MovieItem>>> subscriber) {
                        try {
                            Response<List<MovieItem>> response = call.execute();
                            subscriber.onNext(response);
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Response<List<MovieItem>>>() {
                    @Override
                    public void onCompleted() {
                        Log.e("aa", "o111=");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("aa", "o1=" + e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Response<List<MovieItem>> o) {
                        Log.e("aa", "o=" + o);
                    }
                });


            }
        });
    }

    @Override
    public void initView() {
        mBtnRetrofitGet = findView(R.id.btn_retrofit_get);
        mTvRetrofitGet = findView(R.id.tv_retrofit_get);
    }
}
