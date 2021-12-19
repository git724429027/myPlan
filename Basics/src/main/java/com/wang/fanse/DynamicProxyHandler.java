package com.wang.fanse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author xiaowang
 * @time 2021-12-19 16:28
 **/
public class DynamicProxyHandler implements InvocationHandler {

    private Object obj;

    public DynamicProxyHandler(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("dynamic:before:" + method.getName());
        Object returnValue = method.invoke(obj, args);
        System.out.println("dynamic:after:" + method.getName());
        return returnValue;
    }
}
