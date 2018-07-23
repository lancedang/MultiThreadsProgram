package com.dang.book2.c6taskrun.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Dangdang on 2018/7/23.
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        //定义线程间共享 CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(2);

        long start = System.currentTimeMillis();

        WorkThread work1 = new WorkThread("thread1", 2, countDownLatch);
        WorkThread work2 = new WorkThread("thread2", 2, countDownLatch);

        Thread thread1 = new Thread(work1);
        Thread thread2 = new Thread(work2);

        thread1.start();
        thread2.start();

        try {
            //因为 main 线程会异步执行，可能会先于work线程结束，达不到计时效果
            //故调用 await 方法来阻塞 main 线程直到 countDownLatch 为 0
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));

    }

    static class WorkThread extends Thread {
        String workName;
        int workTime;
        CountDownLatch countDownLatch;

        public WorkThread(String workName, int workTime, CountDownLatch countDownLatch) {
            this.workName = workName;
            this.workTime = workTime;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                System.out.println("线程" + workName + " 开始工作");
                Thread.sleep(2000);
                System.out.println("线程" + workName + " 结束工作");

                //每个线程完成一个任务时，将CountDownLatch计数减一，标记完成了一个任务
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

