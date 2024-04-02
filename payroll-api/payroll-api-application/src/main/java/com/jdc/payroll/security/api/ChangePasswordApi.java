package com.jdc.payroll.security.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.payroll.security.api.model.ChangePasswordForm;
import com.jdc.payroll.security.service.ChangePasswordService;
import com.jdc.payroll.utils.response.ApiResponse;
import com.jdc.payroll.utils.response.DataModificationResult;

@RestController
@RequestMapping("/personal/password")
public class ChangePasswordApi {
	
	@Autowired
	private ChangePasswordService service;

	@PostMapping
	ApiResponse<DataModificationResult<String>> changePassword(
			@Validated @RequestBody ChangePasswordForm form, BindingResult result) {
		return ApiResponse.success(service.change(form));
	}
}
