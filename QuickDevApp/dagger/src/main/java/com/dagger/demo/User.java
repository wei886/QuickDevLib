package com.dagger.demo;


import javax.inject.Inject;

/**
 * author: midVictor
 * date: on 2016/12/13
 * description:
 */

public class User {

    @Inject
    User(){

    }

    String name;

    public User(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

}
