package com.ihsmarkit.exception;

// A custom exception is created here
public class DuplicateBookingException extends Exception {
	public DuplicateBookingException(String message) {
		super(message);
	}
}
