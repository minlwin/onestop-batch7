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

import com.jdc.payroll.master.input.AllowanceForm;
import com.jdc.payroll.master.input.AllowanceSearch;
import com.jdc.payroll.master.output.AllowanceInfo;
import com.jdc.payroll.master.service.AllowanceService;
import com.jdc.payroll.utils.response.ApiResponse;
import com.jdc.payroll.utils.response.DataModificationResult;

@RestController
@RequestMapping("allowance")
public class AllowanceApi {
	
	@Autowired
	private AllowanceService service;

	@GetMapping
	ApiResponse<List<AllowanceInfo>> search(AllowanceSearch search) {
		return ApiResponse.success(service.search(search));
	}

	@PostMapping
	ApiResponse<DataModificationResult<Integer>> create(
			@Validated @RequestBody AllowanceForm form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}

	@GetMapping("{id}")
	ApiResponse<AllowanceInfo> findById(@PathVariable int id) {
		return ApiResponse.success(service.findById(id));
	}

	@PutMapping("{id}")
	ApiResponse<DataModificationResult<Integer>> update(@PathVariable int id,
			@Validated @RequestBody AllowanceForm form, BindingResult result) {
		return ApiResponse.success(service.update(id, form));
	}

}
