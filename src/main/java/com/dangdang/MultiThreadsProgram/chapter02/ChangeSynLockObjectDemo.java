package com.dangdang.MultiThreadsProgram.chapter02;

import java.util.Random;

/**
 * Created by Dangdang on 2017/4/3.
 */

class FlagObject{
    String attribute1 = "";

}

class ChangeSynLockObject {
    FlagObject flagObject = new FlagObject();
    //String flag = new String("");

    public void show1() {
        synchronized (flagObject) {
            System.out.println(" Begin, show1 method" + "CurrentThreadName = " + Thread.currentThread().getName());
            //flagObject.attribute1 = "...";
            flagObject = new FlagObject();
            try {
                Thread.sleep(3000);
                System.out.println(" Class name = " + this.getClass().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End, show1 method" + "CurrentThreadName = " + Thread.currentThread().getName());
        }
    }

    public void show2() {
        synchronized (flagObject) {
            System.out.println(" Begin, show2 method" + "CurrentThreadName = " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
                System.out.println(" Class name = " + this.getClass().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End, show2 method" + "CurrentThreadName = " + Thread.currentThread().getName());
        }
    }

}

class SynChangeThread extends Thread {
    ChangeSynLockObject synClass;
    public SynChangeThread(ChangeSynLockObject synClass) {
        this.synClass = synClass;
    }
    @Override
    public void run() {
        synClass.show1();
    }
}

class SynChangeThread2 extends Thread {
    ChangeSynLockObject synClass;
    public SynChangeThread2(ChangeSynLockObject synClass) {
        this.synClass = synClass;
    }
    @Override
    public void run() {
        synClass.show2();
    }
}

public class ChangeSynLockObjectDemo {
    public static void main(String[] a) {
        ChangeSynLockObject object = new ChangeSynLockObject();
        SynChangeThread thread1 = new SynChangeThread(object);
        SynChangeThread2 thread2 = new SynChangeThread2(object);
        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();

        System.out.println("Math.random() = "+ Math.random()); //return double between [0.0, 10)
        Random random = new Random();//
        random.nextInt();
    }
}
