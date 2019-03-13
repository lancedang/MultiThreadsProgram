package com.dang.synextend;

/**
 * 本文讨论父类定义某Synchronized方法，然后
 */
public class SynExtendsDemo {
    public static void main(String[] args) {
        Appender appender1 = new SonAppender();

        Thread thread1 = new Thread(() -> appender1.doAppend(""));
        Thread thread2 = new Thread(() -> appender1.doAppend(""));

        thread1.start();
        thread2.start();

    }


    public void repeatCallDoAppend() {
        Appender appender1 = new SonAppender();
        appender1.doAppend("");
    }
}
