package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xiaowang
 * @time 2021-11-04 22:34
 **/
@SpringBootApplication
@EnableScheduling
public class ScheduApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduApplication.class,args);
    }

}
