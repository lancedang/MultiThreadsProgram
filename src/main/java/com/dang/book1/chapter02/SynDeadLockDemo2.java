package com.dang.book1.chapter02;

/**
 * 制造死锁情况，死锁是彼此持有互补一半资源，同时等待对方释放额外一半资源的现象
 * Created by Dangdang on 2017/4/2.
 */


class MySynDeadLockObject {

    Object object1 = new Object();
    Object object2 = new Object();

    public void show1() {
        synchronized (object1) {
            System.out.println(" Begin, show1 method" + "CurrentThreadName = " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (object2) {
                System.out.println("CurrentThreadName = " + Thread.currentThread().getName() + " 请求 另一半资源");
            }
        }
        System.out.println("End, show1 method" + "CurrentThreadName = " + Thread.currentThread().getName());
    }

    public void show2() {
        synchronized (object2) {
            System.out.println(" Begin, show2 method" + "CurrentThreadName = " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (object1) {
                System.out.println("CurrentThreadName = " + Thread.currentThread().getName() + " 请求 另一半资源");
            }
        }
        System.out.println("End, show2 method" + "CurrentThreadName = " + Thread.currentThread().getName());
    }

}

class MySynDeadLockThread1 extends Thread {
    MySynDeadLockObject mySynDeadLockObject;

    public MySynDeadLockThread1(MySynDeadLockObject mySynDeadLockObject) {
        this.mySynDeadLockObject = mySynDeadLockObject;
    }

    @Override
    public void run() {
        mySynDeadLockObject.show1();
    }
}

class MySynDeadLockThread2 extends Thread {
    MySynDeadLockObject mySynDeadLockObject;

    public MySynDeadLockThread2(MySynDeadLockObject mySynDeadLockObject) {
        this.mySynDeadLockObject = mySynDeadLockObject;
    }

    @Override
    public void run() {
        mySynDeadLockObject.show2();
    }
}

class MySynDeadLockThread3 implements Runnable {
    Object object1;
    Object object2;
    String flag;

    public MySynDeadLockThread3(Object object1, Object object2, String flag) {
        this.object1 = object1;
        this.object2 = object2;
        this.flag = flag;
    }

    public void run() {
        if (flag.equals("a")) {
            synchronized (object1) {
                System.out.println(" Begin, " + "CurrentThreadName = " + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println("CurrentThreadName = " + Thread.currentThread().getName() + " 请求 另一半资源");
                }

                System.out.println("End, show1 method" + "CurrentThreadName = " + Thread.currentThread().getName());
            }
        } else {
            synchronized (object2) {
                System.out.println(" Begin, " + "CurrentThreadName = " + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println("CurrentThreadName = " + Thread.currentThread().getName() + " 请求 另一半资源");
                }

                System.out.println("End, " + "CurrentThreadName = " + Thread.currentThread().getName());
            }
        }
    }
}

public class SynDeadLockDemo2 {
    public static void main2(String[] ar) {
        MySynDeadLockObject object = new MySynDeadLockObject();
        MySynDeadLockThread1 thread1 = new MySynDeadLockThread1(object);
        MySynDeadLockThread2 thread2 = new MySynDeadLockThread2(object);

        thread1.start();
        thread2.start();
    }

    public static void main(String[] ar) {
        Object object1 = new Object();
        Object object2 = new Object();

        MySynDeadLockThread3 mySynDeadLockThread3 = new MySynDeadLockThread3(object1, object2, "a");
        MySynDeadLockThread3 mySynDeadLockThread3_2 = new MySynDeadLockThread3(object1, object2, "b");

        Thread thread1 = new Thread(mySynDeadLockThread3);
        Thread thread2 = new Thread(mySynDeadLockThread3_2);

        thread1.start();
        thread2.start();

    }

}
