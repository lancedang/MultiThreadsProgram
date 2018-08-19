package com.dang.book2.c7cancel.mockdemo;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Dangdang on 2018/8/18.
 */
public class ClientCaller {
    public static void main(String[] args) throws InterruptedException {
        GenerateNumberTask task = new GenerateNumberTask();

        Thread thread = new Thread(task);
        Thread thread2 = new Thread(task);

        thread.start();
        thread2.start();

        try {
            //main线程休息一秒，以使task执行1s
            SECONDS.sleep(1);
        } finally {
            //1s后向task发送停止命令
            task.cancelTask();
        }
        //读取number container
        List<Double> numberList = task.getNumberList();
        System.out.println(numberList.size());
        System.out.println(numberList);
    }
}
