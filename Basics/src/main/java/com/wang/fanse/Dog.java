package com.wang.fanse;

/**
 * @author xiaowang
 * @time 2021-09-05 2:21
 **/
public class Dog implements Walk {
    @Override
    public String walk(String walk) {
        System.out.println("dog:"+walk);
        return "狗也用脚走路";
    }
}
