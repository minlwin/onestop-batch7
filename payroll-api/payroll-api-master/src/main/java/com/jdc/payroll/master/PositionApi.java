package com.jdc.payroll.master;

import java.util.List;

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
import com.jdc.payroll.utils.response.ApiResponse;
import com.jdc.payroll.utils.response.DataModificationResult;

@RestController
@RequestMapping("position")
public class PositionApi {

	@GetMapping
	ApiResponse<List<PositionInfo>> search(PositionSearch search) {
		return ApiResponse.success(null);
	}
	
	@PostMapping
	ApiResponse<DataModificationResult<String>> create(
			@Validated @RequestBody PositionFormForCreate form, BindingResult result) {
		return ApiResponse.success(null);
	}
	
	@PutMapping("{department}/{position}")
	ApiResponse<DataModificationResult<String>> update(
			@PathVariable String department, 
			@PathVariable String position, 
			@Validated @RequestBody PositionFormForUpdate form, BindingResult result) {
		return ApiResponse.success(null);
	}
	
	@GetMapping("{department}/{position}")
	ApiResponse<PositionInfoDetails> findById(
			@PathVariable String department, 
			@PathVariable String position) {
		return ApiResponse.success(null);
	}
}
