package com.dagger.demo;

import com.dagger.MainActivity;

import dagger.Component;

/**
 * author: midVictor
 * date: on 2016/12/13
 * description:
 */

@Component(modules ={UserModule.class,PersonModule.class})
public interface PersonCompont {

    void inject(MainActivity mainActivity);

}
