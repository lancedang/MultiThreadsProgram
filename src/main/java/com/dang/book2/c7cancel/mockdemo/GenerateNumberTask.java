package com.dang.book2.c7cancel.mockdemo;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

/**
 * Created by Dangdang on 2018/8/18.
 * 定义可取消任务，该任务是不断生成数字的一个task
 */
public class GenerateNumberTask implements Runnable {

    //用于存放number
    private final List<Double> container = new ArrayList<>();

    //定义是否取消flag，且以volatile修饰使其能在线程间可见
    private boolean canceled;

    @Override
    public void run() {

        //检测取消标识
        while (!canceled) {
            double number = Math.floor(Math.random() * 100);
            //以同步的方式保存该number
            synchronized (this) {
                System.out.println(Thread.currentThread().getName()+ " " + number);
                container.add(number);
                try {
                    //每隔10ms插入一条数据
                    MICROSECONDS.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //对外提供的任务取消接口
    public void cancelTask() {
        this.canceled = true;
    }

    //以同步的方式取出container
    public synchronized List<Double> getNumberList() {
        return new ArrayList<>(container);
    }
}
