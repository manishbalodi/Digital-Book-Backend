package com.digitalbooks.userservice.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
	
	private String message;
	private LocalDateTime now;
	
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorResponse(String message) {
		super();
		this.message = message;
		this.now = LocalDateTime.now();
	}
	
	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + ", now=" + now + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getNow() {
		return now;
	}
	public void setNow(LocalDateTime now) {
		this.now = now;
	}

}
