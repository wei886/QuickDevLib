package quickdev.com.quick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import quickdev.com.View.NewButton;
import quickdev.com.http.RetActivity;
import ui.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    private LinearLayout mLlMain;

    private Activity mContenxt = MainActivity.this;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

        mLlMain = findView(R.id.activity_main);

        new NewButton(mContenxt, (ViewGroup) findViewById(R.id.activity_main)).setText("ret").onclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContenxt, RetActivity.class));
            }
        });


        new NewButton(mContenxt, (ViewGroup) findViewById(R.id.activity_main)).setText("2").onclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContenxt, SecondActivity.class));
            }
        });


        new NewButton(mContenxt, (ViewGroup) findViewById(R.id.activity_main)).setText("dagger").onclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContenxt, com.dagger.MainActivity.class));
            }
        });


    }


}
