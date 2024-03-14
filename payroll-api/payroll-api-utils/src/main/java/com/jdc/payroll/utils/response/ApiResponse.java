package com.jdc.payroll.utils.response;

import java.time.LocalDateTime;

public record ApiResponse<T>(
		boolean success,
		LocalDateTime responseAt,
		T payload
		) {

	public static<T> ApiResponse<T> success(T payload) {
		return new ApiResponse<T>(true, LocalDateTime.now(), payload);
	}

	public static<T> ApiResponse<T> fails(T payload) {
		return new ApiResponse<T>(false, LocalDateTime.now(), payload);
	}
}
