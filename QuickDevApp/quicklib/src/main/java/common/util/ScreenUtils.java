package common.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * @ explain:
 */
public class ScreenUtils {

    private ScreenUtils(){

    }

    public static int  getScreenHeight(Activity activity){
        DisplayMetrics displayMetrics = getDisplayMetrics(activity);

        return displayMetrics.heightPixels;
    }

    public static int  getScreenWidth(Activity activity){
        DisplayMetrics displayMetrics = getDisplayMetrics(activity);
        return displayMetrics.widthPixels;
    }


    public static DisplayMetrics getDisplayMetrics(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
