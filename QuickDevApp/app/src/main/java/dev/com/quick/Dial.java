package dev.com.quick;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;

/**
 * author: midVictor
 * date: on 2017/1/17
 * description:
 */

public class Dial extends AlertDialog {
    public Dial(@NonNull Context context) {
        super(context);
    }

    public Dial(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }
}
