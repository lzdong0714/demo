package com.example.stomp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Liu Zhendong
 * @Description springboot 子启动
 * @createTime 2019年07月31日 17:16:00
 */
@SpringBootApplication
@MapperScan(value = "com.example.stomp.dao")
public class StompMain {
    public static void main(String[] args) {
        SpringApplication.run(StompMain.class);
    }
}
