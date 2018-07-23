package com.dang.book2.c2threadsafety;

/**
 * Created by Dangdang on 2018/7/19.
 * 某个线程请求由其他线程获取的锁时，发出请求的线程就会阻塞；但当某个线程想要获取已经由他持有的锁时，这个请求就会成功
 */
public class ThreadReEnter {
    public static void main(String[] arg) {
        Widget logWidget = new LogWidget();
        Thread thread1 = new Thread(() -> logWidget.doSth());
        Thread thread2 = new Thread(() -> logWidget.doSth());

        thread1.start();
        thread2.start();
    }
}

class Widget {
    public synchronized void doSth() {
        System.out.println("current thread: " + Thread.currentThread().getName());
        System.out.println("father widget, hascode=" + this.hashCode());
    }
}

class LogWidget extends Widget {
    //与父亲是同一把锁，this锁住了子doSth方法，也锁住了super.doSth方法
    public synchronized void doSth() {
        System.out.println("current thread: " + Thread.currentThread().getName());
        System.out.println("son LogWidget,hascode=" + this.hashCode());
        super.doSth();
    }
}
