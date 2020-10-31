package com.dang.designpattern.singlethreadexecution;

import com.dang.designpattern.singlethreadexecution.unsafe.GateMutex;

public class VisitThread extends Thread{

    private Pass gateMutex;
    private final String name;
    private final String address;

    public VisitThread(Pass gateMutex, String name, String address) {
        this.gateMutex = gateMutex;
        this.address = address;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            gateMutex.pass(name, address);
        }
    }
}
