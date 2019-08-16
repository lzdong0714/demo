package com.example.annotated;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年08月14日 15:28:00
 */

@DBTable(name = "MEMBER")
public class Member {

    @SQLString(name = "ID", value = 50, contraints = @Constraints(primaryKey = true))
    private String id;

    @SQLString(name = "NAME", value = 30)
    private String name;

    @SQLInteger(name = "AGE")
    private int age;

    @SQLString(name = "DESRIPTION", value = 150, contraints = @Constraints(allowNull = true))
    private String description;
}
