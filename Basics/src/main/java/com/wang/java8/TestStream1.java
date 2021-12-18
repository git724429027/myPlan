package com.wang.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author xiaowang
 * @time 2021-09-05 22:40
 **/
public class TestStream1 {
    public static void main(String[] args) {

    }


    //创建Stream
    @Test
    public void test1(){
        //1.可以通过Collection 系列集合提供的stream()或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过 Arrays 中的静态方法stream()获取数组流
        Employee[] emps=new Employee[10];
        Stream<Employee> stream2= Arrays.stream(emps);

        //3.通过Stream 类中的静态方法of()
        Stream<String> stream3=Stream.of("aa","bb","cc");

        //4.创建无限流
        //迭代
        Stream<Integer> stream4=Stream.iterate(0, (x) -> x+2);
        stream4.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);
    }

    @Test
    public void test2(){
        int[] arr=new int[]{3,5,8,9,12,23};
        IntStream stream = Arrays.stream(arr)
                .filter(x -> x >= 8);
        int[] array = stream.toArray();
        System.out.println(Arrays.toString(array));
    }

}
