package dev.com.Inject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * author: midVictor
 * date: on 2017/1/3
 * description:
 */

public class UserInvocationHandler implements InvocationHandler {


    private User user;

    void bind(User user) {


        this.user = user;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
