package com.dagger.demo;

import com.dagger.MainActivity;

import dagger.Component;

/**
 * author: midVictor
 * date: on 2016/12/15
 * description:
 */

@Component(modules = {MainActivityModule.class})
public interface MainActivityCompont {


    void inject(MainActivity mainActivity);
}
