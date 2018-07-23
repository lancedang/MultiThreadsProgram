package com.dang.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Dangdang on 2018/7/21.
 * 验证BlockingQueue的线程安全性：通过对同一个阻塞队列添加多个消费者来验证，多个消费者代表多个独立线程，多个线程同时处理同一个阻塞队列
 */
public class BlockQueueDemo {

    public static void main(String[] args) {
        //生产者-消费者共享的阻塞队列
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(1000);

        MyProducer producer = new MyProducer(queue);

        //多个消费者
        MyConsumer consumer1 = new MyConsumer(queue);
        MyConsumer consumer2 = new MyConsumer(queue);

        Thread proThread = new Thread(producer, "pro");
        Thread conThread1 = new Thread(consumer1, "con1");
        Thread conThread2 = new Thread(consumer2, "con2");

        proThread.start();
        conThread1.start();
        conThread2.start();

    }
}

class MyConsumer implements Runnable {

    private BlockingQueue<String> queue;

    public MyConsumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        //永不停歇的取出数据,验证多消费者的线程安全性
        while (true) {
            get();
        }
    }

    private void get() {
        //poll操作，队列为空返回null
        String poll = queue.poll();
        if (poll != null) {
            System.out.println(Thread.currentThread().getName() + " get: " + poll);
        }
    }
}

class MyProducer implements Runnable {

    private BlockingQueue<String> queue;

    public MyProducer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        //插入数据
        int i = 1000;

        while (i > 0) {
            put(i);
            i--;
        }
    }

    private void put(int i) {
        //插入数据，若队列full返回false
        queue.offer("hello-" + i);
        System.out.println(Thread.currentThread().getName() + " add: hello-" + i);
    }
}
