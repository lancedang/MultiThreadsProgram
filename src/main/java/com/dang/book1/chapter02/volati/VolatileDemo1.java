package com.dang.book1.chapter02.volati;

/**
 * Created by Dangdang on 2017/4/3.
 */

class MyObject {
    private boolean flag = true;

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void show() {
        System.out.println("begin, show method" + "CurrentThreadName = " + Thread.currentThread().getName());
        while (flag == true) {
            System.out.println(".....");
/*            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        System.out.println("out, show method" + "CurrentThreadName = " + Thread.currentThread().getName());
    }

}

class MyVoThread implements Runnable {
    MyObject myObject;

    public MyVoThread(MyObject myObject) {
        this.myObject = myObject;
    }

    public void run() {
        myObject.show();

    }
}

public class VolatileDemo1 {
    public static void main(String[] a) {
        MyObject myObject = new MyObject();
        Thread thread = new Thread(new MyVoThread(myObject));
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myObject.setFlag(false);
        System.out.println("已经赋值为False");
    }
}

