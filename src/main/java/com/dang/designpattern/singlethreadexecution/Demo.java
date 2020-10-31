package com.dang.designpattern.singlethreadexecution;

import com.dang.designpattern.singlethreadexecution.safe.SafeGateMutex;
import com.dang.designpattern.singlethreadexecution.unsafe.GateMutex;

public class Demo {
    public static void main(String[] args) {
        //unsafeFacade();
        safeFacade();
    }

    static void safeFacade() {
        Pass gateMutex = new SafeGateMutex();
        VisitThread visitThread1 = new VisitThread(gateMutex, "zhangs", "zhangs");
        VisitThread visitThread2 = new VisitThread(gateMutex, "lis", "lis");
        VisitThread visitThread3 = new VisitThread(gateMutex, "wangwu", "wangwu");
        VisitThread visitThread4 = new VisitThread(gateMutex, "666", "6666");
        VisitThread visitThread5 = new VisitThread(gateMutex, "8888", "888");

        visitThread1.start();
        visitThread2.start();
        visitThread3.start();
        visitThread4.start();
        visitThread5.start();
    }

    static void unsafeFacade() {
        Pass gateMutex = new GateMutex();
        VisitThread visitThread1 = new VisitThread(gateMutex, "zhangs", "zhangs");
        VisitThread visitThread2 = new VisitThread(gateMutex, "lis", "lis");
        VisitThread visitThread3 = new VisitThread(gateMutex, "wangwu", "wangwu");
        VisitThread visitThread4 = new VisitThread(gateMutex, "666", "6666");
        VisitThread visitThread5 = new VisitThread(gateMutex, "8888", "888");

        visitThread1.start();
        visitThread2.start();
        visitThread3.start();
        visitThread4.start();
        visitThread5.start();
    }
}
