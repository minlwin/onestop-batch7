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

import com.jdc.payroll.master.input.PositionFormForCreate;
import com.jdc.payroll.master.input.PositionFormForUpdate;
import com.jdc.payroll.master.input.PositionSearch;
import com.jdc.payroll.master.output.PositionInfo;
import com.jdc.payroll.master.output.PositionInfoDetails;
import com.jdc.payroll.master.service.PositionService;
import com.jdc.payroll.utils.response.ApiResponse;
import com.jdc.payroll.utils.response.DataModificationResult;

@RestController
@RequestMapping("position")
public class PositionApi {
	
	@Autowired
	private PositionService service;

	@GetMapping
	ApiResponse<List<PositionInfo>> search(PositionSearch search) {
		return ApiResponse.success(service.search(search));
	}
	
	@PostMapping
	ApiResponse<DataModificationResult<String>> create(
			@Validated @RequestBody PositionFormForCreate form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}
	
	@PutMapping("{code}")
	ApiResponse<DataModificationResult<String>> update(
			@PathVariable String code, 
			@Validated @RequestBody PositionFormForUpdate form, BindingResult result) {
		return ApiResponse.success(service.update(code, form));
	}
	
	@GetMapping("{code}")
	ApiResponse<PositionInfoDetails> findById(
			@PathVariable String code) {
		return ApiResponse.success(service.findById(code));
	}
}
