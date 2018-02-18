package com.dangdang.MultiThreadsProgram.chapter02;

/**
 * Created by Dangdang on 2017/4/2.
 */

class SynPublicClass {
    class SynInnerClass {
        String flag = new String();

        public void show1() {
            synchronized (this) { //纵然在静态内部类，我依旧不改容颜，锁一个成员变量看看
                System.out.println(" Begin, show1 method" + "CurrentThreadName = " + Thread.currentThread().getName());
                try {
                    System.out.println(" Class name = " + this.getClass().getName());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("End, show1 method" + "CurrentThreadName = " + Thread.currentThread().getName());
        }


        synchronized public void show2() { //纵然在静态内部类，其实也不知道为啥在静态内部类非静态内部类有啥区别，锁this看看
            System.out.println(" Begin, show1 method" + "CurrentThreadName = " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
                System.out.println(" Class name = " + this.getClass().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End, show1 method" + "CurrentThreadName = " + Thread.currentThread().getName());
        }

        public void show3(SynInnerClass2 innerClass2) {
            synchronized (innerClass2) {
                System.out.println(" Begin, SynInnerClass2 show3 method" + "CurrentThreadName = " + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                    System.out.println(" Class name = " + this.getClass().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("End, SynInnerClass2 show3 method" + "CurrentThreadName = " + Thread.currentThread().getName());
            }
        }
    }

    class SynInnerClass2 {
        synchronized public void show4() {
            System.out.println(" Begin, SynInnerClass2 show4 method" + "CurrentThreadName = " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
                System.out.println(" Class name = " + this.getClass().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End, SynInnerClass2 show4 method" + "CurrentThreadName = " + Thread.currentThread().getName());
        }
    }
}

class SynInnerClassThread extends Thread {
    SynPublicClass.SynInnerClass synInnerClass;

    public SynInnerClassThread(SynPublicClass.SynInnerClass synInnerClass) {
        this.synInnerClass = synInnerClass;
    }

    @Override
    public void run() {
        synInnerClass.show1();
    }
}

class SynInnerClassThread2 extends Thread {
    SynPublicClass.SynInnerClass synInnerClass;

    public SynInnerClassThread2(SynPublicClass.SynInnerClass synInnerClass) {
        this.synInnerClass = synInnerClass;
    }

    @Override
    public void run() {
        synInnerClass.show2();
    }
}

class SynInnerClassThread3 extends Thread {
    SynPublicClass.SynInnerClass synInnerClass;
    SynPublicClass.SynInnerClass2 synInnerClass2;

    public SynInnerClassThread3(SynPublicClass.SynInnerClass synInnerClass, SynPublicClass.SynInnerClass2 synInnerClass2) {
        this.synInnerClass = synInnerClass;
        this.synInnerClass2 = synInnerClass2;
    }

    @Override
    public void run() {
        synInnerClass.show3(synInnerClass2);
    }
}

class SynInnerClassThread4 extends Thread {
    SynPublicClass.SynInnerClass2 synInnerClass;

    public SynInnerClassThread4(SynPublicClass.SynInnerClass2 synInnerClass) {
        this.synInnerClass = synInnerClass;
    }

    @Override
    public void run() {
        synInnerClass.show4();
    }
}

public class SynInnerClassDemo {
    public static void main(String[] ar) {
        // SynPublicClass.SynInnerClass synInnerClass = new SynPublicClass.SynInnerClass();
        SynPublicClass.SynInnerClass synInnerClass = new SynPublicClass().new SynInnerClass();
        SynPublicClass.SynInnerClass2 synInnerClass2 = new SynPublicClass().new SynInnerClass2();


        SynInnerClassThread3 thread1 = new SynInnerClassThread3(synInnerClass, synInnerClass2);
        SynInnerClassThread4 thread2 = new SynInnerClassThread4(synInnerClass2);

        thread1.start();
        thread2.start();
    }
}
