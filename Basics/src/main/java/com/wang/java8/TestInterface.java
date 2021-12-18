package com.wang.java8;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author xiaowang
 * @time 2021-12-14 20:53
 **/

public class TestInterface {


    public void consumer(String str , Consumer<String> con){
        con.accept(str);
    }

    @Test
    public void test1(){
        consumer("l love you", System.out::println);
    }

    public Employee receive(Supplier<Employee> supplier){
        return supplier.get();
    }

    @Test
    public void test2(){
        Employee employee = receive(() -> new Employee("zhangsan"));
        System.out.println(employee.getName());

    }


}
