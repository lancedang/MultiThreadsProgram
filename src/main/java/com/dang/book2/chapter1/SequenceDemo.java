package com.dang.book2.chapter1;

/**
 * Created by Dangdang on 2018/7/16.
 */
public class SequenceDemo {
    public static void main(String[] args) {
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
            for (int i = 0; i < 50; i++) {
                int next = sequence.getNext();
                System.out.println(Thread.currentThread().getName() + ":" +next);
            }
        };

        Thread thread1 = new Thread(target);
        Thread thread2 = new Thread(target);
        thread1.start();
        thread2.start();
    }
}
