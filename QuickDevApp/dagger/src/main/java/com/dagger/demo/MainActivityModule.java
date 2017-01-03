package com.dagger.demo;

import com.dagger.utils.ToastUtils;

import dagger.Module;
import dagger.Provides;

/**
 * author: midVictor
 * date: on 2016/12/15
 * description:
 */

@Module
public class MainActivityModule {


    @Provides
    ToastUtils provideToast() {
        return new ToastUtils();
    }


}
