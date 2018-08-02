package com.dang.mdc;

import org.slf4j.MDC;

/**
 * 利用 logback mdc 验证线程共享变量
 */
public class MDCDemo {

    public static void main(String[] args) {
        MdcThread mdcThread = new MdcThread();

        Thread thread1 = new Thread(mdcThread);
        Thread thread2 = new Thread(mdcThread);

        thread1.start();
        thread2.start();
    }

}

/**
 * 在两个线程环境中用MDC保存同名变量，判断后面线程会不会覆盖前面set的value
 */
class MdcThread extends Thread {

    @Override
    public void run() {

        try {
            MDC.put("number", (int) (Math.random() * 100) + "");
            //让出cpu，让其它线程重复put number，看是否被覆盖
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + ", number " + MDC.get("number"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
