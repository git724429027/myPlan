package com.wang.fanse;

/**
 * @author xiaowang
 * @time 2021-12-19 16:26
 **/
public class TestStaticProxy {
    public static void main(String[] args) {
        StaticProxy staticProxy = new StaticProxy(new Son());
        staticProxy.speak("我说话了");
    }
}
