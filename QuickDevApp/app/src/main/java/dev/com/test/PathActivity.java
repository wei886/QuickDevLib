package dev.com.test;

import android.os.Bundle;

import dev.com.View.BoatRunView;
import dev.com.quick.R;
import ui.activity.BaseActivity;

/**
 * author: midVictor
 * date: on 2017/1/17
 * description:
 */

public class PathActivity extends BaseActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_path;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
//

//        TextView textView =new TextView(this);
//        textView.setText("dfsfdsfdafdafdafadfa");
//
//        AlertDialog.Builder builder = new Dial.Builder(PathActivity.this).
//                setTitle("d").setMessage("fdsf").
//                setView(textView);



//        AlertDialog dialog = new Dial.Builder(PathActivity.this).
//                setTitle("d").setMessage("fdsf").
//                setView(textView)
//                .create();


//        AlertDialog dialog = builder.create();
//
//
//        Window window = dialog.getWindow();
//
//        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
//        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//
//        textView.setLayoutParams(layoutParams);
//
//
//        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
////        window.setDimAmount(0);
//        dialog.show();




//        WaveView waveView = (WaveView) findViewById(R.id.waveView);
//        waveView.startAnimator();


        BoatRunView boatRunView = (BoatRunView) findViewById(R.id.boat);
        boatRunView.startAnimator();

    }

    @Override
    public void initView() {

    }
}
