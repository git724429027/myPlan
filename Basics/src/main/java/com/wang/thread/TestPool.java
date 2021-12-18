package com.wang.thread;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaowang
 * @time 2021-12-14 21:43
 **/
public class TestPool {



}


class BlockingQueue<T>{

    //任务队列
    private Deque<T> queue = new ArrayDeque<>();

    private ReentrantLock lock = new ReentrantLock();

    private Condition fullWait = lock.newCondition();

    private Condition emptyWait = lock.newCondition();

    private int capcity;

    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    public T take(){
        lock.lock();

        T data;
        try {
            while (queue.isEmpty()){
                try {
                    emptyWait.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            data = queue.remove();
            fullWait.signal();
        } finally {
            lock.unlock();
        }
        return data;
    }

    public void put(T data){
        lock.lock();

        try {
            while (queue.size()==capcity){
                try {
                    fullWait.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add(data);
            emptyWait.signal();
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}