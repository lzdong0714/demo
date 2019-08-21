package com.example.base;

import org.apache.commons.lang3.ArrayUtils;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BitCalculate {
    public static void main(String[] args) {
        BitCalculate bitCalculate = new BitCalculate();
        bitCalculate.intStreamTest();
    }

    public void intStreamTest(){
        int[] ints = IntStream.range(2, 2).toArray();
        Integer[] integers = ArrayUtils.toObject(ints);
        List<Integer> integerList = Arrays.asList(integers);

        integerList.forEach(
                item->System.out.println(item)
        );
    }

    public void bitTest(){
        int a = 1;
        int b = 1;
        int c = 2;

        int ret_1 = (a^b^1);
        int ret_2 = (a^c);
        System.out.println("ret_1:"+ret_1);
        System.out.println("ret_2:"+ret_2);
    }
}
