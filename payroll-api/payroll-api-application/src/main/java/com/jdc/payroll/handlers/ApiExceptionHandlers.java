package com.jdc.payroll.handlers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.payroll.utils.exceptions.ApiBusinessException;
import com.jdc.payroll.utils.exceptions.ApiValidationException;
import com.jdc.payroll.utils.response.ApiResponse;

@RestControllerAdvice
public class ApiExceptionHandlers {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	ApiResponse<List<String>> handle(ApiValidationException e) {
		return ApiResponse.fails(e.getMessages());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	ApiResponse<List<String>> handle(ApiBusinessException e) {
		return ApiResponse.fails(e.getMessages());
	}
	
}
