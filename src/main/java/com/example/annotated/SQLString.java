package com.example.annotated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年08月14日 15:14:00
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    //数据库的列名
    String name() default "";

    //列类型分配长度，如varchar(30) 的 30
    int value() default 0;

    Constraints contraints() default @Constraints;

}
