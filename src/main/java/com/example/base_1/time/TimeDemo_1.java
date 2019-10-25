package com.example.base_1.time;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年10月14日 16:10:00
 */
public class TimeDemo_1 {
    Logger logger = LoggerFactory.getLogger(getClass());


    public static void main(String[] args) {
        TimeDemo_1 instance = new TimeDemo_1();
        instance.test_1();
    }


    public void test_1(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime after = now.plusMinutes(2);

        Duration duration = Duration.between(now,after);
        System.out.println(duration.toMinutes());
        System.out.println("---------------------");
        System.out.println(Duration.between(after,now).toMinutes());

        logger.info("current time :"+ now.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // 时间轴减价，包含正负号
    }
}
