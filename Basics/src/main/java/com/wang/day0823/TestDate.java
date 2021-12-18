package com.wang.day0823;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author xiaowang
 * @time 2021-08-23 21:16
 **/
public class TestDate {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now(); //日期
        LocalTime localTime = LocalTime.now(); //时间
        LocalDateTime localDateTime = LocalDateTime.now(); //日期时间
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        LocalDateTime dateTime = LocalDateTime.of(2021, 8, 23, 21, 20, 10);
        System.out.println(dateTime.toString());


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(format);



    }
}
