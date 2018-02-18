package com.dangdang.MultiThreadsProgram.chapter02;

class SynBlockAdvantageUtil {
	static long begin1;
	static long end1;
	static long begin2;
	static long end2;
}

class SynBlockAdObject {
	private String target1;
	private String target2;

	void show() {
		System.out.println("Begin, Current Thread Name: " + Thread.currentThread().getName() + ", now time: "
				+ System.currentTimeMillis());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String t1 = "长时间处理后从远程返回结果 1 ";
		String t2 = "长时间处理后从远程返回结果 2 ";

		synchronized (this) {

			target1 = t1;
			target2 = t2;

			System.out.println("target1 : " + target1);
			System.out.println("target2 : " + target2);
		}
		System.out.println("End, Current Thread Name: " + Thread.currentThread().getName() + ", now time: "
				+ System.currentTimeMillis());
	}
}

class ThreadA1 extends Thread {
	SynBlockAdObject object;

	public ThreadA1(SynBlockAdObject object) {
		// TODO Auto-generated constructor stub
		this.object = object;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		SynBlockAdvantageUtil.begin1 = System.currentTimeMillis();
		object.show();
		SynBlockAdvantageUtil.end1 = System.currentTimeMillis();
	}

}

class ThreadA2 extends Thread {
	SynBlockAdObject object;

	public ThreadA2(SynBlockAdObject object) {
		// TODO Auto-generated constructor stub
		this.object = object;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		SynBlockAdvantageUtil.begin2 = System.currentTimeMillis();

		object.show();
		SynBlockAdvantageUtil.end2 = System.currentTimeMillis();
	}

}

public class SynBlockAdvantageDemo {
	public static void main(String[] args) {
		SynBlockAdObject object = new SynBlockAdObject();
		ThreadA1 thread1 = new ThreadA1(object);
		ThreadA2 thread2 = new ThreadA2(object);
		// SynMetDisadvantageUtil.begin1 = System.currentTimeMillis();
		thread1.start();
		thread2.start();

		try {
			Thread.sleep(10000); // 充分等待两线程结束
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = SynBlockAdvantageUtil.end1;
		long begin = SynBlockAdvantageUtil.begin1;

		if (SynBlockAdvantageUtil.end1 < SynBlockAdvantageUtil.end2) {
			end = SynBlockAdvantageUtil.end2;
		}

		if (SynBlockAdvantageUtil.begin1 > SynBlockAdvantageUtil.begin2) {
			begin = SynBlockAdvantageUtil.begin2;
		}

		System.out.println("Task Time : " + (end - begin) / 1000);

	}
}
