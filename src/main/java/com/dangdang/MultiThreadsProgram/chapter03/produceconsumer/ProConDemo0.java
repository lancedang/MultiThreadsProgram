package com.dangdang.MultiThreadsProgram.chapter03.produceconsumer;

import java.util.ArrayList;
import java.util.List;

class ValueObject0 {
    //public String data = "";
    public List<String> list = new ArrayList<String>();
}

class Producer0 {
    ValueObject0 valueObject0;

    public Producer0(ValueObject0 valueObject0) {
        this.valueObject0 = valueObject0;
    }

    public void produce() {
        synchronized (valueObject0) {
            valueObject0.list.add("sth");
            valueObject0.notify();
        }
    }

}

class Consumer0 {
    ValueObject0 valueObject0;

    public Consumer0(ValueObject0 valueObject0) {
        this.valueObject0 = valueObject0;
    }

    public void consume() {
        synchronized (valueObject0) {
            if (valueObject0.list.size() == 0) {
                try {
                    valueObject0.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            valueObject0.list.remove(0);
        }
    }

}

/**
 * Created by Dangdang on 2017/4/11.
 */
public class ProConDemo0 {
}
