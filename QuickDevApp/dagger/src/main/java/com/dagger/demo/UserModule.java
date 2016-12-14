package com.dagger.demo;

import dagger.Module;
import dagger.Provides;

/**
 * author: midVictor
 * date: on 2016/12/14
 * description:
 */


@Module
public class UserModule {

    @Provides
    User provideUser() {
        return new User("XiaoMing");
    }
}
