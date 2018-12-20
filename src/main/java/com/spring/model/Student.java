package com.spring.model;

public class Student {
    private Integer age;

    public Student() {}

    public Student(String name, Integer age, String address) {
        this.age = age;
        this.address = address;
        this.name = name;
    }

    private String address;
    private String name;
    private Integer id;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
