package com.wang.fanse;

public class Person {
    //私有属性
    private String name = "Tom";
    //公有属性
    public int age = 18;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //构造方法
    public Person() {
    }

    //私有方法
    private void say(String say) {
        System.out.println("说了："+say);
    }

    //公有方法
    public void work() {
        System.out.println("public work()...");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}