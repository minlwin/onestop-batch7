package com.jdc.payroll.master;

import java.time.LocalDate;

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

import com.jdc.payroll.master.input.HolidayFormForCreate;
import com.jdc.payroll.master.input.HolidayFormForUpdate;
import com.jdc.payroll.master.input.HolidaySearch;
import com.jdc.payroll.master.output.HolidayInfo;
import com.jdc.payroll.master.output.HolidayInfoDetails;
import com.jdc.payroll.master.service.HolidayService;
import com.jdc.payroll.utils.response.ApiResponse;
import com.jdc.payroll.utils.response.DataModificationResult;
import com.jdc.payroll.utils.response.Pager;

@RestController
@RequestMapping("holiday")
public class HolidayApi {
	
	@Autowired
	private HolidayService service;

	@GetMapping
	ApiResponse<Pager<HolidayInfo>> search(HolidaySearch search, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.success(service.search(search, page, size));
	}
	
	@PostMapping
	ApiResponse<DataModificationResult<LocalDate>> create(
			@Validated @RequestBody HolidayFormForCreate form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}
	
	@PutMapping("{date}")
	ApiResponse<DataModificationResult<LocalDate>> update(
			@PathVariable LocalDate date,
			@Validated @RequestBody HolidayFormForUpdate form, BindingResult result) {
		return ApiResponse.success(service.update(date, form));
	}
	
	@GetMapping("{date}")
	ApiResponse<HolidayInfoDetails> findById(@PathVariable LocalDate date) {
		return ApiResponse.success(service.findById(date));
	}
}
