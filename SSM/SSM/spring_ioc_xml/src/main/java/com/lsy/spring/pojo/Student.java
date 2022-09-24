package com.lsy.spring.pojo;

import javax.swing.*;

public class Student {

    private int id;
    private Spring name;
    private int age;
    private String gender;

    public Student() {
    }

    public Student(int id, Spring name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Spring getName() {
        return name;
    }

    public void setName(Spring name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
        return "Student{" +
                "id=" + id +
                ", name=" + name +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
