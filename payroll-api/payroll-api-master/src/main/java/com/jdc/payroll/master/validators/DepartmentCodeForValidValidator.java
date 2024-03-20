package com.jdc.payroll.master.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.repo.DepartmentRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
@Transactional(readOnly = true)
public class DepartmentCodeForValidValidator implements ConstraintValidator<DepartmentCodeForValid, String>{

	@Autowired
	private DepartmentRepo repo;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return repo.countByCode(value) > 0;
	}

}
