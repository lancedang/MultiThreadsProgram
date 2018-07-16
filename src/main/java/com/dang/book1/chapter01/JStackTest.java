package com.dang.book1.chapter01;

class MyJStackThread extends Thread {
	int count;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			count = count + 2;
			System.out.println("currentThread.getName = " + Thread.currentThread().getName());
		}

	}

}

public class JStackTest {
	public static void main(String[] args) {
		MyJStackThread myJStackThread = new MyJStackThread();
		myJStackThread.start();
	}
}
