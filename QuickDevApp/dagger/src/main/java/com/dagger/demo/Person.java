package com.dagger.demo;

import javax.inject.Inject;

/**
 * author: midVictor
 * date: on 2016/12/13
 * description:
 */

public class Person {

    private int age;
    private String name ="dd";
    private boolean sex;


    @Inject
    Person(){}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
