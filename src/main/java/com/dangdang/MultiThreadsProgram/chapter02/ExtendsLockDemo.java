package com.dangdang.MultiThreadsProgram.chapter02;

/**
 * 锁在继承中可重入，可重入是指同一个对象中 同步方法1调用同步方法 2 锁 恒 守，继承中
 * 同步方法1在Father中，同步方法2在Son中，2调1也符合这种情况 父类和子类都对同一个 共享变量进行操作(子类继承的父类成员变量)，需要同步
 * 
 * @author Dangdang
 *
 */
class FatherObject {

	int num = 10;

	public FatherObject() {
		// TODO Auto-generated constructor stub
	}

	synchronized void fatherCompute() {
		this.num--; // 递减操作
		System.out.println("Father fatherCompute(), currentThreadName = " + Thread.currentThread().getName()
				+ ",  num = " + this.num);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	synchronized void readNum() {
		System.out.println("Son readNum(), currentThreadName = " + Thread.currentThread().getName() + "; num = " + num);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class SonObject extends FatherObject {
	synchronized void sonCompute() {
		while (this.num > 0) {
			this.num--; // 我也对父类的num进行处理，会不会冲突呢？
			System.out.println(
					"Son sonCompute, currentThreadName = " + Thread.currentThread().getName() + " , num = " + this.num);
			try {
				Thread.sleep(10000); // 子类同步方法 一旦进入，在调用父类
										// 同步方法前先休息10s，验证此刻，父类其它同步方法能否执行-不能
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.fatherCompute();
		}
	}
}

class MyExtendsThread extends Thread {

	SonObject object;

	public MyExtendsThread(SonObject object) {
		// TODO Auto-generated constructor stub
		this.object = object;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		object.sonCompute();
	}

}

class MyExtendsThread2 extends Thread {

	SonObject object;

	public MyExtendsThread2(SonObject object) {
		// TODO Auto-generated constructor stub
		this.object = object;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		object.readNum();
	}

}

public class ExtendsLockDemo {
	public static void main(String[] args) {
		SonObject object = new SonObject();
		// FatherObject object2 = object
		MyExtendsThread thread = new MyExtendsThread(object);
		MyExtendsThread2 thread2 = new MyExtendsThread2(object);
		thread2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread.start();
	}
}
