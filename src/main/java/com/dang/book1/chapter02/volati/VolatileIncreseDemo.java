package com.dang.book1.chapter02.volati;

/**
 * Created by Dangdang on 2017/4/6.
 */
class VolatileIncrementObject {
    public static volatile int count = 0;
    public static final int THREADS_COUNT = 20;

    public static void increase() {
        count++;
    }
}

public class VolatileIncreseDemo {
    public static void main(String[] arg) {
        Thread[] threads = new Thread[VolatileIncrementObject.THREADS_COUNT];
        for (int i = 0; i < VolatileIncrementObject.THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        VolatileIncrementObject.increase();
                    }
                }
            });
            threads[i].start();
        }
/*        while (Thread.activeCount() > 1) {
            Thread.yield();
        }*/
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(VolatileIncrementObject.count);
    }
}
