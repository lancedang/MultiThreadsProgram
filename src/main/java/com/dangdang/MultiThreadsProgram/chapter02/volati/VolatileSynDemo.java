package com.dangdang.MultiThreadsProgram.chapter02.volati;

import org.omg.PortableServer.THREAD_POLICY_ID;

/**
 * Created by Dangdang on 2017/4/4.
 */
class VolatileSynObject {
    private boolean flag;
    Object object = new Object();
    public VolatileSynObject(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void showInfo() {
        System.out.println("begin, show method" + "CurrentThreadName = " + Thread.currentThread().getName());
        while (flag == true) {
            //System.out.println(".....");
            synchronized (object){

            }
        }
        System.out.println("end, show method" + "CurrentThreadName = " + Thread.currentThread().getName());
    }

}

class VolatileSynThread extends Thread {
    VolatileSynObject volatileSynObject;

    public VolatileSynThread(VolatileSynObject volatileSynObject) {
        this.volatileSynObject = volatileSynObject;
    }

    @Override
    public void run() {
        volatileSynObject.showInfo();
    }
}

class VolatileSynThread2 extends Thread {
    VolatileSynObject volatileSynObject;

    public VolatileSynThread2(VolatileSynObject volatileSynObject) {
        this.volatileSynObject = volatileSynObject;
    }

    @Override
    public void run() {
        System.out.println("stop thread begins");
        volatileSynObject.setFlag(false);
        System.out.println("stop thread completed");
    }
}

public class VolatileSynDemo {
    public static void main(String[] a) {
        VolatileSynObject object = new VolatileSynObject(true);

        VolatileSynThread thread1 = new VolatileSynThread(object);
        VolatileSynThread2 thread2 = new VolatileSynThread2(object);

        thread1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
        System.out.println("main over");
    }
}
