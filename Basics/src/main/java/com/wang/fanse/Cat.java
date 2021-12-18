package com.wang.fanse;

/**
 * @author xiaowang
 * @time 2021-09-05 2:04
 **/
public class Cat implements Work{
    @Override
    public String work(String work) {
        System.out.println(work+"猫用脚走路");
        return "猫用脚走路";
    }
}
