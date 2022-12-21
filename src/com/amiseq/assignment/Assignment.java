package com.amiseq.assignment;

import java.util.Scanner;

public class Assignment {
	
	public static void main(String[] args) throws InterruptedException {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter the limit of thread pool: ");
		int limit = s.nextInt();
		
		//Initializing Thread pool 
		MyThreadPool myThreadPool = new MyThreadPool(limit);
		
		System.out.println("Enter the number of threads to perform the operation: ");
		int operations = s.nextInt();
		
		for(int i =1;i<=operations;i++) {
			Runnable operation = new Operation(""+i);
			myThreadPool.execute(operation);
		}
		s.close();
		myThreadPool.shutdown();
		
	}

}
