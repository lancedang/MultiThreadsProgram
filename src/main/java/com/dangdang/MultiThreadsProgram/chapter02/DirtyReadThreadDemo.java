package com.dangdang.MultiThreadsProgram.chapter02;

class DirtyObject {
	private String name = "a";
	private String pwd = "aa";

	synchronized public void setValue(String name, String pwd) {
		this.name = name;
		try {
			Thread.sleep(3000); // 本来该一气呵成的，结果中间休息3秒，交出了CPU
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pwd = pwd;
		System.out.println(
				"CurrentThread " + Thread.currentThread().getName() + "; name = " + this.name + ", pwd = " + this.pwd);
	}

	public void getValue() {
		System.out.println(
				"CurrentThread " + Thread.currentThread().getName() + "; name = " + this.name + ", pwd = " + this.pwd);
	}

}

class MyDirtyThreadA extends Thread {
	private DirtyObject dirtyObject;

	public MyDirtyThreadA(DirtyObject dirtyObject) {
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

class MyDirtyThreadB extends Thread {
	private DirtyObject dirtyObject;

	public MyDirtyThreadB(DirtyObject dirtyObject) {
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

public class DirtyReadThreadDemo {
	public static void main(String[] args) {
		DirtyObject dirtyObject = new DirtyObject();

		MyDirtyThreadA dirtyThreadA = new MyDirtyThreadA(dirtyObject); // 相当于A用户修改自己的姓名和密码，用户的请求用线程处理
		MyDirtyThreadB dirtyThreadB = new MyDirtyThreadB(dirtyObject); // 同时B用户修改自己的姓名密码，另外一个线程

		//dirtyThreadA.start();
		dirtyThreadB.start(); //我去修改name和pwd去了
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dirtyObject.getValue();
	}
}
