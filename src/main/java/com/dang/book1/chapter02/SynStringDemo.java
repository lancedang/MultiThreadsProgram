package com.dang.book1.chapter02;

class MyStringObject {
	public void show(String flag) {
		synchronized (flag) {
			while (true) {
				System.out.println("Begin, CurrentThreadName = " + Thread.currentThread().getName());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("End, CurrentThreadName = " + Thread.currentThread().getName());
			}
		}

	}
}

class MyStringSynThread extends Thread {
	MyStringObject myStringObject;

	String flag;

	public MyStringSynThread(MyStringObject myStringObject, String flag) {
		// TODO Auto-generated constructor stub
		this.myStringObject = myStringObject;
		this.flag = flag;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		myStringObject.show(flag);
	}
}

class MyStringSynThread2 extends Thread {
	MyStringObject myStringObject;
	String flag;

	public MyStringSynThread2(MyStringObject myStringObject, String flag) {
		// TODO Auto-generated constructor stub
		this.myStringObject = myStringObject;
		this.flag = flag;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		myStringObject.show(flag);
	}
}

public class SynStringDemo {
	public static void main2(String[] args) {
		String aString = "a";
		String bString = "a";
		String cString = new String("a");
		System.out.println(aString == cString);
	}

	public static void main(String[] args) {
		MyStringObject myStringObject = new MyStringObject();
		String aString = "A";
		String bString = "A";

		MyStringSynThread thread1 = new MyStringSynThread(myStringObject, aString);
		MyStringSynThread thread2 = new MyStringSynThread(myStringObject, bString);
		// MyStringSynThread2 thread2 = new MyStringSynThread2(myStringObject);
		thread1.start();
		thread2.start();
	}
}
