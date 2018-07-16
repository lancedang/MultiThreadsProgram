package com.dang.book1.chapter02;

class SynBlockObject {
	void show() {
		synchronized (this) {

			System.out.println("Begin, Current Thread Name: " + Thread.currentThread().getName() + ", now time: "
					+ System.currentTimeMillis());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("end, Current Thread Name: " + Thread.currentThread().getName() + ", now time: "
					+ System.currentTimeMillis());
		}
	}
}

class Thread11 extends Thread {
	SynBlockObject object;

	public Thread11(SynBlockObject object) {
		// TODO Auto-generated constructor stub
		this.object = object;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		object.show();
	}

}

class Thread22 extends Thread {
	SynBlockObject object;

	public Thread22(SynBlockObject object) {
		// TODO Auto-generated constructor stub
		this.object = object;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		object.show();
	}

}

public class SynBlockDemo {
	public static void main(String[] args) {
		SynBlockObject object = new SynBlockObject();
		Thread11 thread11 = new Thread11(object);
		Thread22 thread22 = new Thread22(object);
		thread11.start();
		thread22.start();
	}
}
