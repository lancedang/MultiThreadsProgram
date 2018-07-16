package com.dang.book1.chapter02;

class SynBlockProveObject {

	void show() {
		synchronized (this) {
			for (int i = 0; i < 100; i++) {
				System.out.println(
						"Synchronized Block, CurrentThreadName = " + Thread.currentThread().getName() + "; i = " + i);
			}
		}

		for (int i = 0; i < 100; i++) {
			System.out.println(
					"Non Synchronized Block, CurrentThreadName = " + Thread.currentThread().getName() + "; i = " + i);
		}
	}
}

class ThreadAA1 extends Thread {
	SynBlockProveObject object;

	public ThreadAA1(SynBlockProveObject object) {
		// TODO Auto-generated constructor stub
		this.object = object;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		object.show();
	}

}

public class SynBlockProveDemo {
	public static void main(String[] args) {
		SynBlockProveObject object = new SynBlockProveObject();
		
		ThreadAA1 thread11 = new ThreadAA1(object);
		ThreadAA1 thread22 = new ThreadAA1(object);
		
		thread11.start();
		thread22.start();
	}

}
