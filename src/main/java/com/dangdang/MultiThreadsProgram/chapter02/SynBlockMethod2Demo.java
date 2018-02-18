package com.dangdang.MultiThreadsProgram.chapter02;

class BlockMethod2Object {

	String flag = new String(); // 锁成员变量

	public void setValue() {
		synchronized (flag) {  //针对成员变量为锁的同步方法--flag线程
			System.out.println("Method setValue1(), CurrentThread " + Thread.currentThread().getName() + " , set begin: ");

			try {
				Thread.sleep(2000); // 我站着茅坑不拉屎
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Method setValue1(), CurrentThread " + Thread.currentThread().getName()  + " , set end");
		}
	}

	/**
	 * 针对this加锁的线程，会阻塞Synchronized(this)同步块，而对Synchronized(notThisObject)异步
	 */
	synchronized public void setValue2() {
		System.out.println("Method setValue2(), CurrentThread " + Thread.currentThread().getName() + " , set begin: ");

		try {
			Thread.sleep(2000); // 我站着茅坑不拉屎
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Method setValue2(), CurrentThread " + Thread.currentThread().getName()  + " , set end");
	}

}

class My2LockThreadA extends Thread {
	private BlockMethod2Object dirtyObject;

	public My2LockThreadA(BlockMethod2Object dirtyObject) {
		// TODO Auto-generated constructor stub
		this.dirtyObject = dirtyObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		dirtyObject.setValue();
	}
}

class My2LockThreadB extends Thread {
	private BlockMethod2Object dirtyObject;

	public My2LockThreadB(BlockMethod2Object dirtyObject) {
		// TODO Auto-generated constructor stub
		this.dirtyObject = dirtyObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		dirtyObject.setValue2();
	}
}

public class SynBlockMethod2Demo {
	public static void main(String[] args) {
		BlockMethod2Object dirtyObject = new BlockMethod2Object();

		My2LockThreadA my2LockThreadA1 = new My2LockThreadA(dirtyObject); // 相当于A用户修改自己的姓名和密码，用户的请求用线程处理
		My2LockThreadA my2LockThreadA2 = new My2LockThreadA(dirtyObject); // 相当于A用户修改自己的姓名和密码，用户的请求用线程处理

		My2LockThreadB my2LockThreadB1 = new My2LockThreadB(dirtyObject); // 同时B用户修改自己的姓名密码，另外一个线程
		My2LockThreadB my2LockThreadB2 = new My2LockThreadB(dirtyObject); // 同时B用户修改自己的姓名密码，另外一个线程

		my2LockThreadA1.start();
		my2LockThreadA2.start();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		my2LockThreadB1.start(); // 我去修改name和pwd去了
		my2LockThreadB2.start(); // 我去修改name和pwd去了

	}
}
