package com.dang.book1.chapter02;

class InstanceVar {
	int num; // 成员变量，对于同一对象， 是共享资源

	synchronized void compute(String s) {
		if (s.equals("a")) {
			num = 100;
			System.out.println("a set end");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("b".equals(s)) {
			num = 200;
			System.out.println("b set end");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("name = " + s + " ; num = " + num);

	}

}

class MyInstanceVarThreadA extends Thread {
	private InstanceVar instanceVar; // 让每个线程都持有同一个对象，线程执行的是同一对象的逻辑，才会有同步问题

	public MyInstanceVarThreadA(InstanceVar instanceVar) {
		// TODO Auto-generated constructor stub
		this.instanceVar = instanceVar;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		instanceVar.compute("a");
	}
}

class MyInstanceVarThreadB extends Thread {
	private InstanceVar instanceVar; // 让每个线程都持有同一个对象，线程执行的是同一对象的逻辑，才会有同步问题

	public MyInstanceVarThreadB(InstanceVar instanceVar) {
		// TODO Auto-generated constructor stub
		this.instanceVar = instanceVar;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		instanceVar.compute("b");
	}
}

public class InstanceVarThreadSynDemo {
	public static void main(String[] args) {
		InstanceVar instanceVar = new InstanceVar();
		InstanceVar instanceVar2 = new InstanceVar();
		
		MyInstanceVarThreadA threadA = new MyInstanceVarThreadA(instanceVar);
		MyInstanceVarThreadB threadB = new MyInstanceVarThreadB(instanceVar);
		//MyInstanceVarThreadB threadB = new MyInstanceVarThreadB(instanceVar2);

		threadA.start();
		threadB.start();
	}
}
