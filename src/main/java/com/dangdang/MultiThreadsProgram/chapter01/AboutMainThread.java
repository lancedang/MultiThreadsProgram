package com.dangdang.MultiThreadsProgram.chapter01;

import java.util.Timer;
import java.util.TimerTask;

public class AboutMainThread {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("timer thread is running");
			}
		}, 1000, 1000);
		System.out.println("main end");
	}
}
