package com.example.station;

import com.example.demo.DemoApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.station.dao")
public class StationMain {
    public static void main(String[] args) {
        SpringApplication.run(StationMain.class, args);
    }
}
