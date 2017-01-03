package ui.utils;

import android.util.Log;

/**
 * author: midVictor
 * date: on 2017/1/3
 * description:
 */

public class LogUtils {

    public static void log(Object o) {
        Log.e("wh", o.getClass().getName() + "=" + o != null ? o.toString() : "null");
    }


    public static void logString(String  o) {
        Log.e("wh", o);
    }


    public static void log__(){
        log("-------------------------------------------------");
    }
}
