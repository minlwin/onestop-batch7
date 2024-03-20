package com.jdc.payroll.domain.master.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class EmployeeHistoryPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "employee_code")
	private String employeeCode;
	@Column(name = "change_at")
	private LocalDateTime changeAt;
}
