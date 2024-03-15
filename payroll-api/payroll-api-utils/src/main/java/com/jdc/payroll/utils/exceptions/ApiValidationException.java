package com.jdc.payroll.utils.exceptions;

import java.util.List;

public class ApiValidationException extends ApiBaseException{

	private static final long serialVersionUID = 1L;
	
	public ApiValidationException(List<String> messages) {
		super(messages);
	}

}
