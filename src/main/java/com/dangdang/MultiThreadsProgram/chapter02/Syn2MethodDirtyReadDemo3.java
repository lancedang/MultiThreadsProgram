package com.dangdang.MultiThreadsProgram.chapter02;

import java.util.ArrayList;
import java.util.List;

class Dirty2SynMObject {
	List<String> list = new ArrayList<String>();

	synchronized public void setValue(String name) {
		System.out.println("CurrentThread " + Thread.currentThread().getName() + " 进入setValue 方法 ");

		list.add(name);
		try {
			Thread.sleep(100); // 本来该一气呵成的，结果中间休息3秒，交出了CPU
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("CurrentThread " + Thread.currentThread().getName() + "退出setValue 方法, name = " + name);
	}

	synchronized public int readSize() {
		System.out.println("CurrentThread " + Thread.currentThread().getName() + " 进入readSize 方法 ");
		int size = list.size();
		try {
			Thread.sleep(100); // 本来该一气呵成的，结果中间休息3秒，交出了CPU
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("CurrentThread " + Thread.currentThread().getName() + " 退出readSize 方法 ,  size = " + size);
		return size;
	}

}

class Dirty2SynMThreadA extends Thread {
	private Dirty2SynMObject dirtyObject;

	public Dirty2SynMThreadA(Dirty2SynMObject dirtyObject) {
		// TODO Auto-generated constructor stub
		this.dirtyObject = dirtyObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for (int i = 0; i < 100; i++) {
			dirtyObject.setValue(Thread.currentThread().getName() + "--- " + i);
		}
	}
}

class Dirty2SynMThreadB extends Thread {
	private Dirty2SynMObject dirtyObject;

	public Dirty2SynMThreadB(Dirty2SynMObject dirtyObject) {
		// TODO Auto-generated constructor stub
		this.dirtyObject = dirtyObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		for (int i = 0; i < 100; i++) {
			dirtyObject.setValue(Thread.currentThread().getName() + "--- " + i);
		}

	}
}

public class Syn2MethodDirtyReadDemo3 {
	public static void main(String[] args) {
		Dirty2SynMObject dirtyObject = new Dirty2SynMObject();

		Dirty2SynMThreadA dirtyThreadA = new Dirty2SynMThreadA(dirtyObject); // 
		Dirty2SynMThreadB dirtyThreadB = new Dirty2SynMThreadB(dirtyObject); //

		dirtyThreadA.start();
		dirtyThreadB.start(); // 我去修改name和pwd去了
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
