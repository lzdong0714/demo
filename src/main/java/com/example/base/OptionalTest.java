package com.example.base;

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {

        MethodInner methodInner = new MethodInner();

        Optional<String> id = Optional.ofNullable(methodInner.getId());
        System.out.println(id);

        methodInner.setId("raw_id");
        Optional<String> idplus = Optional.ofNullable(methodInner)
                .map(MethodInner::plusId);
        System.out.println(idplus);
        System.out.println(idplus.orElse("no data"));

        methodInner.setId("id");
        Optional<String> s = Optional.of(methodInner).map(MethodInner::plusId);
        System.out.println("s : "+s);
        String s1 = s.orElse("");
        System.out.println("s1 : "+s1);
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
