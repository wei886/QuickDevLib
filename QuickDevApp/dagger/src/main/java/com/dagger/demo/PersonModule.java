package com.dagger.demo;

import dagger.Module;
import dagger.Provides;

/**
 * author: midVictor
 * date: on 2016/12/13
 * description:
 */

@Module
public class PersonModule {

    @Provides
    Person providePersonInfo() {
        return new Person();
    }


}
