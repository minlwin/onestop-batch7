package com.jdc.payroll.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.payroll.master.input.PermissionUpdateForm;
import com.jdc.payroll.master.service.PermissionService;
import com.jdc.payroll.utils.response.ApiResponse;
import com.jdc.payroll.utils.response.DataModificationResult;

@RestController
@RequestMapping("permission")
public class PermissionsApi {
	
	@Autowired
	private PermissionService service;

	@PutMapping
	ApiResponse<DataModificationResult<Integer>> update(
			@RequestBody @Validated PermissionUpdateForm form, BindingResult result) {
		return ApiResponse.success(service.update(form));
	}
}
