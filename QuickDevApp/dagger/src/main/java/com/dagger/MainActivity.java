package com.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dagger.demo.DaggerMainActivityCompont;
import com.dagger.demo.DaggerPersonCompont;
import com.dagger.demo.Person;
import com.dagger.demo.User;
import com.dagger.utils.ToastUtils;

import javax.inject.Inject;

import dev.com.dagger.R;

public class MainActivity extends AppCompatActivity {


    @Inject
    Person person;

    @Inject
    User user;


    @Inject
    ToastUtils toastUtils;

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DaggerPersonCompont.create().inject(this);

        Log.e(TAG, "AAA=" + person.getName());
        Log.e(TAG, "AAA=" + user.getName());


        DaggerMainActivityCompont.create().inject(this);


        toastUtils.show(MainActivity.this,"dddddd="+person.getName());


    }
}
