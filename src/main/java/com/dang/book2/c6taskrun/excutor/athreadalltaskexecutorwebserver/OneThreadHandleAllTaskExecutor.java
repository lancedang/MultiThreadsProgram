package com.dang.book2.c6taskrun.excutor.athreadalltaskexecutorwebserver;

import java.util.concurrent.Executor;

/**
 * Created by Dangdang on 2018/7/25.
 * 在一个线程中处理所有任务
 */
public class OneThreadHandleAllTaskExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        //直接调用方法执行任务，不分配线程处理
        command.run();
    }
}
