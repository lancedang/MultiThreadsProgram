package com.dang.book2.c1summary;

public class SafeSequence {

    private int number;

    /**
     * 返回唯一数值，将在多线程环境下运行，用synchronized保证同步
     *
     * @return
     */
    public synchronized int getNext() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {

        }
        return number++;
    }

}
