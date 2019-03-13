package com.dang.book2.c1summary;

/**
 * Created by Dangdang on 2018/7/16.
 */
public class SequenceDemo {

    public static void main(String[] args) {

        SequenceDemo demo = new SequenceDemo();

        demo.unSafeTest();
        //demo.safeTest();

    }

    public void unSafeTest() {
        //线程共享变量
        final UnsafeSequence sequence = new UnsafeSequence();

        //匿名内部类
/*        Runnable old_target = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    int next = sequence.getNext();
                    System.out.println(next);
                }
            }
        };*/

        //lambda 代替可以代替匿名内部类,也可直接将=右边表达式放到Thread参数中
        Runnable target = () -> {
            for (int i = 0; i < 5; i++) {
                int next = sequence.getNext();
                System.out.println(Thread.currentThread().getName() + ":" + next);
            }
        };

        //多线程操作共享变量
        Thread thread1 = new Thread(target);
        Thread thread2 = new Thread(target);
        thread1.start();
        thread2.start();
    }

    public void safeTest() {
        SafeSequence safeSequence = new SafeSequence();

        Runnable target2 = () -> {
            for (int i = 0; i < 5; i++) {
                int next = safeSequence.getNext();
                System.out.println(Thread.currentThread().getName() + ":" + next);
            }
        };

        //多线程操作共享变量
        Thread thread1 = new Thread(target2);
        Thread thread2 = new Thread(target2);
        thread1.start();
        thread2.start();
    }

}
