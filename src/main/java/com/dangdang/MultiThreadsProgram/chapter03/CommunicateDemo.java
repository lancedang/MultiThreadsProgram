package com.dangdang.MultiThreadsProgram.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dangdang on 2017/4/8.
 */
class CommonObject {
    List<Integer> list = new ArrayList<Integer>();

    public void addE(int x) {
        list.add(x);
    }

    public int getsSize() {
        return list.size();
    }
}

class ComuThreadA extends Thread {
    CommonObject commonObject;

    public ComuThreadA(CommonObject commonObject) {
        this.commonObject = commonObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            commonObject.addE(i);
            System.out.println("add element：" + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class ComuThreadB extends Thread {
    CommonObject commonObject;

    public ComuThreadB(CommonObject commonObject) {
        this.commonObject = commonObject;
    }

    @Override
    public void run() {
        while (true) {
            // System.out.println(".... ");
            try {
                System.out.println("now size = " + commonObject.getsSize());
                if (commonObject.getsSize() == 5) {
                    System.out.println("I'm going out. ");
                    throw new InterruptedException(); //需要接盘侠
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class CommunicateDemo {
    public static void main(String[] ar) {
        CommonObject commonObject = new CommonObject();
        ComuThreadA threadA = new ComuThreadA(commonObject);
        ComuThreadB threadB = new ComuThreadB(commonObject);
        threadA.start();
        threadB.start();
    }
}
