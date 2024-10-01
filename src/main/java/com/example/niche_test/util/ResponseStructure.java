package com.example.niche_test.util;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ResponseStructure<T> {

	private String message;
	private int status;
	private T data;
}
