package com.example.reflection;

import lombok.Data;

/**
 * @Author: Liu Zhendong
 * @Description 样例类用来实验反射
 * @createTime 2019年08月21日 16:19:00
 */
@Data

public class Student {

    private String name;

    private int age ;

    public Student(){}

    private Student(String name, int age){
        this.name = name;
        this.age = age;
    }


    public int addAge(int add){
        age = age + add;
        return age;
    }


}
