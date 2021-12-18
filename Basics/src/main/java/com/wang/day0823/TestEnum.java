package com.wang.day0823;

/**
 * @author xiaowang
 * @time 2021-08-23 21:45
 **/
public enum TestEnum {

    MYSQL("mysql",1),ORACLE("oracle",2);

    private final String name;

    private final int number;

    TestEnum(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
