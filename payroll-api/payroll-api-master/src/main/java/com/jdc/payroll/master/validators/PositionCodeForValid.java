package com.jdc.payroll.master.validators;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;

@Retention(RUNTIME)
@Target(PARAMETER)
@Constraint(validatedBy = PositionCodeForValidValidator.class)
@NotBlank
public @interface PositionCodeForValid {

	String message() default "Invalid position code.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
