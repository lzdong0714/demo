package com.example.base;

import java.util.EnumMap;

public enum EnumTest_1 {
    UNDEFINE,
    CIRCLE,
    POLYGON;


    public static EnumMap<EnumTest_1,Integer> getMap(){
        EnumMap<EnumTest_1,Integer> enumMap = new EnumMap<EnumTest_1, Integer>(EnumTest_1.class);
        enumMap.put(EnumTest_1.UNDEFINE,1);
        enumMap.put(EnumTest_1.CIRCLE,2);
        enumMap.put(EnumTest_1.POLYGON,3);
        return enumMap;
    }

    public static void main(String[] args) {
        int type = 1;
        EnumMap<EnumTest_1, Integer> map = EnumTest_1.getMap();
        Integer integer = map.get(EnumTest_1.UNDEFINE);
        System.out.println(integer);

    }


}
