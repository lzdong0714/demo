package com.example.base;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateTest {

    public static void main(String[] args) {
        DateTest dateTest = new DateTest();
//        dateTest.test_1();
//        dateTest.test_2();

        Date date_1 = new Date();
        Date date_2 = new Date();
        try {
            dateTest.test_3(date_1, date_2);
            System.out.println("------------------");
            System.out.println(date_1);
            System.out.println(date_2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    public static SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

    public void test_3(Date out_date_1, Date out_date_2) throws ParseException {
        Date nextTime = df.parse(df2.format(new Date()) + " 00:00:00");
        Date to = new Date(nextTime.getTime() + 24*3600*1000);
        LocalDateTime now = LocalDateTime.now();
        Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
        Date now_date = Date.from(instant);
        CronExpression expression;
        List<Date> crontimes = new ArrayList<>();
        expression = new CronExpression("0/20 * * * * ?");
        for(;nextTime.getTime()<=to.getTime();){
            Date nextTime_1 = expression.getNextValidTimeAfter(nextTime);
            if(now_date.before(nextTime_1)&&now_date.after(nextTime)){
                System.out.println(df.format(nextTime));
                out_date_1 = nextTime;
                System.out.println(df.format(now_date));
                out_date_2 = nextTime_1;
                System.out.println(df.format(nextTime_1));
            }
            nextTime = nextTime_1;

            if(nextTime.getTime()>=to.getTime()) break;

        }

    }
}
