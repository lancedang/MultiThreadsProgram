package com.dang.book1.chapter02;

import java.util.ArrayList;
import java.util.List;

class MyOneList {
	private List<String> list = new ArrayList<String>();

	synchronized void add(String name) {
		list.add(name);
	}

	synchronized int getSize() {
		return list.size();
	}

}

class MyListService {
	public MyOneList listService(MyOneList list, String data) {
		synchronized (list) {

			if (list.getSize() < 1) { // getSize()是syn方法
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				list.add(data);// add也是syn方法，呵呵
			}
		}
		return list;
	}
}

class MyOneListThreadA extends Thread {
	MyListService service;
	MyOneList list;

	public MyOneListThreadA(MyListService service, MyOneList list) {
		// TODO Auto-generated constructor stub
		this.service = service;
		this.list = list;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		service.listService(list, "A");
	}
}

class MyOneListThreadB extends Thread {
	MyListService service;
	MyOneList list;

	public MyOneListThreadB(MyListService service, MyOneList list) {
		// TODO Auto-generated constructor stub
		this.service = service;
		this.list = list;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		service.listService(list, "B");
	}
}

public class Syn2MethodDirtyReadDemo2 {
	public static void main(String[] args) {
		MyOneList dirtyObject = new MyOneList();
		MyListService service = new MyListService();

		MyOneListThreadA dirtyThreadA = new MyOneListThreadA(service, dirtyObject); //
		MyOneListThreadB dirtyThreadB = new MyOneListThreadB(service, dirtyObject); //

		dirtyThreadA.start();
		dirtyThreadB.start(); // 我去修改name和pwd去了
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("list size = " + dirtyObject.getSize());

	}
}
