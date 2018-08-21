package com.dang.book2.c6taskrun.timer.multitask;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Dangdang on 2018/8/21.
 * 该类用来展现Timer在执行多个任务时出现的弊端
 */
public class TimerBugDemo {


    public static void main(String[] args) throws InterruptedException {
        //定义main开始时间，便于检验timer delay时间
        System.out.println("main start at " + SecondUtil.getCurrentSecond());

        //twoDelayTask();

        oneLongTimeOnePeriodTask();

        //oneNormalOneThrowTask();

        //executorExecuteTask();

    }

    /**
     * 验证同一个Timer执行两个delay任务出现的彼此间影响
     */
    public static void twoDelayTask() {

        //定义两个正常任务
        NormalTask normalTask1 = new NormalTask();
        NormalTask normalTask2 = new NormalTask();

        Timer timer = new Timer();

        //本意是两个任务同时开始，一个延迟5s,一个延迟2s,但现在...
        timer.schedule(normalTask1, 5000);
        timer.schedule(normalTask2, 2000);

    }

    /**
     * 验证同一个Timer执行一个delay,一个period任务时的彼此影响问题
     */
    public static void oneLongTimeOnePeriodTask() {

        LongTimeTask longTimeTask = new LongTimeTask();
        NormalTask normalTask = new NormalTask();

        Timer timer = new Timer();

        //本意是两个任务同时开始，一个任务延迟4s,另一个延迟2s然后每隔1s周期执行，因为前一个任务需要占用20s长时间
        timer.schedule(longTimeTask, 2000);
        timer.schedule(normalTask, 4000, 1000);

    }

    /**
     * 一个正常任务，一个会抛异常的任务，判断异常是否会对另一个有影响,
     * 本意是一个抛异常了另一个任务能正常执行，不会影响其他任务
     */
    public static void oneNormalOneThrowTask() {

        NormalTask normalTask = new NormalTask();
        ThrowTask throwTask = new ThrowTask();

        Timer timer = new Timer();

        timer.schedule(normalTask, 2000);
        timer.schedule(throwTask, 1000);

    }

    /**
     * 用Executor来执行一个normal一个异常task,判断彼此间是否影响
     */
    public static void executorExecuteTask() {

        NormalTask normalTask = new NormalTask();
        ThrowTask throwTask = new ThrowTask();

        Executor executor = Executors.newFixedThreadPool(1);
        executor.execute(normalTask);
        executor.execute(throwTask);

    }

    /**
     * 定义任务类，模拟出现异常的任务
     */
    static class ThrowTask extends TimerTask {

        @Override
        public void run() {
            throw new RuntimeException();
        }
    }

    /**
     * 定义正常执行较短且无异常的任务类
     */
    static class NormalTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("normal task " + SecondUtil.getCurrentSecond());
        }
    }

    /**
     * 定义执行耗时较长的无异常任务
     */
    static class LongTimeTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("long time task start " + SecondUtil.getCurrentSecond());
            try {
                //任务执行消耗40s
                Thread.sleep(20000);
            } catch (InterruptedException e) {

            }
            System.out.println("long time task end " + SecondUtil.getCurrentSecond());
        }
    }

}
