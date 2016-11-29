package com.eyepetizer.ui.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * author: midVictor
 * date: on 2016/11/29
 * description:
 */

public class ImageUtils {


   public static void disPLay(Context context , ImageView imageView,String url){

       Picasso.with(context) .load(url).resize(50, 50).centerCrop().into(imageView);
   }
}
