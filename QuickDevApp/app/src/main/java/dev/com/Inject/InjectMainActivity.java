package dev.com.Inject;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import common.util.ImageUtils.ImageUtils;
import common.util.XPermissionUtils;
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
    private ImageView imageView1;
    private ImageView imageView2;


    String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488950363453&di=a6f3bd7d1b2461d2b6a8c1bb7fa9aeb7&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fe7cd7b899e510fb3bde5709ddb33c895d1430c3f.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectContentView(this);
        injectViews(this);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        imageView1 = (ImageView) findViewById(R.id.img_1);
        imageView2 = (ImageView) findViewById(R.id.img_2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //权限管理
                XPermissionUtils.requestPermissions(InjectMainActivity.this, 1, new String[]{Manifest.permission
                        .READ_EXTERNAL_STORAGE, Manifest.permission
                        .WRITE_EXTERNAL_STORAGE}, new XPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        ImageUtils.instance(InjectMainActivity.this).disPlayImage(InjectMainActivity.this, url, imageView1);
                    }

                    @Override
                    public void onPermissionDenied() {
                        Toast.makeText(InjectMainActivity.this, "请打开权限！", Toast.LENGTH_SHORT).show();
                    }
                });


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

                //权限管理
                XPermissionUtils.requestPermissions(InjectMainActivity.this, 1, new String[]{Manifest.permission
                        .READ_EXTERNAL_STORAGE, Manifest.permission
                        .WRITE_EXTERNAL_STORAGE}, new XPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        ImageUtils.instance(InjectMainActivity.this).downLoadImage(url, "abc", InjectMainActivity.this);
                    }

                    @Override
                    public void onPermissionDenied() {
                        Toast.makeText(InjectMainActivity.this, "请打开权限！", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


    }


}
