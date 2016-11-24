package ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/14.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {


    private ArrayList<View> mClickViews;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        initView();
        initData();
    }

    public abstract int setLayoutId();

    public abstract void initData();

    public abstract void initView();


    public <T extends View> T findView(int id) {
        T viewById = (T) findViewById(id);
        viewById.setOnClickListener(this);
        return viewById;
    }


//    public void setOnClick(View view) {
//        if (mClickViews == null)
//            mClickViews = new ArrayList<>();
//        mClickViews.add(view);
//    }

    @Override
    public void onClick(View v) {
        if (mClickViews != null && !mClickViews.isEmpty())
            for (View view : mClickViews) {
             }
    }
}
