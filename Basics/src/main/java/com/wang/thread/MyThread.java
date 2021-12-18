package com.wang.thread;


/**
 * @author xiaowang
 * @time 2021-09-04 14:16
 **/
public class MyThread extends Thread {

    private static volatile int number=10;

    @Override
    public void run() {

            while (true){
                synchronized (MyThread.class){
                    if (number<=0){
                        break;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"拿到了第"+number--+"票");
                }
            }
    }
}
