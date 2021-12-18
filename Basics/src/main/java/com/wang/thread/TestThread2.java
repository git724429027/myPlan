package com.wang.thread;

/**
 * @author xiaowang
 * @time 2021-09-04 16:30
 **/
public class TestThread2 {
    public static void main(String[] args) {
        Thread t=new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                if (i==5){
                    try {
                        synchronized (TestThread2.class){
                            TestThread2.class.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        });
        t.start();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
        Object obj = new Object();
        synchronized (obj){
            obj.notify();
            System.out.println("释放了，但没完全释放！");
        }
        synchronized (TestThread2.class){
            TestThread2.class.notify();
            System.out.println("完全释放！");
        }
    }
}
