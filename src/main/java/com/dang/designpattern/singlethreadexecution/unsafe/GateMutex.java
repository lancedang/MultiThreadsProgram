package com.dang.designpattern.singlethreadexecution.unsafe;

import com.dang.designpattern.singlethreadexecution.Pass;

/**
 * 互斥资源
 */
public class GateMutex implements Pass {
    /**
     * 计数用
     */
    private int count;
    /**
     * 谁通过
     */
    private String name;
    /**
     * 这个人地址
     */
    private String address;

    /**
     * 互斥方法
     *
     * @param name
     * @param address
     */
    public void pass(String name, String address) {
        this.name = name;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.address = address;
        count++;

        //若将check()方法中逻辑抽出来直接写道pass方法中，出现并发冲突的概率很低
        //而抽象出一个方法进行调用的时候，线程冲突的概率很高
        check();
/*        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("count=" + count + ",name:" + name + ", address=" + address);
        }*/
    }

    /**
     * 表示当前gate状态
     */
    private void check() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println(toString());
        }
    }

    public String toString() {
        return "count=" + count + ",name:" + name + ", address=" + address;
    }

}
