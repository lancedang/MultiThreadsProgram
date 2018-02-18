package com.dangdang.MultiThreadsProgram.fromjvm;

import java.util.Vector;

/**
 * Created by Dangdang on 2017/4/9.
 */
public class VectorSecureDemo {
    private static Vector<Integer> vector = new Vector<Integer>();

    public static void main(String[] sr) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            Runnable removeRun = new Runnable() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };

            Runnable getRun = new Runnable() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        int item = vector.get(i);
                        System.out.println(Thread.currentThread().getName() + ", " + item);
                    }
                }
            };
            Thread removeThread = new Thread(removeRun);
            Thread printThread = new Thread(getRun);

            removeThread.start();
            printThread.start();

            while (Thread.activeCount() < 10) ;
        }
    }
}
