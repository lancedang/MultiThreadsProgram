package com.dangdang.MultiThreadsProgram.chapter02;

class MyStaticBlockObject {
	public static void test1() {
		synchronized (MyStaticBlockObject.class) {

			System.out.println("Begin, static test1 Method, CurrentThreadName = " + Thread.currentThread().getName());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("End, static test1 Method, CurrentThreadName = " + Thread.currentThread().getName());
		}

	}

	public static void test2() {
		synchronized (MyStaticBlockObject.class) {

			System.out.println("Begin, static test2 Method, CurrentThreadName = " + Thread.currentThread().getName());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("End, static test2 Method, CurrentThreadName = " + Thread.currentThread().getName());

		}
	}

	synchronized public void test3() {
		System.out.println("Begin, test3 Method, CurrentThreadName = " + Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End, test3 Method, CurrentThreadName = " + Thread.currentThread().getName());

	}

	synchronized public void test4() {
		System.out.println("Begin, test3 Method, CurrentThreadName = " + Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End, test3 Method, CurrentThreadName = " + Thread.currentThread().getName());

	}
	
}

class MySynBlockThreadA extends Thread {
	MyStaticBlockObject mystaticObject;

	public MySynBlockThreadA(MyStaticBlockObject mystaticObject) {
		// TODO Auto-generated constructor stub
		this.mystaticObject = mystaticObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		mystaticObject.test1();
	}
}

class MySynBlockThreadB extends Thread {
	MyStaticBlockObject mystaticObject;

	public MySynBlockThreadB(MyStaticBlockObject mystaticObject) {
		// TODO Auto-generated constructor stub
		this.mystaticObject = mystaticObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		mystaticObject.test2();
	}
}

class MySynBlockThreadC extends Thread {
	MyStaticBlockObject mystaticObject;

	public MySynBlockThreadC(MyStaticBlockObject mystaticObject) {
		// TODO Auto-generated constructor stub
		this.mystaticObject = mystaticObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		mystaticObject.test3();
	}
}

public class SynStaticBlockDemo {
	public static void main(String[] args) {

		MyStaticObject mystaticObject = new MyStaticObject();
		MyStaticObject mystaticObject2 = new MyStaticObject();
		MyStaticObject mystaticObject3 = new MyStaticObject();

		MySynThreadA threadA = new MySynThreadA(mystaticObject);

		MySynThreadB threadB = new MySynThreadB(mystaticObject2);
		MySynThreadB threadB2 = new MySynThreadB(mystaticObject3);
		
		 MySynThreadC threadC = new MySynThreadC(mystaticObject);

		//threadA.start();
		threadB.start();
		threadB2.start();
		//threadC.start();
	}
}
