package com.jdc.payroll.master.service;

import org.springframework.stereotype.Service;

import com.jdc.payroll.master.input.EmployeeFormForCreate;
import com.jdc.payroll.master.input.EmployeeFormForUpdate;
import com.jdc.payroll.master.input.EmployeeSearch;
import com.jdc.payroll.master.output.EmployeeInfo;
import com.jdc.payroll.master.output.EmployeeInfoDetails;
import com.jdc.payroll.utils.response.DataModificationResult;
import com.jdc.payroll.utils.response.Pager;

@Service
public class EmployeeService {

	public Pager<EmployeeInfo> search(EmployeeSearch search, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModificationResult<String> create(EmployeeFormForCreate form) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModificationResult<String> update(String code, EmployeeFormForUpdate form) {
		// TODO Auto-generated method stub
		return null;
	}

	public EmployeeInfoDetails findById(String code) {
		// TODO Auto-generated method stub
		return null;
	}

}
