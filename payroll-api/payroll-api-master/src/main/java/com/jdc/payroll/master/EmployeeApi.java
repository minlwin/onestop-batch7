package com.jdc.payroll.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.payroll.master.input.EmployeeFormForCreate;
import com.jdc.payroll.master.input.EmployeeFormForUpdate;
import com.jdc.payroll.master.input.EmployeeFormForUpdatePosition;
import com.jdc.payroll.master.input.EmployeeFormForUpdateStatus;
import com.jdc.payroll.master.input.EmployeeSearch;
import com.jdc.payroll.master.output.EmployeeInfo;
import com.jdc.payroll.master.output.EmployeeInfoDetails;
import com.jdc.payroll.master.service.EmployeeService;
import com.jdc.payroll.utils.response.ApiResponse;
import com.jdc.payroll.utils.response.DataModificationResult;
import com.jdc.payroll.utils.response.Pager;

@RestController
@RequestMapping("employee")
public class EmployeeApi {
	
	@Autowired
	private EmployeeService service;

	@GetMapping
	ApiResponse<Pager<EmployeeInfo>> search(EmployeeSearch search, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.success(service.search(search, page, size));
	}
	
	@PostMapping
	ApiResponse<DataModificationResult<String>> create(
			@Validated @RequestBody EmployeeFormForCreate form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}
	
	@PutMapping("{code}")
	ApiResponse<DataModificationResult<String>> update(@PathVariable String code,
			@Validated @RequestBody EmployeeFormForUpdate form, BindingResult result) {
		return ApiResponse.success(service.update(code, form));
	}
	
	@PutMapping("{code}/position")
	ApiResponse<DataModificationResult<String>> update(@PathVariable String code,
			@Validated @RequestBody EmployeeFormForUpdatePosition form, BindingResult result) {
		return ApiResponse.success(service.update(code, form));
	}

	@PutMapping("{code}/status")
	ApiResponse<DataModificationResult<String>> update(@PathVariable String code,
			@Validated @RequestBody EmployeeFormForUpdateStatus form, BindingResult result) {
		return ApiResponse.success(service.update(code, form));
	}

	@GetMapping("{code}")
	ApiResponse<EmployeeInfoDetails> findById(@PathVariable String code) {
		return ApiResponse.success(service.findById(code));
	}
	
}
