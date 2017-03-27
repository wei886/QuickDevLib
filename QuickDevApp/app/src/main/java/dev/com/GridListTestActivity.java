package dev.com;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dev.com.adapter.GridTestListAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ui.activity.BaseGridListActivity;
import ui.common.CommonConfig;

/**
 * author: midVictor
 * date: on 2017/3/24
 * description:
 */

public class GridListTestActivity extends BaseGridListActivity {
    List data = new ArrayList();
    private GridTestListAdapter mAdapter;


    @Override
    public RecyclerView.Adapter setAdapter() {
        mAdapter = new GridTestListAdapter(GridListTestActivity.this, data);
        return mAdapter;
    }

    @Override
    public void onRefreshData() {

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public void onLoadMore() {


        Observable.timer(4000, TimeUnit.MILLISECONDS).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                notifyDataRequestFailure();
            }

            @Override
            public void onNext(Long aLong) {
                int size = data.size();
                List list = new ArrayList();
                for (int i = 0; i < CommonConfig.PAGE_SIZE; i++) {
                    list.add("I'M " + (i + size));
                }
                data.addAll(list);
                mAdapter.notifyDataSetChanged();
                notifyDataRequestSuccess(list);
            }
        });
    }

    @Override
    public int setSpanCount() {


        return 3;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        for (int i = 0; i < CommonConfig.PAGE_SIZE; i++) {
            data.add("I'M " + i);
        }

        TextView textView = new TextView(this);
        textView.setText("我是头部1");

        Button button = new Button(this);
        button.setText("我是头部2");


        Button button2 = new Button(this);

        button2.setText("我是底部");


        mAdapter.addHeader(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("wh", "t1");
            }
        });

        mAdapter.addHeader(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("wh", "button");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("wh", "button2");
            }
        });

        mAdapter.addFooter(button2);

        mAdapter.notifyDataSetChanged();
    }
}
