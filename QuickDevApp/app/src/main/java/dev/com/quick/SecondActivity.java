package dev.com.quick;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import dev.com.bluetooth.BlueToothActivity;
import ui.activity.BaseActivity;
import ui.utils.LogUtils;

/**
 * Created by Administrator on 2016/11/14.
 */

public class SecondActivity extends BaseActivity {


    private MenuItem mItem;

    @Override
    public int setLayoutId() {
        return R.layout.secondactivity;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

        Button btn = findView(R.id.btn);

        TextView textView = findView(R.id.tv_content_has_peidui);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, BlueToothActivity.class));
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItem.isVisible()) {
                    mItem.setVisible(false);
                } else {
                    mItem.setVisible(true);
                }
            }
        });


        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("https://www.ptyxjy.com/site/topicwarewx.htm?id=33&from=singlemessage&isappinstalled=0");

        WebSettings settings = webView.getSettings();
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }


        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                LogUtils.log(url);
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {

//                handler.proceed();  // 接受所有网站的证书

                Log.e("wh", "error" + error);
                super.onReceivedSslError(view, handler, error);
            }

        });

        webView.setWebChromeClient(new WebChromeClient() {


            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                Log.e("wh", "newp=" + newProgress);
                super.onProgressChanged(view, newProgress);
            }



        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        mItem = menu.findItem(R.id.main_item1);
        return super.onCreateOptionsMenu(menu);
    }
}
