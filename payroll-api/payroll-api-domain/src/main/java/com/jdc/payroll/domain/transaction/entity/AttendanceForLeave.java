package com.jdc.payroll.domain.transaction.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class AttendanceForLeave extends Attendance{

	public AttendanceForLeave() {
		setStatus(Status.Leave);
	}
	
	@ManyToOne
	private LeaveApplication application;
	
	private Type type;
	
	public enum Type {
		Paid, UnPaid
	}
}
