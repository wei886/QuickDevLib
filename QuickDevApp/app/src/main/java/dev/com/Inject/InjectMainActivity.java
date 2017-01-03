package dev.com.Inject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dev.com.Inject.inj.WhContentView;
import dev.com.Inject.inj.WhInjectViews;
import dev.com.quick.R;
import ui.utils.LogUtils;

/**
 * author: midVictor
 * date: on 2017/1/3
 * description:
 */


@WhContentView(contentViewId = R.layout.activity_main_inject)


public class InjectMainActivity extends InjectBaseActivity {

    @WhInjectViews(viewId = R.id.btn1)
    private Button btn1;
    @WhInjectViews(viewId = R.id.btn2)
    private Button btn2;
    @WhInjectViews(viewId = R.id.btn3)
    private Button btn3;
    @WhInjectViews(viewId = R.id.btn4)
    Button btn4;
    @WhInjectViews(viewId = R.id.btn5)
    Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectContentView(this);
        injectViews(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.log("dsddddddfsfdaf");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.log("222222222222222222222");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.log("333333333333333333");
            }
        });
    }


}
