package com.dang.book1.chapter02;

class MyStaticObject {
	synchronized public static void test1() {
		System.out.println("Begin, static test1 Method, CurrentThreadName = " + Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End, static test1 Method, CurrentThreadName = " + Thread.currentThread().getName());

	}

	synchronized public static void test2() {
		System.out.println("Begin, static test2 Method, CurrentThreadName = " + Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End, static test2 Method, CurrentThreadName = " + Thread.currentThread().getName());

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

}

class MySynThreadA extends Thread {
	MyStaticObject mystaticObject;

	public MySynThreadA(MyStaticObject mystaticObject) {
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

class MySynThreadB extends Thread {
	MyStaticObject mystaticObject;

	public MySynThreadB(MyStaticObject mystaticObject) {
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

class MySynThreadC extends Thread {
	MyStaticObject mystaticObject;

	public MySynThreadC(MyStaticObject mystaticObject) {
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

public class SynStaticMethodDemo {
	public static void main(String[] args) {
		
		MyStaticObject mystaticObject = new MyStaticObject();
		MyStaticObject mystaticObject2 = new MyStaticObject();
		MyStaticObject mystaticObject3 = new MyStaticObject();
		
		MySynThreadA threadA = new MySynThreadA(mystaticObject);
		
		MySynThreadB threadB = new MySynThreadB(mystaticObject2);
		//MySynThreadC threadC = new MySynThreadC(mystaticObject);
		
		threadA.start();
		threadB.start();
		//threadC.start();
	}
}
