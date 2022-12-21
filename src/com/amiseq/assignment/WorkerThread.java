//package com.amiseq.assignment;
//
//import java.lang.System.Logger.Level;
//
//public class WorkerThread extends Thread {
//	
//	WorkerThread(String name){
//		super(name);
//	}
//	public void run() {
//		Runnable task;
//		while (true) {
//			synchronized (MyThreadPool.taskQueue) {
//				while (MyThreadPool.taskQueue.isEmpty()) {
//					try {
//						MyThreadPool.taskQueue.wait();
//					} catch (InterruptedException e) {
//						//logger.log(Level.ERROR, "Error occured while queue is waiting " + e.getMessage());
//					}
//				}
//				task = MyThreadPool.taskQueue.poll();
//			}
//			try {
//				task.run();
//			} catch (Exception e) {
//				//logger.log(Level.ERROR, "Thread pool interupted " + e.getMessage());
//			}
//		}
//	}
//}
