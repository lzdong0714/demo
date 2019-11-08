package com.example.base;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {

      test_2();
    }

    public static void test_2(){
        MethodInner methodInner = new MethodInner();
//        methodInner.setId("id_1");
        List<MethodInner> list = new LinkedList<>();
        list.add(methodInner);

        boolean b_1 = Optional.ofNullable(list).isPresent();
        System.out.println("b1 : "+ b_1);

        boolean b_2 = Optional.ofNullable(list).map(items -> {
            return items.get(0);
        }).map(item -> {
            return item.getId();
        }).isPresent();
        System.out.println("b2 : "+ b_2);

    }

}

class MethodInner{
    public MethodInner(){}

    public void setId(String id){
        this.id = id;
    }
    private String id ;


    public String getId(){
        return id ;
    }

    public String plusId(){
        return id+"plus";
    }

}
