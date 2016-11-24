package quickdev.com.quick;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import quickdev.com.bluetooth.BlueToothActivity;
import ui.activity.BaseActivity;

/**
 * Created by Administrator on 2016/11/14.
 */

public class SecondActivity extends BaseActivity {


    @Override
    public int setLayoutId() {
        return R.layout.secondactivity;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        Button btn = findView(R.id.btn);

        TextView textView = findView(R.id.tv_content_has_peidui);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SecondActivity.this,BlueToothActivity.class));
                }
            });
    }




}
