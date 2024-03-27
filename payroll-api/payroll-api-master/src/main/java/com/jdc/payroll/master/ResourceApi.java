package com.jdc.payroll.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.payroll.master.output.ResourceInfo;
import com.jdc.payroll.master.service.ResourceService;
import com.jdc.payroll.utils.response.ApiResponse;

@RestController
@RequestMapping("resource")
public class ResourceApi {
	
	@Autowired
	private ResourceService service;

	@GetMapping
	ApiResponse<List<ResourceInfo>> findAll() {
		return ApiResponse.success(service.findAll());
	}
	
}
