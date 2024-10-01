package com.example.niche_test.exception;

public class TaskNotFoundException extends Exception {

	private String msg;
	
	public TaskNotFoundException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public String getMessage(String msg) {
		return msg;
	}
}
