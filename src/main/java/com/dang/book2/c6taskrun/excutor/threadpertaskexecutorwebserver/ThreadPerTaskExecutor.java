package com.dang.book2.c6taskrun.excutor.threadpertaskexecutorwebserver;

import java.util.concurrent.Executor;

/**
 * 为每个task分配一个线程
 */
public class ThreadPerTaskExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        //为每个task分配一个线程
        new Thread(command).start();
    }
}