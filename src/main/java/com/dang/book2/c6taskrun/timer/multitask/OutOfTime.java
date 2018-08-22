package com.dang.book2.c6taskrun.timer.multitask;

import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Dangdang on 2018/8/23.
 */
public class OutOfTime {
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        TimerTask throwTask = new ThrowTask();

        System.out.println("main start " + SecondUtil.getCurrentSecond());

        timer.schedule(throwTask, 3000);

        SECONDS.sleep(3);

        //timer.schedule(throwTask, 3000);

        SECONDS.sleep(7);

        System.out.println("main end " + SecondUtil.getCurrentSecond());


    }

    static class ThrowTask extends TimerTask {

        @Override
        public void run() {
            throw new RuntimeException();
        }
    }

}
