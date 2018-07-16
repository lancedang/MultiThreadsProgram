package com.dang.book2.chapter1;

/**
 * Created by Dangdang on 2018/7/16.
 * 非线程安全的数值生成器，非线程安全主要体现在 number++ 操作上，该操作并非原子性，包括3个连续动作
 * 从主存中取number -> 线程内存 number+1 -> 写回到主存number；<br>
 * 但由于执行很快，故本例的两个线程有可能未能体现出最终结果不等于99的现象
 */
public class UnsafeSequence {

    private int number;

    /**
     * 返回唯一数值，将在多线程环境下运行
     *
     * @return
     */
    public int getNext() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {

        }
        return number++;
    }

}
