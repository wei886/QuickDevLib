package com.eyepetizer.ui.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * author: midVictor
 * date: on 2016/11/30
 * description:
 */

public class DateUtils {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /*
                 * 将时间戳转换为时间
                 */
    public static String stampToDate(String s) {
        String res;
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    /*
         * 将时间戳转换为时间
         */
    public static String stampToDate(Long lt) {
        String res;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


}
