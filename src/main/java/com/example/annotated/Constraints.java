package com.example.annotated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)//只能用在字段上
public @interface Constraints {

    //判断是否作为主键约束
    boolean primaryKey() default false;

    // 判断是否允许为null
    boolean allowNull() default false;

    // 判断是否唯一
    boolean unique() default false;
}
