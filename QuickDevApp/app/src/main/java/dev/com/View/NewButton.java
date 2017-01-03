package dev.com.View;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class NewButton {

        Button button = null;
        Context context = null;
        ViewGroup parent = null;

        public NewButton(Activity context, ViewGroup parent) {
            this.context = context;
            this.parent = parent;
            init(context);
        }

        NewButton(Activity context) {
            init(context);
        }

        void init(Activity context) {
            button = new Button(context);
            if (parent == null) {
                parent = (ViewGroup) context.getWindow().getDecorView();
            }
            parent.addView(button);
            ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams lay = (LinearLayout.LayoutParams) layoutParams;
                lay.gravity = Gravity.CENTER_HORIZONTAL;
                lay.topMargin = 50;
                lay.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                lay.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                button.setLayoutParams(lay);
            } else if (layoutParams instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams lay = (RelativeLayout.LayoutParams) layoutParams;
                lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
                lay.topMargin = 50;
                lay.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                lay.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                button.setLayoutParams(lay);
            } else if (layoutParams instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams lay = (FrameLayout.LayoutParams) layoutParams;
                lay.topMargin = 50;
                lay.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                lay.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                button.setLayoutParams(lay);
            }
        }

        public NewButton setText(String str) {
            button.setText(str);
            return this;
        }

        public NewButton onclick(View.OnClickListener onClickListener) {
            button.setOnClickListener(onClickListener);
            return this;
        }
    }