package com.wang.fanse;

import java.lang.reflect.Constructor;

/**
 * @author xiaowang
 * @time 2021-09-05 1:51
 **/
public class TestConstructor {
    public static void main(String[] args) throws Exception {
        Class<Person> personClass=Person.class;
        Person person=personClass.newInstance();
        Constructor<Person> constructor = personClass.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        Person person1 = constructor.newInstance("lisi", 19);
        System.out.println(person1);
    }
}
