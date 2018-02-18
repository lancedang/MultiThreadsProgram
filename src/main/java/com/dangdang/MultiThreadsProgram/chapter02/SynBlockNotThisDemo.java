package com.dangdang.MultiThreadsProgram.chapter02;

class InforSetObject {
	private String name;
	private String pwd;

	String flag = new String(); //锁成员变量
	
	public void setValue(String name, String pwd) {
		synchronized (flag) {
			System.out.println("CurrentThread " + Thread.currentThread().getName() + " , set begin: ");
			this.name = name;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.pwd = pwd;
			System.out.println("CurrentThread " + Thread.currentThread().getName() + ", set ends ; name = " + this.name
					+ ", pwd = " + this.pwd);
		}
	}

}

class MyLockNotThisThreadA extends Thread {
	private InforSetObject dirtyObject;

	public MyLockNotThisThreadA(InforSetObject dirtyObject) {
		// TODO Auto-generated constructor stub
		this.dirtyObject = dirtyObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		dirtyObject.setValue("a", "a");
	}
}

class MyLockNotThisThreadB extends Thread {
	private InforSetObject dirtyObject;

	public MyLockNotThisThreadB(InforSetObject dirtyObject) {
		// TODO Auto-generated constructor stub
		this.dirtyObject = dirtyObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		dirtyObject.setValue("b", "b");
	}
}

public class SynBlockNotThisDemo {
	public static void main(String[] args) {
		InforSetObject dirtyObject = new InforSetObject();

		MyLockNotThisThreadA dirtyThreadA = new MyLockNotThisThreadA(dirtyObject); // 相当于A用户修改自己的姓名和密码，用户的请求用线程处理
		MyLockNotThisThreadB dirtyThreadB = new MyLockNotThisThreadB(dirtyObject); // 同时B用户修改自己的姓名密码，另外一个线程

		dirtyThreadA.start();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dirtyThreadB.start(); // 我去修改name和pwd去了

	}
}
