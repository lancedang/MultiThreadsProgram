package com.dang.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * CountDownLatch控制多个不相关（无顺序关联）任务并行执行，且考虑其中任务抛exception情况
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Map<Integer, Future> futureMap = new HashMap<>();
        Map<Integer, Integer> result = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            Future<Integer> future = executorService.submit(new MyTask(i, countDownLatch));
            futureMap.put(i, future);
        }

        try {
            //最多等待60s，否则超时，中间若被中断或者出现ExecutionException则捕获异常
            if (countDownLatch.await(60, TimeUnit.SECONDS)) {
                Set<Map.Entry<Integer, Future>> entries = futureMap.entrySet();
                for (Map.Entry<Integer, Future> entry : entries) {
                    Future future = entry.getValue();
                    Object o = future.get();
                    result.put(entry.getKey(), (Integer) o);
                }
                System.out.println(result);
            } else {
                //当任务抛异常（中断或ExecutionException时，不会进入该超时代码块）
                System.out.println("超时,任务未完成");
            }
        } catch (InterruptedException e) {
            //在超时前发生中断
            System.out.println("线程被中断,任务未完成");
            e.printStackTrace();
        } catch (ExecutionException e) {
            //在超时前发生ExecutionException
            System.out.println("线程执行throw exception,任务未完成");
            e.printStackTrace();
        }

    }
}

class MyTask implements Callable {

    private int number;

    private CountDownLatch countDownLatch;

    public MyTask(int number, CountDownLatch countDownLatch) {
        this.number = number;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public Integer call() throws Exception {

        try {
            if (number != 2) {
                System.out.println(number);
            }else {
                //抛异常
                throw new Exception("this is exception");
            }
        } finally {
            //这里没有catch代码块，代码先进入finally，然后向外抛异常，资源清理和异常处理解耦
            //不论call代码执行是否正常，countdownlatch都会减一
            countDownLatch.countDown();
        }
        return number*2;
    }
}
