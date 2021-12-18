package com.wang.fanse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author xiaowang
 * @time 2021-09-05 1:13
 **/
public class TestProperties {
    public static void main(String[] args) {
        Properties properties = new Properties();
        // test1(properties);
        test2(properties);
    }

    static void test2(Properties properties){
        //获取src下的配置文件
        InputStream inputStream = TestProperties.class.getClassLoader().getResourceAsStream("test.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String name = properties.getProperty("name");
        String age = properties.getProperty("age");
        System.out.println(name+":"+age);
    }

    static void test1(Properties properties){
        FileInputStream inputStream=null;
        try {
            //这种方式用来加载当前mould的配置文件
            inputStream = new FileInputStream("test.properties");
            properties.load(inputStream);
            String name = properties.getProperty("name");
            String age = properties.getProperty("age");
            System.out.println(name+":"+age);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
