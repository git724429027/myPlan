package com.wang.fanse;

import java.lang.reflect.Proxy;

/**
 * @author xiaowang
 * @time 2021-12-19 16:34
 **/
public class TestDynamicProxy {
    public static void main(String[] args) {
        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler(new Son());
        Parent son = (Parent) Proxy.newProxyInstance(Son.class.getClassLoader(), Son.class.getInterfaces(), dynamicProxyHandler);
        String speak = son.speak("我又说话了");
        System.out.println(speak);
    }
}
