package com.jdc.payroll.domain;

public class IllegalDomainDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalDomainDataException(String message) {
		super(message);
	}

}
