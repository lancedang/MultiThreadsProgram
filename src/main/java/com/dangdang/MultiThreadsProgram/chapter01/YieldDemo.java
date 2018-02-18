package com.dangdang.MultiThreadsProgram.chapter01;

class MyYieldThread extends Thread {
	static int a;
	static String ss;
	int b;
	String s;
	public MyYieldThread() {
		// TODO Auto-generated constructor stub
		a = 3;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		super.run();
		long count = 0;
		long begin = System.currentTimeMillis();
		for (long i = 0; i < 50000000; i++) {
			Thread.yield(); //让执行线程让出CPU，导致运行时间变长
			count = count + (i+1);
		}
		long end = System.currentTimeMillis();
		System.out.println("time = " + (end - begin));
	}
}

public class YieldDemo {
	public static void main(String[] args) {
		MyYieldThread myYieldThread = new MyYieldThread();
		myYieldThread.start();
		System.out.println(MyYieldThread.a);
		System.out.println(myYieldThread.b);
		System.out.println(myYieldThread.s);
		System.out.println(myYieldThread.ss);
	}
}
