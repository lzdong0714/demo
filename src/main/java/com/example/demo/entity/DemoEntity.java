package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DemoEntity implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
}
