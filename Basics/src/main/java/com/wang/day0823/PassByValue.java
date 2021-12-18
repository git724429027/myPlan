package com.wang.day0823;

import java.util.Arrays;

/**
 * 值传递
 * @author xiaowang
 * @time 2021-08-23 20:10
 **/
public class PassByValue {

    public static void main(String[] args) {
        int i=10;
        testInt(i);//不变
        System.out.println(i);

        String str="hello world";
        testString(str);//不变
        System.out.println(str);

        int[] attr={0,1,2};
        testAttr(attr);//变
        System.out.println(Arrays.toString(attr));

        Person person = new Person();
        person.setAge(1);
        person.setName("t");
        testPerson2(person);//不会变 --》因为方法接受的参数只是一个变量，这个变量被赋值成了person的地址，
                            // 但是方法体内已经对变量重新赋值了，影响不了最开始的地址的内容
        System.out.println(person);
        testPerson(person);//会变
        System.out.println(person);


    }

    public static void testInt(int i){
        i=20;
        System.out.println("i="+i);
    }

    public static void testString(String str){
        str=str+"?";
        System.out.println("str="+str);
    }

    public static void testAttr(int[] attr){
        attr[0]=1;
        System.out.println("attr="+Arrays.toString(attr));
    }

    public static void testPerson(Person person){
        person.setAge(11);
        person.setName("tom");
        System.out.println("person="+person);
    }

    public static void testPerson2(Person person){
        person=new Person();
        person.setAge(11);
        person.setName("tom");
        System.out.println("person="+person);
    }

}

class Person{
    int age;
    String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}