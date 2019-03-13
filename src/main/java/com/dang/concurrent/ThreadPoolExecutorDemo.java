package com.dang.concurrent;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0,
            TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(2));

    public void doSth() {
        try {
            Future<?> future = threadPoolExecutor.submit(() -> {
                System.out.println("Pipeline:    " + Thread.currentThread() +
                        ", 活跃线程=" + threadPoolExecutor.getActiveCount() +
                        ", 任务队列task number=" + threadPoolExecutor.getQueue().size() +
                        ", scheduled task number=" + threadPoolExecutor.getTaskCount() +
                        ", completed task number=" + threadPoolExecutor.getCompletedTaskCount());
            });
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutorDemo demo = new ThreadPoolExecutorDemo();

        int x = 10000;

        while (x > 0){
            demo.doSth();
            x--;
        }
    }

}
