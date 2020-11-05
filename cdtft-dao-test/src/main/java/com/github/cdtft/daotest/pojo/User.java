package com.github.cdtft.daotest.pojo;

import java.util.StringJoiner;

/**
 * @author : wangcheng
 * @date : 2020年09月28日 23:07
 */
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("age=" + age)
                .add("gender='" + gender + "'")
                .toString();
    }
}
