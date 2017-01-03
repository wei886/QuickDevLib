package com.dagger.utils;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * author: midVictor
 * date: on 2016/12/15
 * description:
 */

public class ToastUtils {


    @Inject
    public ToastUtils(){

    }

    public static void show(Context context, String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
