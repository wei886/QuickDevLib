package common.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtils {

    private ScreenUtils(){

    }

    public static int  getScreenHeight(Context activity){
        DisplayMetrics displayMetrics = getDisplayMetrics(activity);

        return displayMetrics.heightPixels;
    }

    public static int  getScreenWidth(Context activity){
        DisplayMetrics displayMetrics = getDisplayMetrics(activity);
        return displayMetrics.widthPixels;
    }


    public static DisplayMetrics getDisplayMetrics(Context activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)activity).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }



}
