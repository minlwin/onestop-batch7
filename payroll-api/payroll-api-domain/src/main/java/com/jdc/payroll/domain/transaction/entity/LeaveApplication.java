package com.jdc.payroll.domain.transaction.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.payroll.domain.AbstractEntity;
import com.jdc.payroll.domain.master.entity.Employee;
import com.jdc.payroll.domain.master.entity.LeaveType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "leave_application_seq", allocationSize = 1)
public class LeaveApplication extends AbstractEntity{

	@Id
	@GeneratedValue(generator = "leave_application_seq")
	private long id;
	
	@Column(nullable = false)
	private LocalDateTime applyAt;
	
	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private LeaveType type;

	@Column(nullable = false)
	private LocalDate startDate;
	
	@Column(nullable = false)
	private LocalDate endDate;

	@Column(nullable = false)
	private String remark;
}
