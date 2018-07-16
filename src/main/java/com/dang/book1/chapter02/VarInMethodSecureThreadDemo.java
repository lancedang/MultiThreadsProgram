package com.dang.book1.chapter02;

/**
 * 方法内的局部变量是私有的，不能共享也就无线程安全问题
 * @author Dangdang
 *
 */
class LocalVarInMethod {
	//int  num = 0;
	void compute(String name) {
		int  num = 0;  //方法内部变量，私有，线程安全
		if (name.equals("a")) {
			num = 100;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("a set end");

		} else if (name.equals("b")) {
			num = 200;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("b set end");
		}
		System.out.println("name = " + name + "; num=" + num);
	}
}

class ThreadA extends Thread {
	private LocalVarInMethod var;

	public ThreadA(LocalVarInMethod var) {
		// TODO Auto-generated constructor stub
		this.var = var;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		var.compute("a");
	}
}

class ThreadB extends Thread {
	private LocalVarInMethod var;

	public ThreadB(LocalVarInMethod var) {
		// TODO Auto-generated constructor stub
		this.var = var;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		var.compute("b");
	}
}

public class VarInMethodSecureThreadDemo {
	public static void main(String[] args) {
		LocalVarInMethod varInMethod = new LocalVarInMethod();
		ThreadA threadA = new ThreadA(varInMethod);
		ThreadB threadB = new ThreadB(varInMethod);
		threadA.start();
		threadB.start();
	}
}
