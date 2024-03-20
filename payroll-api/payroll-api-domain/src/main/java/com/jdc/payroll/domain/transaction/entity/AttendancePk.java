package com.jdc.payroll.domain.transaction.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class AttendancePk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "attend_at")
	private LocalDate attendAt;
	@Column(name = "employee_code")
	private String employeeCode;
}
