package com.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * author: midVictor
 * date: on 2016/12/19
 * description:
 */


@DatabaseTable(tableName = "tb_user")
public class Person {

    @DatabaseField(id = true,columnName = "id_p")
    private int personId;

    @DatabaseField(columnName = "name_p")
    private String name;

    @DatabaseField(columnName = "age_p")
    private int age;


    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {

    }
    public Person(int id, String name, int age) {
        this.personId = id;
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "User{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
