package com.jdc.payroll.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.entity.PositionPk.PositionCode;
import com.jdc.payroll.domain.master.repo.PositionRepo;

@Service
public class PositionCodeService {

	@Autowired
	private PositionRepo repo;
	
	@Transactional(readOnly = true)
	public List<PositionCode> findCodeByDepartment(String department) {
		return repo.findCodeByDepartment(department);
	}
}
