package com.wang.io;

import java.io.*;

/**
 * @author xiaowang
 * @time 2021-09-04 18:54
 **/
public class TestFilePutStream {
    public static void main(String[] args) {
        File file = new File("D:/Donload/images/头像/头2.jpg");
        File file2 = new File("D:\\Code\\logs\\2.jpg");
        //字节流
        FileInputStream inputStream=null;
        FileOutputStream outputStream=null;
        try {
            inputStream = new FileInputStream(file);
            outputStream=new FileOutputStream(file2);
            byte[] size=new byte[10];
            int len;
            while ((len=inputStream.read(size))!=-1){
                outputStream.write(size,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
