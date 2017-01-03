package dev.com.Inject;

import android.app.Activity;
import android.widget.Button;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import dev.com.Inject.inj.WhContentView;
import dev.com.Inject.inj.WhInjectViews;
import ui.utils.LogUtils;

/**
 * author: midVictor
 * date: on 2017/1/3
 * description:
 */

public abstract class InjectBaseActivity extends Activity {

    void injectContentView(Activity activity) {

        Class<? extends Activity> aClass = activity.getClass();
        Annotation[] annotations = aClass.getAnnotations();
        Annotation[] declaredAnnotations = aClass.getDeclaredAnnotations();
//        LogUtils.log(annotations);
//        LogUtils.log(declaredAnnotations);
//        LogUtils.log__();
        for (Annotation annotation : annotations) {
            if (annotation instanceof WhContentView) {
                int conId = ((WhContentView) annotation).contentViewId();
                activity.setContentView(conId);
            }
//            LogUtils.log(annotation);
        }
    }


    void injectViews(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Field[] fields = aClass.getDeclaredFields();



        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation == null) {
                    continue;
                }
                LogUtils.log("field=" + field);
                Class<?> type = field.getType();
//                field.setAccessible(true);
                if (annotation instanceof WhInjectViews) {
                    WhInjectViews whInjectViews = (WhInjectViews) annotation;
                    int id = whInjectViews.viewId();
                    if (type.getName().equals("android.widget.Button")) {
                        Button view = (Button) activity.findViewById(id);
                        try {
                            field.set(activity, view);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
