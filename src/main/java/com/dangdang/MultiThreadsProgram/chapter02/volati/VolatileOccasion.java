package com.dangdang.MultiThreadsProgram.chapter02.volati;

import com.dangdang.MultiThreadsProgram.chapter01.ThreadPriorityDemo;

/**
 * Created by Dangdang on 2017/4/6.
 */
class VolatileOccasionObject {
    private volatile boolean flag;

    public VolatileOccasionObject(boolean flag) {
        this.flag = flag;
    }

    public void shutdown() {
        this.flag = false;
    }

    public void work() {
        while (flag == true) {
            System.out.println("begin "  + Thread.currentThread().getName() + " work");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end "  + Thread.currentThread().getName() + " work");

        }
    }

}

public class VolatileOccasion {
    public static void main(String[] ar) {
        final VolatileOccasionObject occasionObject = new VolatileOccasionObject(true);
        Thread[] threads = new Thread[20];
        for (int i = 0; i< 20; i++ ){
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    occasionObject.work();
                }
            });
            threads[i].start();
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        occasionObject.shutdown();
        System.out.println("all end");
    }
}
