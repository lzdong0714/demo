package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Liu Zhendong
 * @Description 视图对应的实体
 * @createTime 2019年08月09日 11:18:00
 */
@Data
public class ViewEntity {
    private String boyName;

    private int boyAge;

    private String girlName;

    private int girlAge;
}
