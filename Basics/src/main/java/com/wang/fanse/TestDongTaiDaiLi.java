package com.wang.fanse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiaowang
 * @time 2021-09-05 2:03
 **/
public class TestDongTaiDaiLi {
    public static void main(String[] args) throws Exception {
        Class<Dog> dogClass=Dog.class;

        Walk c = (Walk) Proxy.newProxyInstance(dogClass.getClassLoader(), dogClass.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("添油加醋");
                String arg= (String) args[0];
                Object invoke = method.invoke(dogClass.newInstance(), arg+"aaaaa");
                System.out.println("结束");
                return invoke;
            }
        });

        System.out.println("返回值："+c.walk("hhhhh"));
    }
    }

