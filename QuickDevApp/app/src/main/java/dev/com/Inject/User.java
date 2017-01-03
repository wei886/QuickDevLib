package dev.com.Inject;

/**
 * author: midVictor
 * date: on 2017/1/3
 * description:
 */

public class User {

    private String name ="SDFS";
    private int age ;
    private boolean sex;


    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
