package com.uos.realmdemo.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Create By xxc
 * Date: 2020/9/18 10:07
 * Desc:
 */
public class User extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
