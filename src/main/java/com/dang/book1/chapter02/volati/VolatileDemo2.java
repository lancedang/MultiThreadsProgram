package com.dang.book1.chapter02.volati;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dangdang on 2017/4/3.
 */
class MyVolatileObject implements Runnable {
    private static int count = 0;
    private AtomicInteger count2 = new AtomicInteger(0);

    public int getCount() {
        return count;
    }

    public int getCount2() {
        return count2.get();
    }

    synchronized public void show() {
        //System.out.println("in, " + "" + Thread.currentThread().getName());
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("out, " + "CurrentThreadName = " + Thread.currentThread().getName() + ", count = " + count + "; class = " + this);
    }

    public void show2() {
        for (int i = 0; i < 100; i++) {
            count2.getAndIncrement();
        }
        System.out.println("out, " + "CurrentThreadName = " + Thread.currentThread().getName() + ", count = " + count2.get() + "; class = " + this);
    }

    public void show3() {

        System.out.println("add 100, " + "CurrentThreadName = " + Thread.currentThread().getName() + ", count = " + count2.get() + "; class = " + this);
        count2.addAndGet(100);
        count2.addAndGet(1);
        System.out.println("add 1, " + "CurrentThreadName = " + Thread.currentThread().getName() + ", count = " + count2.get() + "; class = " + this);
    }

    public void run() {
        show3();
    }
}

public class VolatileDemo2 {
    public static void main(String[] a) {
        MyVolatileObject myVolatileObject = new MyVolatileObject();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(myVolatileObject);
            threads[i].start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count = " + myVolatileObject.getCount2());
    }
}
