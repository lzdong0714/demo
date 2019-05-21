package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Girl implements Serializable {
    private int uid;
    private String name;
    private Integer age;
}
