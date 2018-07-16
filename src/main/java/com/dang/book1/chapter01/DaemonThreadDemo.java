package com.dang.book1.chapter01;

class MyDaemonThread extends Thread {
	private int i;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			i++;
			System.out.println("i = " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("我在睡觉，谁把我叫醒的，中断我有什么事吗？");
				e.printStackTrace();
			}
		}
		
	}
}

public class DaemonThreadDemo {
	public static void main(String[] args) throws InterruptedException {
		MyDaemonThread thread = new MyDaemonThread();
		thread.setDaemon(true);
		thread.start();
		Thread.sleep(10000);
		System.out.println("Now, i main book1 gets out");
	}
}
