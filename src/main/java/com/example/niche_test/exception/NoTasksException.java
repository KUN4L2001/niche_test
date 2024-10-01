package com.example.niche_test.exception;

public class NoTasksException extends RuntimeException {

	private String msg;
	
	public NoTasksException(String msg) {
		super();
		this.msg = msg;
	}
	
	public String getMessage(String msg) {
		return msg;
	}
}
