package com.example.experiment2;

import java.io.Serializable;

public class Student implements Serializable {
    int id;
    private String Name;
    private int Age;
    private float Length;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public float getLength() {
        return Length;
    }

    public void setLength(float length) {
        Length = length;
    }
}
