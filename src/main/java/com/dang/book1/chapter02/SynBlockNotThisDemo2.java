package com.dang.book1.chapter02;

class MyObject {
	void task() {
		System.out.println("Begin Object task Method, CurrentThreadName = " + Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End task Method,CurrentThreadName = " + Thread.currentThread().getName());
	}

	synchronized void synTask() {
		System.out.println("Begin Synchronized task Method, CurrentThreadName = " + Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End Synchronized task Method,CurrentThreadName = " + Thread.currentThread().getName());

	}

	void synBlockTask() {
		synchronized (this) {
			System.out.println("Begin Synchronized this Block task Method, CurrentThreadName = "
					+ Thread.currentThread().getName());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(
					"End Synchronized this Block task Method, CurrentThreadName = " + Thread.currentThread().getName());
		}
	}

}

class MyService {
	public void serviceSyn(MyObject myObject) {
		synchronized (myObject) {

			System.out.println("Begin Service Method, CurrentThreadName = " + Thread.currentThread().getName());

			myObject.task();

			System.out.println("End Service Method,, CurrentThreadName = " + Thread.currentThread().getName());
		}
	}
	// 是在这另起MyObject锁对象的一个方法呢还是写在另起线程调用呢
}

class MySynThread extends Thread {
	private MyService myService;
	private MyObject myObject;

	public MySynThread(MyObject myObject, MyService myService) {
		// TODO Auto-generated constructor stub
		this.myService = myService;
		this.myObject = myObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		myService.serviceSyn(myObject);
	}
}

class MySynThread2 extends Thread {
	private MyService myService;
	private MyObject myObject;

	public MySynThread2(MyObject myObject, MyService myService) {
		// TODO Auto-generated constructor stub
		this.myService = myService;
		this.myObject = myObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		myObject.synTask();
	}
}

class MySynThread3 extends Thread {
	private MyService myService;
	private MyObject myObject;

	public MySynThread3(MyObject myObject, MyService myService) {
		// TODO Auto-generated constructor stub
		this.myService = myService;
		this.myObject = myObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		myObject.synBlockTask();
	}
}

public class SynBlockNotThisDemo2 {
	public static void main(String[] args) {
		MyObject dirtyObject = new MyObject();
		MyObject dirtyObject2 = new MyObject();
		MyService myService = new MyService();

		MySynThread dirtyThreadA = new MySynThread(dirtyObject, myService);
		MySynThread2 dirtyThreadB = new MySynThread2(dirtyObject, myService);
		MySynThread3 dirtyThreadC = new MySynThread3(dirtyObject, myService);

		dirtyThreadA.start();

		dirtyThreadB.start();
		dirtyThreadC.start();

	}
}
