package com.example.base;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class VersionStreamTest {


    public static void main(String[] args) {
        List<Pointer> pointerList = new LinkedList<>();
        String[] names= {"a","b","c","c","d","e"};
        Random random = new Random();
        for (String name:names) {
            double random_x = random.nextDouble()*10-5;
            double random_y = random.nextDouble()*10-5;
            pointerList.add(new Pointer(name, random_x,random_y));
        }

        List<Pointer> pointers = pointerList.stream()
                .map(item -> {
                    item.setName(item.getName() + "add");
                    return item;
                })
                .filter(item->{
                    boolean b = item.getX() > 0;
                    return b;
                })
                .collect(Collectors.toList());


        pointerList.forEach(item->System.out.println(item));

        System.out.println("+++++++++++++++");

        pointers.forEach(item->System.out.println(item));

        pointerList.addAll(pointers);

        System.out.println("+++++++++++++++");

        pointerList.forEach(System.out::println);
    }




}

@Data
class Pointer implements Serializable {
    private double x;
    private double y;
    private String name;

    public Pointer(String name,double x, double y){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Pointer(String name){
        this.x = 0.0;
        this.y = 0.0;
        this.name = name;
    }

    public Pointer(){
        this.x = 0.0;
        this.y = 0.0;
        this.name = "name";
    }


}
