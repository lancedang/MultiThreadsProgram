package com.dang.book1.chapter02.volati;

/**
 * Created by Dangdang on 2017/4/6.
 */

class SingletonObject {
    private volatile static SingletonObject instance;

    private SingletonObject() {
    }

    public static SingletonObject getInstance() {
        if (instance == null) {
            synchronized (SingletonObject.class) {
                if (instance == null)
                    instance = new SingletonObject();
            }
        }
        return instance;
    }

}

class InstanceTestThread implements Runnable {
    SingletonObject instance;

    public void run() {
        instance = SingletonObject.getInstance();
    }
}


public class SingletonDemo {

    public static void main(String[] args) {
        InstanceTestThread thd = new InstanceTestThread();
        InstanceTestThread th2 = new InstanceTestThread();

        Thread thread1 = new Thread(thd);
        Thread thread2 = new Thread(th2);

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thd.instance == th2.instance);
        System.out.println("1: " + thd.instance);
        System.out.println("2: " + th2.instance);

    }

}


