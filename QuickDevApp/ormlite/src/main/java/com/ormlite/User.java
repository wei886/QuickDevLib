package com.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * author: midVictor
 * date: on 2016/12/19
 * description:
 */


@DatabaseTable(tableName = "tb_user")
public class User {

    @DatabaseField(id = true,columnName = "id_")
    private int id;

    @DatabaseField(columnName = "name_")
    private String name;

    @DatabaseField(columnName = "age_")
    private int age;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {

    }
    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
