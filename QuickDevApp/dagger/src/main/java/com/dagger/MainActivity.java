package com.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dagger.demo.DaggerPersonCompont;
import com.dagger.demo.Person;
import com.dagger.demo.User;

import javax.inject.Inject;

import quickdev.com.dagger.R;

public class MainActivity extends AppCompatActivity {


    @Inject
    Person person;

    @Inject
    User user;

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DaggerPersonCompont.create().inject(this);

        Log.e(TAG, "AAA=" + person.getName());
        Log.e(TAG, "AAA=" + user.getName());


    }
}
