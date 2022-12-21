package com.amiseq.assignment;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.LinkedList;

import com.amiseq.assignment.exception.InvalidThreadPoolSizeException;

public class MyThreadPool {
	private LinkedList<Runnable> taskQueue;
	private WorkerThread[] threads;
	static Logger logger = System.getLogger(MyThreadPool.class.getName());
	

	public MyThreadPool(int threadCount) {
		if(threadCount<=0) {
			throw new InvalidThreadPoolSizeException("Invalid Thread Pool Size");
		}
		taskQueue = new LinkedList<Runnable>();
		threads = new WorkerThread[threadCount];

		for (int i = 0; i < threadCount; i++) {
			threads[i] = new WorkerThread("Thread: "+ (i+1) + " >");
			threads[i].start();
		}
		logger.log(Level.INFO, "Thread pool initialized");
	}

	public void execute(Runnable task) {
		synchronized (taskQueue) {
			taskQueue.add(task);
			taskQueue.notify();
//			logger.log(Level.INFO, "Task added into the Queue");
		}
	}

	public synchronized void shutdown() throws InterruptedException {
		int counter = 0;
			while (counter < threads.length) {
				for (int i = 0; i < threads.length; i++) {
					if (threads[i].getState() == Thread.State.WAITING) {
						threads[i].stop();
						while(threads[i].getState()!=Thread.State.TERMINATED) {}
						System.out.println("Stopped Thread " + (i+1));
						counter++;
					}
				}
			}
		System.out.println("All threads Stopped");
	}

	public class WorkerThread extends Thread {
		
		WorkerThread(String name){
			super(name);
		}
		public void run() {
			Runnable task;
			while (true) {
				synchronized (taskQueue) {
					while (taskQueue.isEmpty()) {
						try {
							taskQueue.wait();
						} catch (InterruptedException e) {
							logger.log(Level.ERROR, "Error occured while queue is waiting " + e.getMessage());
						}
					}
					task = taskQueue.poll();
				}
				try {
					task.run();
				} catch (Exception e) {
					logger.log(Level.ERROR, "Thread pool interupted " + e.getMessage());
				}
			}
		}
	}
}
