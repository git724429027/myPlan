package com.wang.fanse;

import java.lang.reflect.Method;

/**
 * @author xiaowang
 * @time 2021-09-05 1:47
 **/
public class TestMethod {
    public static void main(String[] args) throws Exception {
        Class<Person> personClass=Person.class;
        Person person=personClass.newInstance();
        Method say = personClass.getDeclaredMethod("say", String.class);
        say.setAccessible(true);
        System.out.println(say.invoke(person, "hhhh"));
    }
}
