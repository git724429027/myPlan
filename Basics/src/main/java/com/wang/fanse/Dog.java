package com.wang.fanse;

/**
 * @author xiaowang
 * @time 2021-09-05 2:21
 **/
public class Dog implements Work{
    @Override
    public String work(String work) {
        System.out.println("dog:"+work);
        return "狗也用脚走路";
    }
}
