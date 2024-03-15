package com.jdc.payroll.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.payroll.master.input.DepartmentFormForCreate;
import com.jdc.payroll.master.input.DepartmentFormForManagerChanges;
import com.jdc.payroll.master.input.DepartmentFormForUpdate;
import com.jdc.payroll.master.input.DepartmentSearch;
import com.jdc.payroll.master.output.DepartmentInfo;
import com.jdc.payroll.master.output.DepartmentInfoDetails;
import com.jdc.payroll.master.service.DepartmentService;
import com.jdc.payroll.utils.response.ApiResponse;
import com.jdc.payroll.utils.response.DataModificationResult;

@RestController
@RequestMapping("department")
public class DepartmentApi {
	
	@Autowired
	private DepartmentService service;

	@GetMapping
	ApiResponse<List<DepartmentInfo>> search(DepartmentSearch search) {
		return ApiResponse.success(service.search(search));
	}
	
	@PostMapping
	ApiResponse<DataModificationResult<String>> create(
			@Validated @RequestBody DepartmentFormForCreate form, 
			BindingResult result) {	
		return ApiResponse.success(service.create(form));
	}
	
	@PutMapping("{code}")
	ApiResponse<DataModificationResult<String>> update(
			@PathVariable String code, 
			@Validated @RequestBody DepartmentFormForUpdate form, 
			BindingResult result) {
		return ApiResponse.success(service.update(code, form));
	}
	
	@PutMapping("{code}/manager")
	ApiResponse<DataModificationResult<String>> changeManager(
			@PathVariable String code, 
			@Validated @RequestBody DepartmentFormForManagerChanges form, 
			BindingResult result) {
		return ApiResponse.success(service.update(code, form));
	}

	@GetMapping("{code}")
	ApiResponse<DepartmentInfoDetails> findById(@PathVariable String code) {
		return ApiResponse.success(service.findById(code));
	}
	
}
