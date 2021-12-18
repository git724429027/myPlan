package com.wang.thread;

/**
 * @author xiaowang
 * @time 2021-09-04 12:38
 **/
public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName()+":"+i);
                }
            }
        };
        thread.start();
        Thread.sleep(1);
        System.out.println("main------");

        MyThread myThread1 = new MyThread();
        myThread1.setName("用户1");
        MyThread myThread2 = new MyThread();
        myThread2.setName("用户2");
        MyThread myThread3 = new MyThread();
        myThread3.setName("用户3");
        // myThread1.start();
        // myThread2.start();
        // myThread3.start();
        myThread2.start();
        myThread1.start();
        myThread3.start();

        // MyRunnable runnable = new MyRunnable();
        // Thread thread1 = new Thread(runnable);
        // Thread thread2 = new Thread(runnable);
        // Thread thread3 = new Thread(runnable);
        // thread1.setName("客户1");
        // thread2.setName("客户2");
        // thread3.setName("客户3");
        // thread1.start();
        // thread2.start();
        // thread3.start();

    }
}
