package com.jdc.payroll.utils.response;

public record DataModificationResult<ID>(
		ID id,
		String message
		) {

}
