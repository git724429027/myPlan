package com.wang.io;

import java.io.*;

/**
 * @author xiaowang
 * @time 2021-09-04 19:12
 **/
public class TestBufferPutStream {
    public static void main(String[] args) {
        File file = new File("D:/Donload/images/头像/头2.jpg");
        File file2 = new File("D:\\Code\\logs\\3.jpg");
        //缓冲字节流。
        // 为什么需要缓冲流？
        // 当我们用read()读取文件时，每读取一个字节，访问一次硬盘，效率很低。
        // 文件过大时，操作起来不是很方便。因此我们需要用到buffer缓存流，当创建buffer对象时，会创建一个缓存区数组。
        // 当我们读取一个文件时，先从硬盘中读取到缓冲区，然后从缓冲区输出即可，效率会更高。
        BufferedInputStream bufferedInputStream=null;
        BufferedOutputStream bufferedOutputStream=null;
        try {
            bufferedInputStream=new BufferedInputStream(new FileInputStream(file),10240);
            bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(file2),10240);
            byte[] size=new byte[1024];
            int len;
            while ((len=bufferedInputStream.read(size))!=-1){
                bufferedOutputStream.write(size,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedInputStream.close();
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
