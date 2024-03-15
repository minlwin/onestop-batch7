package com.jdc.payroll.master.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jdc.payroll.domain.master.repo.DepartmentRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class DepartmentCodeValidator implements ConstraintValidator<DepartmentCode, String>{

	@Autowired
	private DepartmentRepo repo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return repo.countByCode(value) > 0;
	}
}
