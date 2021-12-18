package com.wang.day0823;

/**
 * @author xiaowang
 * @time 2021-08-23 21:54
 **/
public class EnumTest {
    public static void main(String[] args) {
        // Enum1 str=Enum1.RED;
        // test1(str);
        System.out.println(TestEnum.MYSQL.getName());
        System.out.println(TestEnum.MYSQL.getNumber());
    }

    static void test2(){

    }

    static void test1(Enum1 str){
        switch (str){
            case RED:
                System.out.println("红的");
                break;
            case BLACK:
                System.out.println("黑的");
                break;
            case WHITE:
                System.out.println("白的");
                break;
            default:
                System.out.println("other");
        }
    }
}
