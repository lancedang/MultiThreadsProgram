package com.dang.synextend;

import java.util.concurrent.CountDownLatch;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SonAppender extends AppenderBase {

    @Override
    public void append(Object o){
        for (int i = 0; i < 4; i++) {
            try {
                SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
