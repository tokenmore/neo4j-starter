package com.qt.neo4j.entitiy;

import java.util.List;

public class Nodes {

    private Long uid;
    private String name;
    private String sex;
    private Integer age;
    private String hobbies;

    public Nodes(Long uid, String name, String sex, Integer age, String hobbies) {
        this.uid = uid;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Nodes(Long uid, String sex, Integer age, String hobbies) {
        this.uid = uid;
        this.sex = sex;
        this.age = age;
        this.hobbies = hobbies;
    }

    public Nodes() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Nodes{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", hobbies='" + hobbies + '\'' +
                '}';
    }
}
