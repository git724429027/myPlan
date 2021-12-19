package com.wang.fanse;


/**
 * @author xiaowang
 * @time 2021-12-19 16:23
 **/
public class StaticProxy implements Parent{

    private Object obj;

    public StaticProxy(Object obj){
        this.obj = obj;
    }

    @Override
    public String speak(String something) {
        System.out.println("before");
        Son son = (Son) obj;
        son.speak(something);
        System.out.println("after");
        return something;
    }
}
