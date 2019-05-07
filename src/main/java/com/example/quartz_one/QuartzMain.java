package com.example.quartz_one;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.quartz_one.dao")
public class QuartzMain {
    public static void main(String[] args){
        SpringApplication.run(QuartzMain.class);
    }
}
