package com.jdc.payroll.master.service.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.entity.EmployeeHistory;
import com.jdc.payroll.domain.master.repo.EmployeeHistoryRepo;

@Service
public class EmployeeChangeEventListener {
	
	@Autowired
	private EmployeeHistoryRepo repo;

	@Transactional
	@EventListener
	public void handle(EmployeeChangeEvent event) {
		var entity = new EmployeeHistory();
		entity.setEmployee(event.employee());
		entity.setType(event.changeType());
		repo.save(entity);
	}
}
