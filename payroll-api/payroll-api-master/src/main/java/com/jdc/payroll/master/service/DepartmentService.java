package com.jdc.payroll.master.service;

import static com.jdc.payroll.utils.helpers.EntityOperationHelper.created;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.repo.DepartmentRepo;
import com.jdc.payroll.master.input.DepartmentFormForCreate;
import com.jdc.payroll.master.input.DepartmentFormForManagerChanges;
import com.jdc.payroll.master.input.DepartmentFormForUpdate;
import com.jdc.payroll.master.input.DepartmentSearch;
import com.jdc.payroll.master.output.DepartmentInfo;
import com.jdc.payroll.master.output.DepartmentInfoDetails;
import com.jdc.payroll.utils.response.DataModificationResult;

@Service
@Transactional
public class DepartmentService {
	
	private static final String DOMAIN_NAME = "Department";
	
	@Autowired
	private DepartmentRepo repo;

	public DataModificationResult<String> create(DepartmentFormForCreate form) {
		var entity = repo.save(form.entity());
		return created(entity.getCode(), DOMAIN_NAME);
	}

	public DataModificationResult<String> update(String code, DepartmentFormForUpdate form) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModificationResult<String> update(String code, DepartmentFormForManagerChanges form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public DepartmentInfoDetails findById(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public List<DepartmentInfo> search(DepartmentSearch search) {
		// TODO Auto-generated method stub
		return null;
	}

}
