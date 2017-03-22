package common.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/15.
 */

public class ToastUtils {


    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
