package com.dang.book2.c6taskrun.compositeexecutor;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

public class SerialExecutor implements Executor {
    final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
    final Executor executor;
    Runnable active;

    SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    public synchronized void execute(final Runnable r) {
        //提交任务到queue
        tasks.offer(new Runnable() {
            public void run() {
                try {
                    r.run();
                } finally {
                    scheduleNext();
                }
            }
        });
        //然后，判断当前是否有正在被执行的任务
        if (active == null) {
            //若没有任务，则将任务交给第二个executor来执行
            scheduleNext();
        }
    }

    /**
     * 从queue取出队首task用第二个executor执行并移除该任务
     */
    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }
}