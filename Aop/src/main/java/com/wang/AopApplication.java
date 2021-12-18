package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xiaowang
 * @time 2021-09-07 21:46
 **/
@SpringBootApplication
@EnableScheduling
public class AopApplication {
    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class,args);
    }
}
