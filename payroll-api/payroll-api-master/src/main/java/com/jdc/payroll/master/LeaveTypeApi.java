package com.jdc.payroll.master;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.payroll.utils.response.ApiResponse;

@RestController
@RequestMapping("leave-type")
public class LeaveTypeApi {

	ApiResponse<?> search() {
		return ApiResponse.success(null);
	}
	
	ApiResponse<?> findById() {
		return ApiResponse.success(null);
	}
	
	ApiResponse<?> create() {
		return ApiResponse.success(null);
	}	

	ApiResponse<?> update() {
		return ApiResponse.success(null);
	}	
}
