package com.jdc.payroll.master.service;

import static com.jdc.payroll.utils.helpers.EntityOperationHelper.created;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.getOne;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.updated;

import java.time.LocalDate;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.EmployeeCodeGenerator;
import com.jdc.payroll.domain.master.entity.Employee;
import com.jdc.payroll.domain.master.entity.Employee.Status;
import com.jdc.payroll.domain.master.entity.EmployeeHistory.Type;
import com.jdc.payroll.domain.master.entity.Employee_;
import com.jdc.payroll.domain.master.entity.PositionPk;
import com.jdc.payroll.domain.master.repo.DepartmentRepo;
import com.jdc.payroll.domain.master.repo.EmployeeRepo;
import com.jdc.payroll.domain.master.repo.PositionRepo;
import com.jdc.payroll.master.input.EmployeeFormForCreate;
import com.jdc.payroll.master.input.EmployeeFormForUpdate;
import com.jdc.payroll.master.input.EmployeeFormForUpdatePosition;
import com.jdc.payroll.master.input.EmployeeFormForUpdateStatus;
import com.jdc.payroll.master.input.EmployeeSearch;
import com.jdc.payroll.master.output.EmployeeInfo;
import com.jdc.payroll.master.output.EmployeeInfoDetails;
import com.jdc.payroll.master.service.events.EmployeeChangeEvent;
import com.jdc.payroll.utils.response.DataModificationResult;
import com.jdc.payroll.utils.response.Pager;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional
public class EmployeeService {
	
	private static final String DOMAIN_NAME = "Employee";
	
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private DepartmentRepo departmentRepo;
	@Autowired
	private PositionRepo positionRepo;
	@Autowired
	private EmployeeCodeGenerator codeGenerator;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	public DataModificationResult<String> create(EmployeeFormForCreate form) {
		
		var code = codeGenerator.next(form.department());
		var password = encoder.encode("123456");
		
		var entity = form.entity(code, password);
		var depertment = getOne(departmentRepo.findById(form.department()), "Department", form.department());
		var position = getOne(positionRepo.findById(PositionPk.parse(form.position())), "Position", form.position());
		
		entity.setDepartment(depertment);
		entity.setPosition(position);
		
		entity = employeeRepo.saveAndFlush(entity);
		
		publisher.publishEvent(new EmployeeChangeEvent(Type.Entry, entity));
		
		return created(code, DOMAIN_NAME);
	}

	public DataModificationResult<String> update(String code, EmployeeFormForUpdate form) {
		var entity = getOne(employeeRepo.findById(code), DOMAIN_NAME, code);
		
		entity.getAccount().setName(form.name());
		entity.setPhone(form.phone());
		entity.setEmail(form.email());
		entity.setGender(form.gender());
		entity.setDateOfBirth(form.dob());
		entity.setAssignDate(form.assignDate());
		entity.setRemark(form.remark());
		
		entity = employeeRepo.saveAndFlush(entity);
		publisher.publishEvent(new EmployeeChangeEvent(Type.ChangeInfo, entity));
		
		return updated(code, DOMAIN_NAME);
	}
	
	public DataModificationResult<String> update(String code, EmployeeFormForUpdatePosition form) {
		
		var entity = getOne(employeeRepo.findById(code), DOMAIN_NAME, code);
		var position = getOne(positionRepo.findById(PositionPk.parse(form.position())), "Position", form.position());
		
		entity.setPosition(position);
		entity.setRemark(form.remark());
		
		entity = employeeRepo.saveAndFlush(entity);
		publisher.publishEvent(new EmployeeChangeEvent(Type.ChangePosition, entity));

		return updated(code, DOMAIN_NAME);
	}

	public DataModificationResult<String> update(String code, EmployeeFormForUpdateStatus form) {
		var entity = getOne(employeeRepo.findById(code), DOMAIN_NAME, code);
		
		entity.setStatus(form.status());
		entity.setRemark(form.remark());
		
		if(form.status() == Status.Retired) {
			entity.setRetireDate(LocalDate.now());
		}
		
		if(form.status() == Status.Permenant) {
			entity.setProvationPassDate(LocalDate.now());
		}

		entity = employeeRepo.saveAndFlush(entity);
		publisher.publishEvent(new EmployeeChangeEvent(Type.ChangeStatus, entity));
		
		return updated(code, DOMAIN_NAME);
	}

	@Transactional(readOnly = true)
	public EmployeeInfoDetails findById(String code) {
		var entity = getOne(employeeRepo.findById(code), DOMAIN_NAME, code);
		return EmployeeInfoDetails.from(entity);
	}

	@Transactional(readOnly = true)
	public Pager<EmployeeInfo> search(EmployeeSearch search, int page, int size) {
		
		var queryFunc = queryFunc(search);
		var countFunc = countFunc(search);
		var pageResult = employeeRepo.search(queryFunc, countFunc, page, size);
		
		return Pager.from(pageResult);
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(EmployeeSearch search) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Employee.class);
			cq.select(cb.count(root.get(Employee_.code)));
			cq.where(search.where(cb, root));
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<EmployeeInfo>> queryFunc(EmployeeSearch search) {
		return cb -> {
			var cq = cb.createQuery(EmployeeInfo.class);
			var root = cq.from(Employee.class);
			EmployeeInfo.select(cq, root);
			cq.where(search.where(cb, root));
			cq.orderBy(cb.asc(root.get(Employee_.code)));
			return cq;
		};
	}

}
