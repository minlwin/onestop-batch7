package com.jdc.payroll.handlers;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;

import com.jdc.payroll.utils.exceptions.ApiValidationException;

@Aspect
@Configuration
public class ApiValidationAspect {
	
	@Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
	public void apiMethods() {}
	
	@Before(value = "apiMethods() and args(.., result)", argNames = "result")
	public void checkResult(BindingResult result) {
		
		if(result.hasErrors()) {
			throw new ApiValidationException(result.getFieldErrors()
					.stream().map(a -> a.getDefaultMessage())
					.toList());
		}
	}
}
