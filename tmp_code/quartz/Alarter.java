package com.example.quartz;


import com.example.quartz.config.AppConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan({"com.example.quartz.dao"})
public class Alarter {

    public static void main(String[] args){
        SpringApplication.run(Alarter.class,args);
    }

    @Bean
    public AppConfig appconfig(){
        return new AppConfig();
    }
}
