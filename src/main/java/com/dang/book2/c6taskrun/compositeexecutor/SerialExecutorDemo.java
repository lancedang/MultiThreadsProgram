package com.dang.book2.c6taskrun.compositeexecutor;


/**
 * Created by Dangdang on 2018/7/26.
 */
public class SerialExecutorDemo {

    public static void main(String[] args) {
        SecondExecutor executor = new SecondExecutor();
        SerialExecutor serialExecutor = new SerialExecutor(executor);
        serialExecutor.execute(() -> System.out.println("hello"));
    }

}
