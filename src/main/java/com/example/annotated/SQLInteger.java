package com.example.annotated;


import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)//作用与属性上
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInteger {
    //数据库的列名
    String name() default "";

    //嵌套注解
    Constraints constraints() default @Constraints;

}
