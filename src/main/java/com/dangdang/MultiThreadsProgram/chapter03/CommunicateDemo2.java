package com.dangdang.MultiThreadsProgram.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dangdang on 2017/4/8.
 */

class CommonObject2 {
    List<String> list = new ArrayList<String>();

    public void addE(String s) {
        list.add(s);
    }

    public int getSize() {
        return list.size();
    }
}

class ComuThread1 extends Thread {
    CommonObject2 commonObject2;

    public ComuThread1(CommonObject2 commonObject2) {
        this.commonObject2 = commonObject2;
    }

    @Override
    public void run() {
        synchronized (commonObject2) {
            for (int i = 0; i < 10; i++) {
                commonObject2.addE(i + "");
                System.out.println("add elements " + i + ", 线程： " + Thread.currentThread().getName());
                try {
                    if (i == 5) {
                        System.out.println("before notify, 线程： " + Thread.currentThread().getName());
                        //当想notify一个线程的时候，先检查一下当前所有线程的状态，是否有处于waiting状态的线程
                        //notify只能通知（唤醒）waiting的线程，否则白叫唤了
                        ThreadGroup g = Thread.currentThread().getThreadGroup();
                        System.out.println("active count is " + g.activeCount());

                        Thread[] all = new Thread[g.activeCount()];
                        g.enumerate(all);
                        for (Thread t : all) {
                            if(t.getName().equals("B")) {
                                System.out.println("Thread B state : " + t.getState());
                            }
                        }


                        commonObject2.notify();
                        System.out.println("after notify, 线程： " + Thread.currentThread().getName());
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ComuThread2 extends Thread {
    CommonObject2 commonObject2;

    public ComuThread2(CommonObject2 commonObject2) {
        this.commonObject2 = commonObject2;
    }

    @Override
    public void run() {
        synchronized (commonObject2) {
            System.out.println("before wait, 线程： " + Thread.currentThread().getName() + " 做事3秒");
            try {
                Thread.sleep(3000);
                commonObject2.wait();
                System.out.println("after wait线程： " + Thread.currentThread().getName());
                System.out.println("do stuff....线程： " + Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class CommunicateDemo2 {
    public static void main(String[] ar) {
        CommonObject2 commonObject2 = new CommonObject2();
        ComuThread1 thread1 = new ComuThread1(commonObject2);
        ComuThread2 thread2 = new ComuThread2(commonObject2);

        thread1.setName("A");
        thread2.setName("B");

        thread2.start(); //先让wait线程运行
/*        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        thread1.start();
    }
}

