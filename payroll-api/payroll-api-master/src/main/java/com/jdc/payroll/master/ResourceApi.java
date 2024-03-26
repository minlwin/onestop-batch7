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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.payroll.master.input.ResourceForm;
import com.jdc.payroll.master.input.ResourceSearch;
import com.jdc.payroll.master.output.ResourceInfo;
import com.jdc.payroll.master.output.ResourceInfoDetails;
import com.jdc.payroll.utils.response.ApiResponse;
import com.jdc.payroll.utils.response.DataModificationResult;

@RestController
@RequestMapping("resource")
public class ResourceApi {

	@GetMapping
	ApiResponse<List<ResourceInfo>> search(ResourceSearch search) {
		return ApiResponse.success(null);
	}
	
	@PostMapping
	ApiResponse<DataModificationResult<Integer>> create(
			@Validated @RequestBody ResourceForm form, BindingResult result) {
		return ApiResponse.success(null);
	}
	
	@PostMapping("file")
	ApiResponse<DataModificationResult<List<ResourceInfo>>> importFile(@RequestParam MultipartFile file) {
		return ApiResponse.success(null);
	}
	
	@PutMapping("{id}")
	ApiResponse<DataModificationResult<Integer>> update(
			@PathVariable int id, 
			@Validated @RequestBody ResourceForm form, BindingResult result) {
		return ApiResponse.success(null);
	}
	
	@GetMapping("{id}")
	ApiResponse<ResourceInfoDetails> findById(@PathVariable int id) {
		return ApiResponse.success(null);
	}
}
