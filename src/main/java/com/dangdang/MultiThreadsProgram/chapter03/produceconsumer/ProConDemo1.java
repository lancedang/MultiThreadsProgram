package com.dangdang.MultiThreadsProgram.chapter03.produceconsumer;


/**
 * 共享资源，存储数据的DataEntity
 */
class ValueObject1 {
    static String value = ""; //让DataEntity刚开始为空字符串，代表没有存储任何东西
}

/**
 * 生产者，操纵DataEntity
 */
class Producer1 {
    ValueObject1 valueObject1;

    public Producer1(ValueObject1 valueObject1) {
        this.valueObject1 = valueObject1;
    }

    public void produce() {
        synchronized (valueObject1) {
            if (valueObject1.value.equals("")) { //生产者进来发现盘子为空，然后占据盘子，烧菜
                try {
                    System.out.println("" + Thread.currentThread().getName() + ", it's empty, now produce sth, than going to notify consume");
                    Thread.sleep(2000); //2s 表明我在生产东西
                    valueObject1.value = "produce sth";
                    valueObject1.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {

                System.out.println("" + Thread.currentThread().getName() + ", already produce sth, now going to wait consumer to consume");
                try {
                    valueObject1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * 生产者，操作DataEntity
 */
class Consumer1 {
    ValueObject1 valueObject1;

    public Consumer1(ValueObject1 valueObject1) {
        this.valueObject1 = valueObject1;
    }

    public void consume() {
        synchronized (valueObject1) {
            if (valueObject1.value.equals("")) {
                try {
                    System.out.println("" + Thread.currentThread().getName() + " ,it's empty, nothing to consume, now going to wait produce");
                    valueObject1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {


                System.out.println("" + Thread.currentThread().getName() + ", consume stuff, now going to notify produce");
                try {
                    Thread.sleep(3000); //3s ,表明我在消费东西嘛
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                valueObject1.value = "";
                valueObject1.notify();
            }
        }
    }
}

class Pro1Thread extends Thread {
    Producer1 producer1;

    public Pro1Thread(Producer1 producer1) {
        this.producer1 = producer1;
    }

    @Override
    public void run() {
        while (true)
            producer1.produce();
    }
}

class Con1Thread extends Thread {
    Consumer1 consumer1;

    public Con1Thread(Consumer1 consumer1) {
        this.consumer1 = consumer1;
    }

    @Override
    public void run() {
        while (true)
            consumer1.consume();
    }
}

/**
 * Created by Dangdang on 2017/4/9.
 */
public class ProConDemo1 {
    public static void main(String[] args) {
        ValueObject1 valueObject1 = new ValueObject1();

        Producer1 producer1 = new Producer1(valueObject1);
        Consumer1 consumer1 = new Consumer1(valueObject1);

        Pro1Thread pro1Thread = new Pro1Thread(producer1);
        Con1Thread con1Thread = new Con1Thread(consumer1);
        pro1Thread.setName("producer");
        con1Thread.setName("consumer");

        pro1Thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        con1Thread.start();

    }
}
