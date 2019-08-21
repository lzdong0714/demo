package com.example.demo.vo;

import com.example.demo.entity.Girl;

import java.lang.reflect.Field;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年08月21日 10:37:00
 */

public class GirlAndBoy {
    Class<?> girl = Girl.class;

    public void test() throws NoSuchFieldException, IllegalAccessException {
        Field girl_field = girl.getDeclaredField("age");
        Girl girl = new Girl();
        girl.setAge(10);
        girl.setName("lol");
        girl_field.setAccessible(true);
        Integer age  = (Integer)girl_field.get(girl);
        System.out.println(age);
    }

    public static void main(String[] args) {
        GirlAndBoy girlAndBoy = new GirlAndBoy();
        try {
            girlAndBoy.test();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
