package com.dang.threadlocal;

public class ThreadLocalDemo {

    public static void main(String[] args) {

        //GeneralVariableThread variable = new GeneralVariableThread();
        ThreadLocalVariableThread variable = new ThreadLocalVariableThread();

        Thread thread1 = new Thread(variable);
        Thread thread2 = new Thread(variable);

        thread1.start();
        thread2.start();

    }

}

/**
 * 普通的共享变量，多线程访问race condition
 */
class GeneralVariableThread implements Runnable {

    //线程共享变量
    private int number = 0;

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                //每个调用run方法的线程都会执行number++
                number++;
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " number = " + number);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * threadlocal多线程间共享变量，多线程有各自的threadlocal副本，故线程间副本操作互不影响
 */
class ThreadLocalVariableThread implements Runnable {

    //线程间共享变量，多线程操作互补影响
    private ThreadLocal<Integer> number = ThreadLocal.withInitial(() -> 0);

    @Override
    public void run() {
        try {
            //每个调用run方法的线程都会执行number++
            number.set((int) (Math.random() * 100));
            //延迟获取number值，让其它线程调用run方法从而重新set值，看是否覆盖之前线程set的值
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + " number = " + number.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}