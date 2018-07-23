package com.dang.book2.c2threadsafety;

/**
 * Created by Dangdang on 2018/7/18.
 */
public class LazyInitRaceDemo {
    public static void main(String[] args) {
        LazyInitRace lazyInitRace = new LazyInitRace();

        Runnable task = () -> lazyInitRace.getInstance();

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
