package com.amiseq.assignment;

public class Operation implements Runnable {
	
	private String number;
	
	public Operation(String message) {
		this.number = message;
	}

	@Override
	public void run(){
		System.out.println(Thread.currentThread().getName()+" Start >>> Task:" + number);
		System.out.println("Hello from Task " + number);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" End >>>");
	}

}
