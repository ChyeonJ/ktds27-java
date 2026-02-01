package com.ktdsuniversity.edu.restaurant.exception;

public class SoldOutException extends RuntimeException{
	
	public SoldOutException(String message) {
		super(message);
	}

}
