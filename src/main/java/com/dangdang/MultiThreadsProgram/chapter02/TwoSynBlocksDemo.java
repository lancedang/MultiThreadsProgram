package com.dangdang.MultiThreadsProgram.chapter02;

class TwoSynBlockProveObject {

	void show1() {
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(
						"Synchronized Block, CurrentThreadName = " + Thread.currentThread().getName() + "; i = " + i);
			}
		}

	}

	void show2() {
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(
						"Synchronized Block, CurrentThreadName = " + Thread.currentThread().getName() + "; i = " + i);
			}
		}
	}
}

class TwoSynBlockThread extends Thread {
	TwoSynBlockProveObject object;

	public TwoSynBlockThread(TwoSynBlockProveObject object) {
		// TODO Auto-generated constructor stub
		this.object = object;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		object.show1();
	}

}

class TwoSynBlockThread2 extends Thread {
	TwoSynBlockProveObject object;

	public TwoSynBlockThread2(TwoSynBlockProveObject object) {
		// TODO Auto-generated constructor stub
		this.object = object;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		object.show2();
	}

}

public class TwoSynBlocksDemo {
	public static void main(String[] args) {
		TwoSynBlockProveObject object = new TwoSynBlockProveObject();

		TwoSynBlockThread thread11 = new TwoSynBlockThread(object);
		TwoSynBlockThread2 thread22 = new TwoSynBlockThread2(object);

		thread11.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread22.start();
	}
}
