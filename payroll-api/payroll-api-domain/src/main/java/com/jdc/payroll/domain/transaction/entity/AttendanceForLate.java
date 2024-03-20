package com.jdc.payroll.domain.transaction.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class AttendanceForLate extends Attendance{

	public AttendanceForLate() {
		setStatus(Status.Late);
	}
}
