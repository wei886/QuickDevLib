package dev.com.Inject.proxydemo;

/**
 * author: midVictor
 * date: on 2017/1/4
 * description:
 */

public class ProxyStudent implements Human {

    private Human obj;

    public ProxyStudent bind(Human obj) {

        this.obj = obj;

        return this;
    }


    @Override
    public void eat() {
        eatbefore();
        obj.eat();
        eatAfter();
    }

    @Override
    public void la() {
         obj.la();
     }

    public void eatbefore() {
        System.err.println("吃饭前");

    }

    public void eatAfter() {
        System.err.println("吃饭后");
    }


}
