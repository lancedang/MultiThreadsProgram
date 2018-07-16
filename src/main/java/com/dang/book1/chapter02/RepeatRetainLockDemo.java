package com.dang.book1.chapter02;

class RepeatObject {
	synchronized void service1() {
		System.out.println("in service1");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service2();
	}

	synchronized void service2() {
		System.out.println("in service2");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service3();
	}

	synchronized void service3() {
		// TODO Auto-generated method stub
		System.out.println("in service3");
	}
}

class RepeatLockThread extends Thread {
	RepeatObject repeatObject;

	public RepeatLockThread(RepeatObject repeatObject) {
		// TODO Auto-generated constructor stub
		this.repeatObject = repeatObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		repeatObject.service1();
	}
}

public class RepeatRetainLockDemo {
	public static void main(String[] args) {
		RepeatObject repeatObject = new RepeatObject();
		RepeatLockThread repeatLockThread = new RepeatLockThread(repeatObject);
		repeatLockThread.start();
	}
}
