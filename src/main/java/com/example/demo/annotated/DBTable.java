package com.example.demo.annotated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年08月14日 15:09:00
 */

@Target(ElementType.TYPE)//作用域是类上
@Retention(RetentionPolicy.RUNTIME) //保存到运行时
public @interface DBTable {
    String name() default "";
}
