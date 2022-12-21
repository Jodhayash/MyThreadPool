package com.amiseq.assignment.exception;

public class InvalidThreadPoolSizeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidThreadPoolSizeException(String msg){
		super(msg);
	}

}
