package com.example.base;

import javax.swing.*;

public class BitCalculate {
    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        int c = 2;

        int ret_1 = (a^b^1);
        int ret_2 = (a^c);
        System.out.println("ret_1:"+ret_1);
        System.out.println("ret_2:"+ret_2);
    }
}
