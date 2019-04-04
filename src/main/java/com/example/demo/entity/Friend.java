package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Friend implements Serializable {
    private Integer id;
    private DemoEntity boy;
    private DemoEntity girl;
}
