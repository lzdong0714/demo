package com.example.base;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateTest {

    public static void main(String[] args) {
        DateTest dateTest = new DateTest();
        dateTest.test_1();
    }

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
}
