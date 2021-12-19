package com.wang.fanse;

/**
 * @author xiaowang
 * @time 2021-12-19 16:20
 **/
public class Son  implements Parent{
    @Override
    public String speak(String something) {
        System.out.println("son:" + something);
        return something;
    }
}
