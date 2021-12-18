package com.wang.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author xiaowang
 * @time 2021-11-04 22:37
 **/
@Component
public class TestJob {

    // @Scheduled(cron = "*/5 * * * * ?")
    public void test1(){
        System.out.println("定时任务开始执行："+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }


}
