package com.jdc.payroll.master;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.payroll.domain.master.entity.PositionPk.PositionCode;
import com.jdc.payroll.master.output.PositionCodeInfo;
import com.jdc.payroll.master.service.PositionCodeService;
import com.jdc.payroll.utils.response.ApiResponse;

@RestController
@RequestMapping("position/code")
public class PositionCodeApi {
	
	@Autowired
	private PositionCodeService service;

	@GetMapping
	ApiResponse<List<PositionCodeInfo>> findAll() {
		return ApiResponse.success(Arrays.stream(PositionCode.values())
				.map(PositionCodeInfo::new).toList());
	}

	@GetMapping("{department}")
	ApiResponse<List<PositionCodeInfo>> findCodeForPosition(@PathVariable String department) {
		var list = service.findCodeByDepartment(department);
		return ApiResponse.success(Arrays.stream(PositionCode.values())
				.filter(a -> !list.contains(a)).map(PositionCodeInfo::new).toList());
	}

	@GetMapping("{department}/employee")
	ApiResponse<List<PositionCodeInfo>> findCodeForEmployee(@PathVariable String department) {
		return ApiResponse.success(service.findCodeByDepartment(department)
				.stream().map(PositionCodeInfo::new).toList());
	}

}
