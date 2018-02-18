package com.dangdang.MultiThreadsProgram.chapter02;

class Father {
	synchronized void show() {
		System.out.println(
				"Father, ThreadName：" + Thread.currentThread().getName() + ", begin: " + System.currentTimeMillis());
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Father,ThreadName：" + Thread.currentThread().getName() + ", end: " + System.currentTimeMillis());
	}
}

class Son extends Father {
	
	synchronized void show2() {
		// TODO Auto-generated method stub
		System.out.println(
				"Son,ThreadName：" + Thread.currentThread().getName() + ", begin: " + System.currentTimeMillis());
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Son, ThreadName：" + Thread.currentThread().getName() + ",end: " + System.currentTimeMillis());
	}
}

class SynNotExtendThread extends Thread {
	Son son;

	public SynNotExtendThread(Son son) {
		// TODO Auto-generated constructor stub
		this.son = son;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		son.show();
	}
}

public class SynMethodNotExtendDemo {
	public static void main(String[] args) {
		Son son = new Son();
		SynNotExtendThread thread1 = new SynNotExtendThread(son);
		SynNotExtendThread thread2 = new SynNotExtendThread(son);

		thread1.start();
		thread2.start();

	}
}
