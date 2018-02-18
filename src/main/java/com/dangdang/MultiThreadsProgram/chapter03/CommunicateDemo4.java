package com.dangdang.MultiThreadsProgram.chapter03;

/**
 * Created by Dangdang on 2017/4/9.
 */
class Comu4Object {
    Object object = new Object();
    boolean flag = false;
    Runnable waitThread = new Runnable() {
        public void run() {
            synchronized (object) {
                try {
                    while (flag == false) {
                        System.out.println("" + Thread.currentThread().getName() + " begin wait.....");
                        object.wait();
                        System.out.println("" + Thread.currentThread().getName() + " after wait....., now flag = " + flag);
                        System.out.println("" + Thread.currentThread().getName() + " after wait.....");
                    }
                    System.out.println("" + Thread.currentThread().getName() + " not waiting, flag is true, because notify already executed.....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    Runnable notifyThread = new Runnable() {
        public void run() {
            synchronized (object) {
                System.out.println("" + Thread.currentThread().getName() + " begin notify.....");
                object.notify();
                System.out.println("" + Thread.currentThread().getName() + " after notify.....");
                flag = true;
            }
        }
    };

}

public class CommunicateDemo4 {
    public static void main(String[] args) {
        Comu4Object comu4Object = new Comu4Object();

        Thread thread1 = new Thread(comu4Object.waitThread);
        Thread thread2 = new Thread(comu4Object.notifyThread);

        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();

    }
}
