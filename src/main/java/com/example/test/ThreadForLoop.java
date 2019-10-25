package com.example.test;

import java.util.Random;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年10月15日 16:59:00
 */
public class ThreadForLoop {

    private  String   str="init";

    public void setI(String str){
        this.str = str;
    }

    public void printStr(){
        try {
            Thread.sleep((new Random(23333).nextInt(1000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.str);
    }

    public static void main(String[] args) {
        ThreadForLoop threadForLoop = new ThreadForLoop();
        for(int index = 0; index < 20; index++){

            threadForLoop.setI("change"+index);
            threadForLoop.printStr();


        }
    }
}
