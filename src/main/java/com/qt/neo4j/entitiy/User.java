package com.qt.neo4j.entitiy;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class User extends BaseNode {

    private Long uid;
    private String sex;
    private Integer age;
    private String name;
    private List<String> hobbies;

    @Relationship(type = "Like")
    private List<LikeRelation> likes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
        hobbies = new ArrayList<>();
        likes = new ArrayList<>();
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

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<LikeRelation> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeRelation> likes) {
        this.likes = likes;
    }

    public void addHobby(String hobby){
        hobbies.add(hobby);
    }

    public void addLikes(User user,String reason,Integer since,Integer relationId){
        LikeRelation like = new LikeRelation(this, user, reason, since, relationId);
        this.likes.add(like);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", hobbies=" + hobbies +
                ", likes=" + likes +
                '}';
    }
}
