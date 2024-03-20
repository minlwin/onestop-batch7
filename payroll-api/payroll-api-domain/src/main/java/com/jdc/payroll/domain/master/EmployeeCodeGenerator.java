package com.jdc.payroll.domain.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.entity.EmployeeCodeSeq;
import com.jdc.payroll.domain.master.repo.EmployeeCodeSeqRepo;

@Service
public class EmployeeCodeGenerator {

	@Autowired
	private EmployeeCodeSeqRepo repo;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String next(String department) {
		var seq = repo.findById(department)
				.orElseGet(() -> {
					var entity = new EmployeeCodeSeq();
					entity.setDepartment(department);
					return repo.save(entity);
				});
		
		return seq.next();
	}
}
