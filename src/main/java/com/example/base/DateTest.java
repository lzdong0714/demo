package com.example.base;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTest {

    public static void main(String[] args) {
        DateTest dateTest = new DateTest();
//        dateTest.test_1();
        dateTest.test_2();
    }

    //Date和LocalDate的转化
    public void test_1() {
        LocalDate startTime = LocalDate.of(2018, 12, 11);
        LocalDate endTime = LocalDate.of(2019, 2, 11);
        Instant instant_start = startTime.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant instant_end = endTime.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date startTime_1 = Date.from(instant_start);
        Date endTime_1 = Date.from(instant_end);
        System.out.println("LocalDate print: " + startTime);
        System.out.println("Date print: " + startTime_1);
    }

    //数据库bigint，java Long 和DataLocal之间的转化
    public void test_2(){
        Long java_data = 1143378835098615812L/1000;
        Instant instant = Instant.ofEpochMilli(java_data);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = localDateTime.format(formatter);
        System.out.println(format);
    }
}
