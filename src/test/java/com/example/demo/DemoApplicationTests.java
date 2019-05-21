package com.example.demo;

import com.example.demo.entity.Girl;
import com.example.demo.mapper.GirlDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.example.demo.mapper")
public class DemoApplicationTests {

    @Test
    public void contextLoads() {

    }

}
