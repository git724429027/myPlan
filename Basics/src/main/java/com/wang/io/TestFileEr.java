package com.wang.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author xiaowang
 * @time 2021-09-04 19:33
 **/
public class TestFileEr {
    public static void main(String[] args) {
        //字符流
        FileReader fileReader =null;
        FileWriter fileWriter =null;
        try {
            fileWriter = new FileWriter("D:\\Code\\logs\\error1.log");
            fileReader=new FileReader("D:\\Code\\logs\\error.log");
            char[] size=new char[1024];
            int len;
            while ((len=fileReader.read(size))!=-1){
                fileWriter.write(size,0,len);
                System.out.println(new String(size,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
