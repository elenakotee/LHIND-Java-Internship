package com.hungernet2.exception;

public class NoCredentialsException extends RuntimeException{

	public NoCredentialsException() {
	}

	public NoCredentialsException(String message) {
		super(message);
	}
}
