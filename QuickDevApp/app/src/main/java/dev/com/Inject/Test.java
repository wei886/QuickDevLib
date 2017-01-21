package dev.com.Inject;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dev.com.Inject.proxydemo.Human;
import dev.com.Inject.proxydemo.ProxyDtStudent;
import dev.com.Inject.proxydemo.Student;

/**
 * author: midVictor
 * date: on 2017/1/3
 * description:
 */

public class Test {


    public static void main(String args[]) {


        try {

            Class<?> aClass = Class.forName("dev.com.Inject.User");


            Class[] classes = new Class[]{};

//            System.err.println("classes =" + classes);
//            System.err.println("classes =" + classes.length);


            Object o1 = aClass.getDeclaredConstructor(classes).newInstance();
//            System.err.println("o1 =" + o1);

            Object o = aClass.newInstance();
//            System.err.println("0 =" + o);


            Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();


            for (Constructor constructor : declaredConstructors) {
//                System.err.println(constructor.getName());
//                System.err.println(constructor.getParameterTypes());
                if (constructor.getParameterTypes().length > 0) {
                    User lisi = (User) constructor.newInstance("lisi", 2);
                    sys(lisi.getName());
                } else {
                    User o2 = (User) constructor.newInstance();
                    sys(o2.getName());
                }
            }


            Field[] declaredFields = aClass.getDeclaredFields();

            for (Field field : declaredFields) {
                field.setAccessible(true);
            }

            Constructor<?> aaaaa = aClass.getDeclaredConstructor(new Class[]{String.class, int.class});

            User ao = (User) aaaaa.newInstance("aaaa", 211);

//            sys("a1=" + ao.getName());
            aaaaa.setAccessible(true);
            Method setName = aClass.getMethod("setName", new Class[]{String.class});

            setName.invoke(ao, new Object[]{"woshinamel"});

//            sys(ao.getName());


//            ProxyStudent proxyStudent =new ProxyStudent();
//            ProxyStudent bind = proxyStudent.bind(new Student());
//            bind.eat();


            ProxyDtStudent proxyDtStudent =new ProxyDtStudent();
            Human bind = (Human) proxyDtStudent.bind(new Student());


//            sys(bind.toString());
//
//            sys();

            bind.eat();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    public static void sys(String str) {

        System.err.println(str);
    }
}
