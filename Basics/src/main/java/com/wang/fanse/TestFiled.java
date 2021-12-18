package com.wang.fanse;

import java.lang.reflect.Field;

/**
 * @author xiaowang
 * @time 2021-09-05 1:37
 **/
public class TestFiled {
    public static void main(String[] args) throws Exception {
        Class<Person> personClass=Person.class;
        Person person=personClass.newInstance();
        Field field = personClass.getDeclaredField("name");
        field.setAccessible(true);
        field.set(person,"zhangsan");
        System.out.println(person.getName());
    }
}
