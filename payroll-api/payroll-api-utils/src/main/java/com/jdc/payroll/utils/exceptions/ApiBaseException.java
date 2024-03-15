package com.jdc.payroll.utils.exceptions;

import java.util.List;

public class ApiBaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private List<String> messages;

	public ApiBaseException(List<String> messages) {
		super();
		this.messages = messages;
	}
	
	public List<String> getMessages() {
		return messages;
	}
}
