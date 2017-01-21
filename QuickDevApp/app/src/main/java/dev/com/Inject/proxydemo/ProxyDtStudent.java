package dev.com.Inject.proxydemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author: midVictor
 * date: on 2017/1/4
 * description:
 */

public class ProxyDtStudent implements InvocationHandler {


    Object obj;

    public Object bind(Human human) {

        System.err.println("bind-----------------------------");


        this.obj = human;
        Object o = Proxy.newProxyInstance(human.getClass().getClassLoader(), human.getClass().getInterfaces(), this);

        System.err.println("o=" + o);

        return o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("invoke-----------------------------");

        eatbefore();

        Object invoke = method.invoke(obj, args);

        if (method != null && method.getName().matches("eat()")) {

            System.err.println("嘻嘻嘻吃=");
        } else if (method != null && method.getName().matches("la()")) {

            System.err.println("哈哈哈哈哈啦=");
        }


        eatAfter();
        return invoke;
    }


    public void eatbefore() {
        System.err.println("吃饭前11111111111");

    }

    public void eatAfter() {
        System.err.println("吃饭后222222222222222222");
    }


}
