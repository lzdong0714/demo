package com.example.reflection;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年08月21日 17:17:00
 */
@Data
@Accessors(chain = true)
public class Person {

    private String name;

    private int age ;

    private LocalDateTime birthTime;


    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(10)
        .setBirthTime(LocalDateTime.of(2010,9,12,17,30))
                .setName("pop");


    }
}
