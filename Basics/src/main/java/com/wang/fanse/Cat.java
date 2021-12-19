package com.wang.fanse;

/**
 * @author xiaowang
 * @time 2021-09-05 2:04
 **/
public class Cat implements Walk {
    @Override
    public String walk(String walk) {
        System.out.println(walk+"猫用脚走路");
        return "猫用脚走路";
    }
}
