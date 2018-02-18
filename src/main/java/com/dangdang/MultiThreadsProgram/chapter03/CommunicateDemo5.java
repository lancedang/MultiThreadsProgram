package com.dangdang.MultiThreadsProgram.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dangdang on 2017/4/9.
 */
class ValueOfList {
    public static List<String> list = new ArrayList<String>();
}

class MyAdd {
    ValueOfList list;

    public MyAdd(ValueOfList list) {
        this.list = list;
    }

    public void addE(String ss) {
        synchronized (list) {
            ValueOfList.list.add("everything");
            System.out.println("" + Thread.currentThread().getName() + " add elements, now begin notify.....");
            //list.notify();
            list.notifyAll();
        }
    }
}

class MySubscribe {
    ValueOfList list;

    public MySubscribe(ValueOfList list) {
        this.list = list;
    }

    public void subscribe() {
        synchronized (list) {
            while (list.list.size() == 0) {
                try {
                    System.out.println("" + Thread.currentThread().getName() + " begin wait.....");
                    list.wait();
                    System.out.println("" + Thread.currentThread().getName() + " after wait.....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.list.remove(0);
            System.out.println("" + Thread.currentThread().getName() + " list size = " + ValueOfList.list.size());
        }
    }
}

class MyAddThread extends Thread {
    MyAdd myAdd;

    public MyAddThread(MyAdd myAdd) {
        this.myAdd = myAdd;
    }

    @Override
    public void run() {
        myAdd.addE("sdaf");
    }
}

class MySubThread extends Thread {
    MySubscribe mySubscribe;

    public MySubThread(MySubscribe mySubscribe) {
        this.mySubscribe = mySubscribe;
    }

    @Override
    public void run() {
        mySubscribe.subscribe();
    }
}

public class CommunicateDemo5 {
    public static void main(String[] args) {
        ValueOfList valueOfList = new ValueOfList();

        MyAdd myAdd = new MyAdd(valueOfList);
        MySubscribe mySubscribe = new MySubscribe(valueOfList);

        MyAddThread addThread = new MyAddThread(myAdd);
        MySubThread subThread = new MySubThread(mySubscribe);
        MySubThread subThread2 = new MySubThread(mySubscribe);

        subThread.start();
        subThread2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addThread.start();
    }
}
