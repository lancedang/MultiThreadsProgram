package com.dang.book1.chapter02;

class MyTask {

	/*
	 * 无锁方法e,也就无法同步
	 */
	void e() {
		System.out.println("currentThread  " + Thread.currentThread().getName() + " begins in method e");
		try {
			Thread.sleep(5000); // 当前线程交出CPU给，其它线程执行(无锁)
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("currentThread  " + Thread.currentThread().getName() + " ends");
	}

	/**
	 * 同步方法f
	 */
	synchronized void f() {
		System.out.println("currentThread  " + Thread.currentThread().getName() + " begins in method f");
		try {
			Thread.sleep(5000); // 当前线程交出CPU，但是持有锁不释放
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("currentThread  " + Thread.currentThread().getName() + " ends");
	}

	synchronized void g() {
		System.out.println("currentThread  " + Thread.currentThread().getName() + " begins in method g");
		try {
			Thread.sleep(5000); // 当前线程交出CPU，但是持有锁不释放
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("currentThread  " + Thread.currentThread().getName() + " ends");
	}
}

class ThreadAA extends Thread {
	MyTask myTask;

	public ThreadAA(MyTask myTask) {
		// TODO Auto-generated constructor stub
		this.myTask = myTask;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		myTask.e(); // 调用非同步方法
	}
}

class ThreadBB extends Thread {
	MyTask myTask;

	public ThreadBB(MyTask myTask) {
		// TODO Auto-generated constructor stub
		this.myTask = myTask;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		myTask.f(); // 调用同步方法
	}
}

class ThreadCC extends Thread {
	MyTask myTask;

	public ThreadCC(MyTask myTask) {
		// TODO Auto-generated constructor stub
		this.myTask = myTask;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		myTask.g(); // 调用同步方法
	}
}

public class SynchronizedToObjectDemo {
	public static void main(String[] args) {
		MyTask myTask = new MyTask();

		ThreadAA threadAA = new ThreadAA(myTask);
		ThreadAA threadAA2 = new ThreadAA(myTask);

		// threadAA.start();
		// threadAA2.start();

		ThreadBB threadBB = new ThreadBB(myTask);
		ThreadBB threadBB2 = new ThreadBB(myTask);

		// threadBB.start();
		// threadBB2.start();

		// threadAA.start(); // 调用同一个对象的非同步方法
		// threadBB.start(); // 调用同一个对象的同步方法

		ThreadCC threadCC = new ThreadCC(myTask);
		threadBB.start();
		threadCC.start();

	}
}
