package com.eyepetizer.ui.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * author: midVictor
 * date: on 2016/11/29
 * description:
 */

public enum ImageUtils {


    INSTANCE;

    public void disPLay(Context context, ImageView imageView, String url) {

        Picasso.with(context).load(url).into(imageView);
    }

    public void disPLayCircle(Context context, ImageView imageView, String url) {
        Picasso.with(context).load(url).transform(new CircleTransform()).into(imageView);
    }


}
