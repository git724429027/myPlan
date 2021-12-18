package com.wang.test;

import com.wang.common.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author xiaowang
 * @time 2021-12-17 22:58
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1 {

    @Resource
    private TestBean testBean;


    @Test
    public void test1(){
        System.out.println(testBean.getWangUrl());
    }

}
