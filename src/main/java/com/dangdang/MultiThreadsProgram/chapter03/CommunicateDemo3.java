package com.dangdang.MultiThreadsProgram.chapter03;

/**
 * Created by Dangdang on 2017/4/9.
 */
class Service {
    Object object;

    public Service(Object object) {
        this.object = object;
    }

    public void testWait() {
        synchronized (object) {
            System.out.println("" + Thread.currentThread().getName() + " begin wait.....");
            try {
                System.out.println("" + Thread.currentThread().getName() + " sleep for 2 second first.....");
                Thread.sleep(2000);
                System.out.println("" + Thread.currentThread().getName() + " after sleep.....");
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("" + Thread.currentThread().getName() + " after wait, being wakened....");
        }
    }

    public void testWaitMySelf() {
        synchronized (object) {
            try {
                System.out.println("" + System.currentTimeMillis() + "; " + Thread.currentThread().getName() + " begin wait, wait for 5 seconds....");
                object.wait(5000);
                System.out.println("" + System.currentTimeMillis() + "; " + Thread.currentThread().getName() + "  wait over....");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void testNotify() {
        synchronized (object) {
            System.out.println("" + Thread.currentThread().getName() + " begin notify....");
            object.notifyAll();
            System.out.println("" + Thread.currentThread().getName() + " after notify first, now sleep 2 seconds...");
            try {
                Thread.sleep(2000);
                System.out.println("" + Thread.currentThread().getName() + "  notify second...");
                //  object.notify();
                System.out.println("" + Thread.currentThread().getName() + "  notify second over...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("" + Thread.currentThread().getName() + " after notify....");
        }
    }

}

class TestWaitThread implements Runnable {
    Service service;

    public TestWaitThread(Service service) {
        this.service = service;
    }

    public void run() {
        service.testWait();
    }
}

class TestWaitMySelfThread implements Runnable {
    Service service;

    public TestWaitMySelfThread(Service service) {
        this.service = service;
    }

    public void run() {
        service.testWaitMySelf();
    }
}

class TestNotifyThread implements Runnable {
    Service service;

    public TestNotifyThread(Service service) {
        this.service = service;
    }

    public void run() {
        service.testNotify();
    }
}

class InterruptWaitThread implements Runnable {

    public void run() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        int alive = threadGroup.activeCount();
        Thread[] threads = new Thread[alive];
        threadGroup.enumerate(threads);
        for (int i = 0; i < alive; i++) {
            if (threads[i].getName().equals("A")) {
                System.out.println("" + Thread.currentThread().getName() + " is to interrupt " + threads[i].getName() + " after 2 seconds");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threads[i].interrupt();
                System.out.println("" + Thread.currentThread().getName() + " has interrupted " + threads[i].getName());
            }
        }

    }
}

public class CommunicateDemo3 {
    public static void main(String[] arg) {
        Object object = new Object();
        Service service = new Service(object);
        TestWaitThread wait = new TestWaitThread(service);
        TestNotifyThread notify = new TestNotifyThread(service);
        TestWaitMySelfThread waitMySelf = new TestWaitMySelfThread(service);
        InterruptWaitThread interruptWait = new InterruptWaitThread();


        Thread waitThread1 = new Thread(wait);
        Thread waitThread2 = new Thread(wait);
        Thread notifyThread = new Thread(notify);
        Thread waitMySelfThread = new Thread(waitMySelf);

        waitThread1.setName("A");
        waitThread2.setName("B");

        Thread interruptThread = new Thread(interruptWait);

        waitThread1.start();
        waitThread2.start();
        waitMySelfThread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //notifyThread.start();
    }
}
