package dev.com.Inject.proxydemo;

/**
 * author: midVictor
 * date: on 2017/1/4
 * description:
 */

public class Student implements Human {
    @Override
    public void eat() {
        System.err.println("Student吃饭");
    }

    @Override
    public void la() {

        System.err.println("Student啦");
    }
}
