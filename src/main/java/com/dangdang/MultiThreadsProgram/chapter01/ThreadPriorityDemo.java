package com.dangdang.MultiThreadsProgram.chapter01;

class MyThread1 extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		MyThread2 thread2 = new MyThread2();
		thread2.setName("innerThread");
		thread2.start();
	}
}

class MyThread2 extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread2 run method, currentThread name = " + Thread.currentThread().getName()+ " currentThreadPriority = " + Thread.currentThread().getPriority());
	}
}

public class ThreadPriorityDemo {
	public static void main(String[] args) {
		/**
		System.out.println("main thread priority = " + Thread.currentThread().getPriority());
		MyThread2 myThread2 = new MyThread2();
		myThread2.setName("...");
		myThread2.setPriority(4);
		Thread.currentThread().setPriority(8);
		Thread thread = new Thread(myThread2);
		System.out.println("main thread priority = " + Thread.currentThread().getPriority());
		thread.setPriority(6);
		
		thread.start();
		**/
		System.out.println("main thread priority = " + Thread.currentThread().getPriority());
		MyThread1 myThread1 = new MyThread1();
		myThread1.setName("outThread");
		myThread1.start();
		
	}
}
