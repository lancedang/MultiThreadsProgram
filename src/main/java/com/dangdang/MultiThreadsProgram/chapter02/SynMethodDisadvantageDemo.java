package com.dangdang.MultiThreadsProgram.chapter02;

class SynMetDisadvantageUtil {
	static long begin1;
	static long end1;
	static long begin2;
	static long end2;
}

class SynMethodDisObject {
	private String target1;
	private String target2;

	synchronized void show() {
		System.out.println("Begin, Current Thread Name: " + Thread.currentThread().getName() + ", now time: " + System.currentTimeMillis());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		target1 = "长时间处理后从远程返回结果 1 ";
		target2 = "长时间处理后从远程返回结果 2 ";
		System.out.println("target1 : " + target1);
		System.out.println("target2 : " + target2);
		System.out.println("End, Current Thread Name: " + Thread.currentThread().getName()  + ", now time: " + System.currentTimeMillis());
	}
}

class Thread1 extends Thread {
	SynMethodDisObject object;

	public Thread1(SynMethodDisObject object) {
		// TODO Auto-generated constructor stub
		this.object = object;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		SynMetDisadvantageUtil.begin1 = System.currentTimeMillis();
		object.show();
		SynMetDisadvantageUtil.end1 = System.currentTimeMillis();
	}

}

class Thread2 extends Thread {
	SynMethodDisObject object;

	public Thread2(SynMethodDisObject object) {
		// TODO Auto-generated constructor stub
		this.object = object;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		SynMetDisadvantageUtil.begin2 = System.currentTimeMillis();
		
		object.show();
		SynMetDisadvantageUtil.end2 = System.currentTimeMillis();
	}

}

public class SynMethodDisadvantageDemo {
	public static void main(String[] args) {
		SynMethodDisObject object = new SynMethodDisObject();
		Thread1 thread1 = new Thread1(object);
		Thread2 thread2 = new Thread2(object);
		//SynMetDisadvantageUtil.begin1 = System.currentTimeMillis();
		thread1.start();
		thread2.start();
		
		try {
			Thread.sleep(10000); //充分等待两线程结束
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = SynMetDisadvantageUtil.end1;
		long begin = SynMetDisadvantageUtil.begin1;
		
		if(SynMetDisadvantageUtil.end1 < SynMetDisadvantageUtil.end2) {
			end = SynMetDisadvantageUtil.end2;
		}
		
		if(SynMetDisadvantageUtil.begin1 > SynMetDisadvantageUtil.begin2) {
			begin = SynMetDisadvantageUtil.begin2;
		}
		
		System.out.println("Task Time : " + (end-begin)/1000);
		
	}
}
