package com.dangdang.MultiThreadsProgram.chapter02;

/**
 * Created by Dangdang on 2017/4/2.
 */

class SynDeadLockObject {
    String flag1 = new String( );
    String flag2 = new String(  );

    synchronized public void show1() {
        System.out.println(" Begin, show1 method" + "CurrentThreadName = " + Thread.currentThread().getName());
        boolean flag = true;
        while (flag) {
            System.out.println(Thread.currentThread().getName() + " in show1 circle");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("End, show1 method" + "CurrentThreadName = " + Thread.currentThread().getName());
    }

    synchronized public void show2() {
        System.out.println(" Begin, show2 method" + "CurrentThreadName = " + Thread.currentThread().getName());
        boolean flag = true;
        while (flag) {
            System.out.println(Thread.currentThread().getName() + " in show2 circle");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("End, show1 method" + "CurrentThreadName = " + Thread.currentThread().getName());
    }

    public void show1Plus( ) {
        synchronized (flag1) {
            System.out.println(" Begin, show1Plus method" + "CurrentThreadName = " + Thread.currentThread().getName());
            boolean flag = true;
            while (flag) {
                System.out.println(Thread.currentThread().getName() + " in show1Plus circle");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("End, show1Plus method" + "CurrentThreadName = " + Thread.currentThread().getName());
        }
    }

    public void show2Plus( ) {
        synchronized (flag2) {
            System.out.println(" Begin, show2Plus method" + "CurrentThreadName = " + Thread.currentThread().getName());
            boolean flag = true;
            while (flag) {
                System.out.println(Thread.currentThread().getName() + " in show2Plus circle");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("End, show2Plus method" + "CurrentThreadName = " + Thread.currentThread().getName());
        }
    }

}

class SynDeadLockThread extends Thread {
    SynDeadLockObject synDeadLockObject;

    public SynDeadLockThread(SynDeadLockObject synDeadLockObject) {
        this.synDeadLockObject = synDeadLockObject;
    }

    @Override
    public void run() {
        synDeadLockObject.show1Plus();
    }

}

class SynDeadLockThread2 extends Thread {
    SynDeadLockObject synDeadLockObject;

    public SynDeadLockThread2(SynDeadLockObject synDeadLockObject) {
        this.synDeadLockObject = synDeadLockObject;
    }

    @Override
    public void run() {
        synDeadLockObject.show2Plus();
    }
}

public class SynDeadLockDemo {
    public static void main(String[] args) {
        SynDeadLockObject object = new SynDeadLockObject();

        SynDeadLockThread thread1 = new SynDeadLockThread(object);
        SynDeadLockThread thread1_2 = new SynDeadLockThread(object);

        SynDeadLockThread2 thread2 = new SynDeadLockThread2(object);
        SynDeadLockThread2 thread2_2 = new SynDeadLockThread2(object);

        thread1.start();
        thread1_2.start();

        thread2.start();
        thread2_2.start();

    }
}
